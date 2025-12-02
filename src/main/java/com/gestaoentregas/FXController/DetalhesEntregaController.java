package com.gestaoentregas.FXController;

import com.gestaoentregas.dados.beans.entrega.Entrega;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

public class DetalhesEntregaController {

    @FXML private Label lblIdEntrega;
    @FXML private Label lblEndereco;

    private Entrega entregaAtual;
    private final ApplicationContext context;

    public  DetalhesEntregaController(ApplicationContext context) {
        this.context = context;
    }

    public void setEntrega(Entrega entrega) {
        this.entregaAtual = entrega;
        lblIdEntrega.setText("ID: " + entrega.getCodEntrega());
        lblEndereco.setText("Endereço: " + entrega.getLocalEntrega());
    }


    @FXML
    private void handleIniciarRota(ActionEvent event) {
        if (entregaAtual != null) {
            System.out.println("Iniciando rota para: " + entregaAtual.getLocalEntrega());
            // Lógica real: App.setScene("NavegacaoTurnByTurn.fxml", ...);
        }
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
}