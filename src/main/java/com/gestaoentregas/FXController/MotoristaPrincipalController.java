package com.gestaoentregas.FXController;

import com.gestaoentregas.dados.beans.entrega.Entrega;
import com.gestaoentregas.dados.beans.entrega.StatusEntrega;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import org.springframework.context.ApplicationContext;
import java.io.IOException;

@Component
public class MotoristaPrincipalController {

    @FXML private TableView<Entrega> tabelaEntregas;
    @FXML private TableColumn<Entrega, String> colunaId;
    @FXML private TableColumn<Entrega, String> colunaEndereco;
    @FXML private TableColumn<Entrega, String> colunaStatus;
    @FXML private Button btnVisualizarRota;
    @FXML private Button btnAcessarMenu;

    private final ApplicationContext context;

    public MotoristaPrincipalController(ApplicationContext context) {
        this.context = context;
    }

    private final ObservableList<Entrega> listaDeEntregas = FXCollections.observableArrayList(
            new Entrega("E001", "Av. Paulista, 1000 - São Paulo", "A Caminho", null, StatusEntrega.EM_TRANSITO, null),
            new Entrega("E002", "Rua das Flores, 55 - Campinas", "Pendente", null, StatusEntrega.PENDENTE, null),
            new Entrega("E003", "Rod. Castelo Branco, Km 30 - Osasco", "Pendente", null, StatusEntrega.PENDENTE, null),
            new Entrega("E004", "Av. Brasil, 450 - Rio de Janeiro", "Pendente", null, StatusEntrega.PENDENTE, null)
    );

    @FXML
    public void initialize() {
        colunaId.setCellValueFactory(new PropertyValueFactory<>("idEntrega"));
        colunaEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        colunaStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tabelaEntregas.setItems(listaDeEntregas);
    }


    @FXML
    private void handleVisualizarRota(ActionEvent event) {
        Entrega entregaSelecionada = tabelaEntregas.getSelectionModel().getSelectedItem();

        if (entregaSelecionada != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.gestaoentregas/DetalhesEntrega.fxml"));

                loader.setControllerFactory(context::getBean);
                Parent parent = loader.load();

                // 1. Obtenção correta do Controller
                DetalhesEntregaController controller = loader.getController();

                // 2. Transferência de Dados
                controller.setEntrega(entregaSelecionada);

                // 3. Obtém o Stage ATUAL
                Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

                // 4. SUBSTITUIÇÃO DA CENA (Navegação suave)
                stageAtual.getScene().setRoot(parent);

                stageAtual.setTitle("Detalhes da entrega");
                // stageAtual.setResizable(false); // Mantém o estado atual

                // Remove showAndWait() e initModality

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Nenhuma Seleção");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione uma entrega na lista.");
            alert.showAndWait();
        }
    }


    @FXML
    private void handleAcessarMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.gestaoentregas/MenuMotorista.fxml"));

            loader.setControllerFactory(context::getBean);
            Parent parent = loader.load();

            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stageAtual.getScene().setRoot(parent);

            stageAtual.setTitle("Menu de motorista");
            stageAtual.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}