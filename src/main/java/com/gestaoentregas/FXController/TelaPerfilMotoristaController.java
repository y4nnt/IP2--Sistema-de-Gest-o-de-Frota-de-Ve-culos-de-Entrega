package com.gestaoentregas.FXController;

import com.gestaoentregas.dados.beans.entrega.Entrega;
import com.gestaoentregas.dados.beans.motorista.Motorista;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TelaPerfilMotoristaController {

    @FXML private Label lblNome;
    @FXML private Label lblTelefone;
    @FXML private Label lblCnh;

    private final ApplicationContext context;
    private String emailClienteLogado;
    private Entrega entregaOrigem;

    public TelaPerfilMotoristaController(ApplicationContext context) {
        this.context = context;
    }

    public void setMotorista(Motorista motorista) {
        if (motorista != null) {
            lblNome.setText(motorista.getNomeMotorista());
            lblTelefone.setText("Tel: " + motorista.getTelefoneMotorista());
            lblCnh.setText("CNH Verificada");
        }
    }

    public void setDadosRetorno(String email, Entrega entrega) {
        this.emailClienteLogado = email;
        this.entregaOrigem = entrega;
    }

    @FXML
    private void handleVoltar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.gestaoentregas/TelaDetalhesPedido.fxml"));
            loader.setControllerFactory(context::getBean);
            Parent root = loader.load();

            TelaDetalhesPedidoController controller = loader.getController();
            controller.setEntrega(entregaOrigem);
            controller.setClienteLogado(emailClienteLogado);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}