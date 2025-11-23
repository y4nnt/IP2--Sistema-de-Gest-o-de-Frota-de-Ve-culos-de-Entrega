package com.gestaoentregas.FXController;

import com.gestaoentregas.dados.beans.entrega.*;
import com.gestaoentregas.excecoes.ECException;
import com.gestaoentregas.excecoes.ENCException;
import com.gestaoentregas.excecoes.PCException;
import com.gestaoentregas.excecoes.VInException;
import com.gestaoentregas.negocio.ServicoEntrega;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.gestaoentregas.negocio.ServicoProduto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private VBox painelDetalhes;
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

    // 1. Injeção Final (Imutável)
    private final ServicoEntrega servicoEntrega;
    private final ServicoProduto servicoProduto;

    private final ObservableList<Entrega> listaEntregas = FXCollections.observableArrayList();

    // Objetos de teste (serão criados assim que o Spring instanciar essa classe)
    private Produto p1 = new Produto("Perfume", "001", 30.00);
    private Produto p2 = new Produto("Camisa", "002", 30.00);
    private Produto p3 = new Produto("Calça", "003", 30.00);
    private Entrega e1 = new Entrega("101", "Rua do Poeta, 77", null, null, StatusEntrega.PENDENTE, "123@456");
    private Entrega e2 = new Entrega("102", "Rua da Harmonia, 93", null, null, StatusEntrega.PENDENTE, "123@456");
    private Entrega e3 = new Entrega("103", "Rua Santa Izabel, 42", null, null, StatusEntrega.PENDENTE, "123@456");

    // 2. CONSTRUTOR: O Spring injeta os serviços aqui automaticamente
    public ListaDePedidosController(ServicoEntrega servicoEntrega, ServicoProduto servicoProduto) {
        this.servicoEntrega = servicoEntrega;
        this.servicoProduto = servicoProduto;
    }


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        painelDetalhes.setVisible(false);
        painelDetalhes.setManaged(false);

        colunaID.setCellValueFactory(new PropertyValueFactory<>("codEntrega"));
        colunaLocal.setCellValueFactory(new PropertyValueFactory<>("localEntrega"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        carregarDadosIniciais();

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

    private void carregarDadosIniciais() {
        try {
            // Cadastra Produtos
            servicoProduto.cadastrarProduto(p1);
            servicoProduto.cadastrarProduto(p2);
            servicoProduto.cadastrarProduto(p3);

            // Monta Entregas
            e1.addProduto(p1); e1.addProduto(p2); e1.addProduto(p3);
            e2.addProduto(p1); e2.addProduto(p2); e2.addProduto(p3);
            e3.addProduto(p1); e3.addProduto(p2); e3.addProduto(p3);

            // Cadastra Entregas
            servicoEntrega.cadastrarEntrega(e1);
            servicoEntrega.cadastrarEntrega(e2);
            servicoEntrega.cadastrarEntrega(e3);

            // Atualiza a lista da tela
            List<Entrega> doBanco = servicoEntrega.listarEntregas();
            listaEntregas.setAll(doBanco);

        } catch (Exception e) {
            e.printStackTrace(); // Trate as exceções aqui
        }
    }

    private void mostrarDetalhes(Entrega entrega) {
        // Preenche os Labels e a Tabela com os dados do pedido selecionado
        lblDetalhesID.setText(entrega.getCodEntrega());
        lblDetalhesLocal.setText(entrega.getLocalEntrega());
        lblDetalhesObs.setText(entrega.getObservacoesEntrega());

        ObservableList<Produto> listaProdutos = FXCollections.observableArrayList(entrega.getProdutos());

        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tabelaProdutos.setItems(listaProdutos);

        // Torna o VBox visível e gerenciado pelo layout
        painelDetalhes.setVisible(true);
        painelDetalhes.setManaged(true);
    }
}
