package com.gestaoentregas.FXController;

import com.sun.javafx.stage.EmbeddedWindow;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.time.LocalDate;

public class RegistroManutencaoController {

    @FXML private ComboBox<String> cmbVeiculo;
    @FXML private ComboBox<String> cmbServico;
    @FXML private DatePicker dateManutencao;
    @FXML private TextField txtCusto;
    @FXML private TextField txtHodometro;
    @FXML private TextArea txtDescricao;

    private final ApplicationContext context;

    public RegistroManutencaoController(ApplicationContext context) {
        this.context = context;
    }

    @FXML
    public void initialize() {
        cmbVeiculo.setItems(DadosMotorista.getVeiculosCadastrados());

        cmbServico.setItems(FXCollections.observableArrayList(
                "Troca de Óleo", "Revisão Geral", "Pneus", "Freios", "Reparo Elétrico"
        ));

        dateManutencao.setValue(LocalDate.now());
    }

    @FXML
    private void handleSalvarManutencao(ActionEvent event) {
        if (cmbVeiculo.getValue() == null || txtCusto.getText().isEmpty() || dateManutencao.getValue() == null) {
            exibirAlerta(AlertType.WARNING, "Erro de Registro", "Por favor, preencha os campos obrigatórios: Veículo, Custo e Data.");
            return;
        }

        System.out.println("Registro de Manutenção efetuado.");
        System.out.println("Veículo: " + cmbVeiculo.getValue());
        exibirAlerta(AlertType.INFORMATION, "Sucesso", "Registro de manutenção salvo com sucesso!");
    }

    @FXML
    private void handleVoltar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.gestaoentregas/MenuMotorista.fxml"));

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

    private void exibirAlerta(AlertType type, String titulo, String mensagem) {
        Alert alert = new Alert(type);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}