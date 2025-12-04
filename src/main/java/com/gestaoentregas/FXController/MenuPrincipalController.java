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
public class MenuPrincipalController {

    private final ApplicationContext context;

    public MenuPrincipalController(ApplicationContext context) {
        this.context = context;
    }

    @FXML
    void irParaDefinicaoRota(ActionEvent event) {
        abrirTela(event, "/com.gestaoentregas/ListaDePedidosView.fxml", "Gestão de Rotas");
    }

    @FXML
    void irParaMonitoramento(ActionEvent event) {
        abrirTela(event, "/com.gestaoentregas/MapaView.fxml", "Monitoramento");
        System.out.println("Funcionalidade de Monitoramento clicada!");
    }

    @FXML
    void irParaGerenciamento(ActionEvent event) {
        abrirTela(event, "/com.gestaoentregas/GerenciarMotorista.fxml", "Gerenciamento");
    }

    @FXML
    void irParaRelatorio(ActionEvent event) {
        abrirTela(event, "/com.gestaoentregas/Relatorios.fxml", "Relatorio");
    }

    @FXML
    void acaoSair(ActionEvent event) {
        System.exit(0);

        // Ou, se quiser apenas fechar a janela e voltar pro login:
        // ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    private void abrirTela(ActionEvent event, String fxmlPath, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));

            loader.setControllerFactory(context::getBean);

            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.setResizable(false);

            stage.setResizable(false);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao abrir a tela: " + fxmlPath);
        }
    }
}
