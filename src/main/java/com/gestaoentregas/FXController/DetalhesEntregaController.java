package com.gestaoentregas.FXController;

import com.gestaoentregas.dados.beans.entrega.Entrega;
import com.gestaoentregas.dados.beans.entrega.StatusEntrega;
import com.gestaoentregas.excecoes.EIException;
import com.gestaoentregas.negocio.Alerta;
import com.gestaoentregas.negocio.ServicoEntrega;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DetalhesEntregaController {

    @FXML private Label lblIdEntrega;
    @FXML private Label lblEndereco;

    private final ServicoEntrega servicoEntrega;
    private Entrega entregaAtual;
    private final ApplicationContext context;

    public  DetalhesEntregaController(ServicoEntrega servicoEntrega, ApplicationContext context) {
        this.servicoEntrega = servicoEntrega;
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
            try {
                this.servicoEntrega.atualizarStatusEntrega(entregaAtual, StatusEntrega.EM_TRANSITO);
                System.out.println("Iniciando rota para: " + entregaAtual.getLocalEntrega());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Iniciar Rota");
                alert.setHeaderText("Iniciando Rota");
                alert.setContentText("Rota iniciada com sucesso!");
                alert.showAndWait();
            } catch (EIException e) {
                e.printStackTrace();
            }
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

            stageAtual.setTitle("Menu Motorista");
            stageAtual.setResizable(false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}