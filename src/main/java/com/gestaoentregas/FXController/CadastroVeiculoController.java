package com.gestaoentregas.FXController;

import com.gestaoentregas.dados.beans.Usuario;
import com.gestaoentregas.dados.beans.motorista.Motorista;
import com.gestaoentregas.excecoes.MIException;
import com.gestaoentregas.negocio.ServicoMotorista;
import com.gestaoentregas.negocio.ServicoUsuario;
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
    private final ServicoUsuario servicoUsuario;
    private int idMotorista;

    public CadastroVeiculoController(ServicoVeiculo servicoVeiculo, ServicoMotorista servicoMotorista, ServicoUsuario servicoUsuario) {
        this.servicoVeiculo = servicoVeiculo;
        this.servicoMotorista = servicoMotorista;
        this.servicoUsuario = servicoUsuario;
    }

    public void setIdMotorista(int id) {
        this.idMotorista = id;
    }

    @FXML
    protected void handleCadastrarVeiculo(ActionEvent event) {
        if (this.idMotorista == 0) {
            mostrarAlerta("Erro Crítico", "ID do motorista inválido (0). Faça login novamente.");
            return;
        }

        try {
            // 1. Validações de Campos
            if (txtModelo.getText().isEmpty() || txtPlaca.getText().isEmpty() ||
                    txtCapacidade.getText().isEmpty()) {
                mostrarAlerta("Erro de Validação", "Preencha Modelo, Placa e Capacidade.");
                return;
            }

            // 2. Coleta de Dados
            String modelo = txtModelo.getText();
            String placa = txtPlaca.getText();
            int capacidade = Integer.parseInt(txtCapacidade.getText().trim());

            // 3. Criação e Salvamento do Veículo
            Veiculo novoVeiculo = new Veiculo(placa, modelo, capacidade);
            novoVeiculo.setIdVeiculo(0);
            // Se o seu objeto Veiculo tiver marca/ano, sete-os aqui. Caso contrário, ignore.
            this.servicoVeiculo.cadastrarVeiculo(novoVeiculo);

            // 4. VINCULAÇÃO (A SOLUÇÃO DEFINITIVA)

            // --- CORREÇÃO AQUI: Buscamos direto no Serviço de Motorista ---
            // Isso evita o erro "Usuário não encontrado" e o erro de "ClassCastException"
            // Certifique-se que o método buscarPorId existe no ServicoMotorista
            Motorista motorista = servicoMotorista.buscarPorId(this.idMotorista);

            if (motorista != null) {
                // Faz o vínculo na memória
                motorista.setVeiculoMotorista(novoVeiculo);

                // Salva a alteração no banco usando o ServicoUsuario (como você pediu)
                servicoUsuario.atualizarUsuario(motorista);

                mostrarMensagemSucesso("Sucesso", "Veículo cadastrado e vinculado!");
                limparCampos();
            } else {
                mostrarAlerta("Erro Crítico", "Motorista ID " + this.idMotorista + " não encontrado no cadastro de motoristas.");
            }

        } catch (NumberFormatException e) {
            mostrarAlerta("Erro de Formato", "Capacidade deve ser um número.");
        } catch (Exception e) {
            System.err.println("ERRO REAL:");
            e.printStackTrace();
            mostrarAlerta("Erro Crítico", "Falha: " + e.getMessage());
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