package com.gestaoentregas.FXController;

import com.gestaoentregas.dados.beans.entrega.Entrega;
import com.gestaoentregas.dados.beans.entrega.StatusEntrega;
import com.gestaoentregas.negocio.ServicoEntrega;
import com.gestaoentregas.negocio.ServicoUsuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Component
public class TelaPrincipalClienteController implements Initializable {

    private final ServicoEntrega servicoEntrega;
    private final ServicoUsuario servicoUsuario;
    private final ApplicationContext context;

    @FXML private TableView<Entrega> tabelaPedidos;
    @FXML private TableColumn<Entrega, String> colunaId;
    @FXML private TableColumn<Entrega, LocalDateTime> colunaData;
    @FXML private TableColumn<Entrega, StatusEntrega> colunaStatus;
    @FXML private TableColumn<Entrega, String> colunaEndereco;

    @FXML private Button btnVerDetalhes;
    @FXML private Button btnAcessarMenu;

    private String emailClienteLogado;
    private final ObservableList<Entrega> listaMeusPedidos = FXCollections.observableArrayList();


    public TelaPrincipalClienteController(ServicoEntrega servicoEntrega, ServicoUsuario servicoUsuario, ApplicationContext context) {
        this.servicoEntrega = servicoEntrega;
        this.servicoUsuario = servicoUsuario;
        this.context = context;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarTabela();
    }

    private void configurarTabela() {
        colunaId.setCellValueFactory(new PropertyValueFactory<>("codEntrega"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("dataHoraEntrega"));
        colunaStatus.setCellValueFactory(new PropertyValueFactory<>("statusEntrega"));
        colunaEndereco.setCellValueFactory(new PropertyValueFactory<>("localEntrega"));
    }

    public void setClienteLogado(String email) {
        this.emailClienteLogado = email;
        carregarMeusPedidos();
    }

    private void carregarMeusPedidos() {
        if (emailClienteLogado == null) return;

        listaMeusPedidos.clear();

        ArrayList<Entrega> todas = servicoEntrega.listarEntregas();

        for (Entrega e : todas) {
            if (e.getEmailComprador() != null && e.getEmailComprador().equals(emailClienteLogado)) {
                listaMeusPedidos.add(e);
            }
        }
        tabelaPedidos.setItems(listaMeusPedidos);
    }

    @FXML
    private void handleVerDetalhes(ActionEvent event) {
        Entrega entregaSelecionada = tabelaPedidos.getSelectionModel().getSelectedItem();

        if (entregaSelecionada == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atenção");
            alert.setHeaderText("Nenhuma entrega selecionada");
            alert.setContentText("Por favor, selecione um pedido na tabela.");
            alert.showAndWait();
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.gestaoentregas/TelaDetalhesPedido.fxml"));
            loader.setControllerFactory(context::getBean);
            Parent root = loader.load();

            TelaDetalhesPedidoController controller = loader.getController();
            controller.setEntrega(entregaSelecionada);
            controller.setClienteLogado(emailClienteLogado);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAcessarMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.gestaoentregas/MenuCliente.fxml"));
            loader.setControllerFactory(context::getBean);
            Parent root = loader.load();

            MenuClienteController controller = loader.getController();
            controller.setClienteLogado(emailClienteLogado);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}