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

    public MenuMotoristaController(ApplicationContext context) {
        this.context = context;
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
        System.out.println("Navegando para Cadastro de Veículo.");
    }

    @FXML
    private void handleAdicionarManutencao(ActionEvent event) {
        System.out.println("Navegando para Adicionar Manutenção.");
    }
}