package com.gestaoentregas.FXController;

import com.gestaoentregas.dados.beans.entrega.Entrega;
import com.sun.javafx.stage.EmbeddedWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import java.io.IOException;

public class MotoristaPrincipalController {

    @FXML private TableView<Entrega> tabelaEntregas;
    @FXML private TableColumn<Entrega, String> colunaId;
    @FXML private TableColumn<Entrega, String> colunaEndereco;
    @FXML private TableColumn<Entrega, String> colunaStatus;
    @FXML private Button btnVisualizarRota;
    @FXML private Button btnAcessarMenu;

    private final ObservableList<Entrega> listaDeEntregas = FXCollections.observableArrayList(
            new Entrega("E001", "Av. Paulista, 1000 - São Paulo", "A Caminho"),
            new Entrega("E002", "Rua das Flores, 55 - Campinas", "Pendente"),
            new Entrega("E003", "Rod. Castelo Branco, Km 30 - Osasco", "Pendente"),
            new Entrega("E004", "Av. Brasil, 450 - Rio de Janeiro", "Pendente")
    );

    @FXML
    public void initialize() {
        colunaId.setCellValueFactory(new PropertyValueFactory<>("idEntrega"));
        colunaEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        colunaStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tabelaEntregas.setItems(listaDeEntregas);
    }


    @FXML
    private void handleVisualizarRota(ActionEvent event) {
        Entrega entregaSelecionada = tabelaEntregas.getSelectionModel().getSelectedItem();

        if (entregaSelecionada != null) {
            try {
                EmbeddedWindow App = null;
                App.setScene("DetalhesEntrega.fxml", controller -> {
                    DetalhesEntregaController detalhesController = (DetalhesEntregaController) controller;
                    detalhesController.setEntrega(entregaSelecionada);
                });
            } catch (IOException e) {
                System.err.println("Erro ao carregar DetalhesEntrega.fxml: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Nenhuma Seleção");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione uma entrega na lista.");
            alert.showAndWait();
        }
    }


    @FXML
    private void handleAcessarMenu(ActionEvent event) {
        try {
            EmbeddedWindow App = null;
            App.setScene("MenuMotorista.fxml", null);
        } catch (IOException e) {
            System.err.println("Erro ao carregar MenuMotorista.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }
}