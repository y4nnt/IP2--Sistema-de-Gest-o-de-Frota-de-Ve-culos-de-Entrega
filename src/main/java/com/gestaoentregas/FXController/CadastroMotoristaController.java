package com.gestaoentregas.FXController;

import com.gestaoentregas.dados.beans.motorista.Motorista;
import com.gestaoentregas.negocio.ServicoMotorista;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class CadastroMotoristaController {
    private final ServicoMotorista servicoMotorista;

    public CadastroMotoristaController(ServicoMotorista servicoMotorista) {
        this.servicoMotorista = servicoMotorista;
    }

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtCnh;
    @FXML
    private TextField txtIdade;

    public void acaoSalvar() {
        try {
            String nome = txtNome.getText().trim();
            String telefone = txtTelefone.getText().trim();
            String cpf = txtCpf.getText().trim();
            String cnh = txtCnh.getText().trim();
            String idadeTexto = txtIdade.getText().trim();

            if (nome.isEmpty() || telefone.isEmpty() || cpf.isEmpty() || cnh.isEmpty() || idadeTexto.isEmpty()){
                mostrarAlerta("Erro de Validação", "Todos os campos são obrigatórios");
                return;
            }

            int idade;
            try {
                idade = Integer.parseInt(idadeTexto);
            } catch (NumberFormatException e) {
                mostrarAlerta("Erro na Idade", "A idade deve ser um número válido (sem letras)");
                return;
            }

            if (idade < 18) {
                mostrarAlerta("Erro de Cadastro", "O motorista deve ser maior de idade");
                return;
            }

            Motorista novoMotorista = new Motorista (nome, telefone, cpf, cnh, idade);

            servicoMotorista.cadastrarMotorista(novoMotorista);

            mostrarAlerta("Sucesso", "Motorista " + nome + " cadastrado com sucesso");

            limparCampos();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Erro Critico", "ocorreu um erro ao salvar: " + e.getMessage());
        }
    }

    private void limparCampos() {
        txtNome.clear();
        txtTelefone.clear();
        txtCpf.clear();
        txtCnh.clear();
        txtIdade.clear();
    }

    private void mostrarAlerta(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}