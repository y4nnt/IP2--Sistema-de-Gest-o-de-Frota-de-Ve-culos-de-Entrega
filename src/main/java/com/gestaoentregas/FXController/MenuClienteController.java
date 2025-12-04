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
public class MenuClienteController {

    private final ApplicationContext context;
    private String emailClienteLogado;

    public MenuClienteController(ApplicationContext context) {
        this.context = context;
    }

    public void setClienteLogado(String email) {
        this.emailClienteLogado = email;
    }

    @FXML
    private void handleVoltarPedidos(ActionEvent event) {
        navegar(event, "/com.gestaoentregas/TelaPrincipalCliente.fxml");
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        navegar(event, "/com.gestaoentregas/TelaLogon.fxml");
    }

    private void navegar(ActionEvent event, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            loader.setControllerFactory(context::getBean);
            Parent root = loader.load();

            if (loader.getController() instanceof TelaPrincipalClienteController) {
                ((TelaPrincipalClienteController) loader.getController()).setClienteLogado(emailClienteLogado);
            }

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}