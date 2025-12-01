package com.gestaoentregas.FXController;

import com.sun.javafx.stage.EmbeddedWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

public class MenuMotoristaController {


    @FXML
    private void handleVoltar(ActionEvent event) {
        try {
            EmbeddedWindow App = null;
            App.setScene("MotoristaPrincipal.fxml", null);
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