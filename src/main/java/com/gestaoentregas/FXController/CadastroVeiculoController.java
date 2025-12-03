package com.gestaoentregas.FXController;

import com.gestaoentregas.negocio.ServicoVeiculo;
import com.gestaoentregas.dados.beans.veiculo.Veiculo;

import com.gestaoentregas.excecoes.VCException;
import com.gestaoentregas.excecoes.PlException;

import com.sun.javafx.stage.EmbeddedWindow;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

public class CadastroVeiculoController {

    // --- Componentes FXML ---
    @FXML private TextField txtMarca;
    @FXML private TextField txtModelo;
    @FXML private TextField txtAno;
    @FXML private TextField txtPlaca;
    @FXML private TextField txtCapacidade;
    @FXML private ComboBox<String> cmbCombustivel;

    private final ApplicationContext context;

    private final ServicoVeiculo servicoVeiculo;

    public CadastroVeiculoController(ServicoVeiculo servicoVeiculo, ApplicationContext context) {
        this.servicoVeiculo = servicoVeiculo;
        this.context = context;
    }

    @FXML
    public void initialize() {
        cmbCombustivel.setItems(FXCollections.observableArrayList(
                "Gasolina", "Etanol", "Diesel", "Flex", "Elétrico"
        ));
    }

    @FXML
    private void handleSalvarVeiculo(ActionEvent event) {
        try {
            String placa = txtPlaca.getText();
            String modelo = txtModelo.getText();
            String marca = txtMarca.getText();
            String combustivel = cmbCombustivel.getValue();
            int ano;
            int capacidade = 0;

            if (placa.isEmpty() || modelo.isEmpty() || marca.isEmpty() || combustivel == null) {
                exibirAlerta(AlertType.WARNING, "Validação", "Todos os campos obrigatórios (Marca, Modelo, Placa e Combustível) devem ser preenchidos.");
                return;
            }

            try {
                ano = Integer.parseInt(txtAno.getText());
            } catch (NumberFormatException e) {
                exibirAlerta(AlertType.WARNING, "Validação", "O campo Ano deve ser um número válido.");
                return;
            }

            try {
                capacidade = Integer.parseInt(txtCapacidade.getText());
            } catch (NumberFormatException e) {
                exibirAlerta(AlertType.WARNING, "Validação", "O campo Capacidade deve ser um número válido.");
            }

            Veiculo novoVeiculo = new Veiculo(placa, modelo, capacidade);

            servicoVeiculo.cadastrarVeiculo(novoVeiculo);

            exibirAlerta(AlertType.INFORMATION, "Sucesso", "Veículo cadastrado com sucesso: " + placa);
            limparCampos();


        } catch (VCException e) {
            exibirAlerta(AlertType.ERROR, "Erro de Cadastro", "Este veículo já está cadastrado no sistema (ID duplicado).");
        } catch (PlException e) {
            exibirAlerta(AlertType.ERROR, "Erro de Cadastro", "A placa do veículo está inválida ou ausente. Verifique os dados.");
        } catch (Exception e) {
            exibirAlerta(AlertType.ERROR, "Erro Inesperado", "Ocorreu um erro ao tentar cadastrar o veículo. Detalhes: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleVoltar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.gestaoentregas/MenuMotorista.fxml"));

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

    private void limparCampos() {
        txtMarca.clear();
        txtModelo.clear();
        txtAno.clear();
        txtPlaca.clear();
        cmbCombustivel.getSelectionModel().clearSelection();
    }

    private void exibirAlerta(AlertType type, String titulo, String mensagem) {
        Alert alert = new Alert(type);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}