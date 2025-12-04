package com.gestaoentregas.FXController;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MenuMotoristaController {

    private final ApplicationContext context;
    private int idMotorista;

    public MenuMotoristaController(ApplicationContext context) {
        this.context = context;
    }

    public void setIdMotorista(int id) {
        this.idMotorista = id;
        System.out.println("Menu carregado para o motorista ID: " + id);
    }

    @FXML
    private void handleVoltar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.gestaoentregas/TelaPrincipalMotorista.fxml"));

            loader.setControllerFactory(context::getBean);
            Parent parent = loader.load();

            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stageAtual.getScene().setRoot(parent);

            stageAtual.setTitle("Tela Principal Motorista");
            stageAtual.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleLogout(ActionEvent event) {
        try {
            System.out.println("Logout realizado. Carregando Tela de Logon...");
            // App.setScene("LogonScreen.fxml", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleCadastrarVeiculo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.gestaoentregas/CadastroVeiculo.fxml"));

            loader.setControllerFactory(context::getBean);

            Parent root = loader.load();

            CadastroVeiculoController controller = loader.getController();

            // 2. Passa o ID para ele
            controller.setIdMotorista(this.idMotorista);
            // ---------------------------------

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Registro Manutencao");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAdicionarManutencao(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.gestaoentregas/RegistroManutencao.fxml"));

            loader.setControllerFactory(context::getBean);

            Parent root = loader.load();

            RegistroManutencaoController controller = loader.getController();

            // 2. Passa o ID para ele
            controller.setIdMotorista(this.idMotorista);
            // ---------------------------------

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Registro Manutencao");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}