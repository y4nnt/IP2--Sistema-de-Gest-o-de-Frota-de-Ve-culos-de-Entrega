package com.gestaoentregas.FXController;

import com.gestaoentregas.dados.beans.entrega.*;
import com.gestaoentregas.dados.beans.motorista.*;
import com.gestaoentregas.dados.beans.veiculo.*;
import com.gestaoentregas.dados.beans.VeiculoMotorista;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ListaDePedidosController implements Initializable {

    @FXML
    private TableView<Entrega> tabelaSelecaoEntregasMotorista;
    @FXML
    private TableColumn<Entrega, String> colunaID;
    @FXML
    private TableColumn<Entrega, String> colunaLocal;

    private final ObservableList<Entrega> listaPedidos = FXCollections.observableArrayList(
            new Entrega("101", "Rua do Poeta, 77",null, null, Entrega.StatusEntrega.PENDENTE, "123@456"),
            new Entrega("102", "Rua da Harmonia, 93", null, null, Entrega.StatusEntrega.PENDENTE, "123@456"),
            new Entrega("103", "Rua Santa Izabel, 42", null, null, Entrega.StatusEntrega.PENDENTE, "123@456")
    );

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 1. Mapear as colunas para as propriedades da classe Pedido
        colunaID.setCellValueFactory(new PropertyValueFactory<>("codEntrega"));
        colunaLocal.setCellValueFactory(new PropertyValueFactory<>("localEntrega"));

        // 2. Carregar os dados na tabela
        tabelaSelecaoEntregasMotorista.setItems(listaPedidos);

        // 3. Adicionar o Listener para o clique na linha
        tabelaSelecaoEntregasMotorista.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Abre a nova janela com os dados do pedido
                // abrirTelaDetalhes(newSelection);

                // Opcional: Desselecionar a linha após o clique, se quiser que o usuário possa clicar novamente
                tabelaSelecaoEntregasMotorista.getSelectionModel().clearSelection();
            }
        });
    }
}
