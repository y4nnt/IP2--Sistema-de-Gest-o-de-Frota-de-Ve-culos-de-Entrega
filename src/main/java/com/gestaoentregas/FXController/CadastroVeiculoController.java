package com.gestaoentregas.FXController;

import com.gestaoentregas.negocio.ServicoMotorista;
import com.gestaoentregas.negocio.ServicoVeiculo;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import com.gestaoentregas.dados.beans.veiculo.Veiculo;
import com.gestaoentregas.dados.repositorios.RepositorioVeiculo;
import com.gestaoentregas.dados.repositorios.IRepositorioVeiculo; // Se você usa a interface
import org.springframework.stereotype.Component;

@Component
public class CadastroVeiculoController {

    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtAno;

    @FXML
    private TextField txtPlaca;

    @FXML
    private TextField txtCapacidade;

    private final ServicoVeiculo servicoVeiculo;
    private final ServicoMotorista servicoMotorista;
    private int idMotorista;

    public CadastroVeiculoController(ServicoVeiculo servicoVeiculo, ServicoMotorista servicoMotorista) {
        this.servicoVeiculo = servicoVeiculo;
        this.servicoMotorista = servicoMotorista;
    }

    public void setIdMotorista(int id) {
        this.idMotorista = id;
    }
    @FXML
    protected void handleCadastrarVeiculo(ActionEvent event) {
        try {
            // 1. Validação básica antes de tentar converter números (evita erros feios)
            if (txtMarca.getText().isEmpty() || txtModelo.getText().isEmpty() ||
                    txtPlaca.getText().isEmpty() || txtAno.getText().isEmpty() ||
                    txtCapacidade.getText().isEmpty()) { // <--- Verifique se existe txtCapacidade no FXML

                mostrarAlerta("Erro de Validação", "Por favor, preencha todos os campos.");
                return;
            }

            String marca = txtMarca.getText();
            String modelo = txtModelo.getText();
            String placa = txtPlaca.getText();

            int ano = Integer.parseInt(txtAno.getText().trim());
            int capacidade = Integer.parseInt(txtCapacidade.getText().trim());

            // 3. Validações lógicas
            if (ano < 1900 || ano > 2026) {
                mostrarAlerta("Data Inválida", "O ano do veículo parece incorreto.");
                return;
            }

            if (capacidade <= 0) {
                mostrarAlerta("Capacidade Inválida", "A capacidade deve ser maior que zero.");
                return;
            }

            Veiculo novoVeiculo = new Veiculo(placa, modelo, capacidade);

            this.servicoVeiculo.cadastrarVeiculo(novoVeiculo);
            this.servicoMotorista.buscarMotorista(idMotorista).setVeiculoMotorista(novoVeiculo);

            mostrarMensagemSucesso("Sucesso", "Veículo " + placa + " cadastrado com sucesso!");
            limparCampos();

        } catch (NumberFormatException e) {
            mostrarAlerta("Formato Inválido", "Os campos 'Ano' e 'Capacidade' devem conter apenas números.");
        } catch (Exception e) {
            mostrarAlerta("Erro no Sistema", "Erro ao cadastrar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void mostrarMensagemSucesso(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void limparCampos() {
        txtMarca.clear();
        txtModelo.clear();
        txtAno.clear();
        txtPlaca.clear();
    }
}