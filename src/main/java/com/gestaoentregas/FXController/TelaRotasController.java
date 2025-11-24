package com.gestaoentregas.FXController;

import com.gestaoentregas.dados.beans.entrega.Rota;
import com.gestaoentregas.excecoes.RCException;
import com.gestaoentregas.negocio.ServicoRota;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext; // Importante!
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class TelaRotasController {

    private Rota rota;
    private final ServicoRota servicoRota;
    private final ApplicationContext context; // 1. Adicionado Contexto do Spring

    @FXML
    private ListView<String> listViewPontosParada;
    @FXML
    private Label lblOrigem;

    // 2. Injeta o Contexto no construtor
    public TelaRotasController(ServicoRota servicoRota, ApplicationContext context) {
        this.servicoRota = servicoRota;
        this.context = context;
    }

    public void setRota(Rota rota) {
        this.rota = rota;

        // Atualiza a tela AGORA. O initialize já rodou, os componentes existem.
        if (this.rota != null) {
            if (lblOrigem != null) {
                lblOrigem.setText(this.rota.getOrigemRota());
            }

            if (this.rota.getPontosParada() != null) {
                ObservableList<String> obsLista = FXCollections.observableArrayList(this.rota.getPontosParada());
                listViewPontosParada.setItems(obsLista);
            }
        }
    }

    @FXML
    public void initialize() {
        // 3. Limpei a lógica duplicada daqui.
        // Deixe vazio ou use apenas para configurações visuais estáticas (tamanho de fonte, cores, etc).
        System.out.println("Tela de rotas inicializada (aguardando setRota)...");
    }

    @FXML
    void acaoBotaoVoltar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void acaoBotaoFinalizar(ActionEvent event) {
        try {
            this.rota.setIdRota(ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE));
            servicoRota.cadastrarRota(this.rota);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.gestaoentregas/TelaSelecaoMotorista.fxml"));

            loader.setControllerFactory(context::getBean);

            Parent root = loader.load();

            TelaSelecaoMotoristaController controller = loader.getController();
            controller.initData(rota);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Seleção de Motorista");
            stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            stage.setResizable(false);


            stage.setAlwaysOnTop(true);
            stage.show();
            stage.setAlwaysOnTop(false);
            stage.toFront();
            stage.requestFocus();

            Node source = (Node) event.getSource();

            Stage stageAtual = (Stage) source.getScene().getWindow();

            stageAtual.close();

        } catch (RCException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao cadastrar rota.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Falha técnica");
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
    }
}