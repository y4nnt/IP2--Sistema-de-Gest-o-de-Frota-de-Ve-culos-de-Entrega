package com.gestaoentregas.FXController;

import com.gestaoentregas.dados.beans.entrega.Entrega;
import com.sun.javafx.stage.EmbeddedWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.io.IOException;

public class DetalhesEntregaController {

    @FXML private Label lblIdEntrega;
    @FXML private Label lblEndereco;

    private Entrega entregaAtual;

    public void setEntrega(Entrega entrega) {
        this.entregaAtual = entrega;
        lblIdEntrega.setText("ID: " + entrega.getIdEntrega());
        lblEndereco.setText("Endereço: " + entrega.getEndereco());
    }


    @FXML
    private void handleIniciarRota(ActionEvent event) {
        if (entregaAtual != null) {
            System.out.println("Iniciando rota para: " + entregaAtual.getEndereco());
            // Lógica real: App.setScene("NavegacaoTurnByTurn.fxml", ...);
        }
    }


    @FXML
    private void handleVoltar(ActionEvent event) {
        try {
            // Volta para a tela principal
            EmbeddedWindow App = null;
            App.setScene("MotoristaPrincipal.fxml", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}