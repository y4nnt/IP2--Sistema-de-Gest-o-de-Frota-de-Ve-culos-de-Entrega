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
        abrirTela(event, "/com.gestaoentregas/TelaLogon.fxml", "Tela de Logon");
    }

    private void abrirTela(ActionEvent event, String fxmlPath, String titulo) {
        try {
            // 1. Carrega o Loader da NOVA tela
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            loader.setControllerFactory(context::getBean);
            Parent root = loader.load();

            // 2. Prepara a nova janela
            Stage stageNovo = new Stage();
            stageNovo.setScene(new Scene(root));
            stageNovo.setTitle(titulo);
            stageNovo.setResizable(false);

            // 3. Mostra a nova janela
            stageNovo.show();

            // 4. FECHA A JANELA ANTIGA (Só chega aqui se a nova abriu sem erros)
            // Pegamos a janela atual através do botão que foi clicado
            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageAtual.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro crítico ao abrir a tela: " + fxmlPath);
            // Aqui você pode mostrar um Alert de erro para o usuário se quiser
        }
    }
}
