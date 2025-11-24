package com.gestaoentregas.FXController;

import com.gestaoentregas.dados.beans.entrega.*;
import com.gestaoentregas.excecoes.ECException;
import com.gestaoentregas.excecoes.ENCException;
import com.gestaoentregas.excecoes.PCException;
import com.gestaoentregas.excecoes.VInException;
import com.gestaoentregas.negocio.ServicoEntrega;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.gestaoentregas.negocio.ServicoProduto;
import com.gestaoentregas.negocio.ServicoRota;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ListaDePedidosController implements Initializable {

    @FXML
    private TableView<Entrega> tabelaSelecaoEntregasMotorista;
    @FXML
    private TableColumn<Entrega, String> colunaID;
    @FXML
    private TableColumn<Entrega, String> colunaLocal;
    @FXML
    private Pane painelDetalhes;
    @FXML
    private Label lblDetalhesID;
    @FXML
    private Label lblDetalhesLocal;
    @FXML
    private Label lblDetalhesObs;
    @FXML
    private TableView<Produto> tabelaProdutos;
    @FXML
    private TableColumn<Produto, String> colunaNome;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnRemove;

    private Rota rota = new Rota("Central, 001", 0, null, null, "001");
    private Entrega entregaEmVisualizacao;


    // 1. Injeção Final (Imutável)
    private final ServicoEntrega servicoEntrega;
    private final ServicoRota servicoRota;

    private final ObservableList<Entrega> listaEntregas = FXCollections.observableArrayList();


    // 2. CONSTRUTOR: O Spring injeta os serviços aqui automaticamente
    public ListaDePedidosController(ServicoEntrega servicoEntrega, ServicoRota servicoRota) {
        this.servicoEntrega = servicoEntrega;
        this.servicoRota = servicoRota;
    }

    public void adicionarEntrega(Entrega entrega, Rota rota) {
        rota.addEntrega(entrega);
    }


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        painelDetalhes.setVisible(false);
        painelDetalhes.setManaged(false);

        colunaID.setCellValueFactory(new PropertyValueFactory<>("codEntrega"));
        colunaLocal.setCellValueFactory(new PropertyValueFactory<>("localEntrega"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        listaEntregas.addAll(servicoEntrega.listarEntregas());

        tabelaSelecaoEntregasMotorista.setItems(listaEntregas);

        tabelaSelecaoEntregasMotorista.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        mostrarDetalhes(newSelection);
                    } else {
                        painelDetalhes.setVisible(false);
                        painelDetalhes.setManaged(false);
                    }
                }
        );
    }

    private void mostrarDetalhes(Entrega entrega) {

        this.entregaEmVisualizacao = entrega;

        if (rota.buscarEntregaRota(entrega.getCodEntrega()) == null) {
            btnAdd.setManaged(true);
            btnAdd.setVisible(true);
            btnRemove.setVisible(false);
            btnRemove.setManaged(false);
        } else {
            btnAdd.setManaged(false);
            btnAdd.setVisible(false);
            btnRemove.setVisible(true);
            btnRemove.setManaged(true);
        }

        lblDetalhesID.setText(entrega.getCodEntrega());
        lblDetalhesLocal.setText(entrega.getLocalEntrega());
        lblDetalhesObs.setText(entrega.getObservacoesEntrega());

        ObservableList<Produto> listaProdutos = FXCollections.observableArrayList(entrega.getProdutos());

        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tabelaProdutos.setItems(listaProdutos);

        painelDetalhes.setVisible(true);
        painelDetalhes.setManaged(true);
    }

    @FXML
    public void adicionarEntregaNaRota() {
        this.rota.addEntrega(entregaEmVisualizacao);
    }

    @FXML
    public void removerEntregaNaRota() {
        this.rota.removerEntrega(entregaEmVisualizacao);
    }

    @FXML
    void acaoFinalizarEntregaIrParaRotas(ActionEvent event) {
        try {
            // 1. Carrega o FXML
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com.gestaoentregas/TelaRotas.fxml")));

            // 2. Cria uma NOVA janela (Stage)
            Stage stageNovo = new Stage();
            Scene scene = new Scene(parent);
            stageNovo.setScene(scene);
            stageNovo.setTitle("Visualização de Rotas");

            // 3. Configurações de Modal (Opcional, mas recomendado)
            // Isso impede que o usuário clique na tela de trás enquanto essa estiver aberta
            // Importe: javafx.stage.Modality
            stageNovo.initModality(javafx.stage.Modality.APPLICATION_MODAL);

            // Define quem é o "pai" dessa janela (para bloquear corretamente)
            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageNovo.initOwner(stageAtual);

            // 4. Mostra a nova janela e espera
            stageNovo.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
