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
        System.out.println("DEBUG: Tentando cadastrar veículo para ID: " + this.idMotorista);

        if (this.idMotorista == 0) {
            mostrarAlerta("Erro", "ID inválido. Faça login novamente.");
            return;
        }

        try {
            // 1. Coleta e Validação
            if (txtModelo.getText().isEmpty() || txtPlaca.getText().isEmpty() || txtCapacidade.getText().isEmpty()) {
                mostrarAlerta("Atenção", "Preencha todos os campos.");
                return;
            }

            String modelo = txtModelo.getText();
            String placa = txtPlaca.getText();
            int capacidade = Integer.parseInt(txtCapacidade.getText().trim());

            // 2. Busca o Motorista (CORREÇÃO AQUI: usar buscarMotorista)
            // IMPORTANTE: Se o ID do login for 1 e o repo tiver 101, isso retornará null.
            Motorista motorista = servicoMotorista.buscarPorId(this.idMotorista);

            if (motorista == null) {
                mostrarAlerta("Erro Crítico", "Motorista com ID " + this.idMotorista + " não encontrado no banco de dados.");
                return;
            }


            Veiculo novoVeiculo = new Veiculo(placa, modelo, capacidade);
            novoVeiculo.setIdVeiculo(0);

            servicoVeiculo.cadastrarVeiculo(novoVeiculo);


            motorista.setVeiculoMotorista(novoVeiculo);


            servicoUsuario.atualizarUsuario(motorista);

            mostrarMensagemSucesso("Sucesso", "Veículo " + modelo + " vinculado ao motorista " + motorista.getNomeMotorista());
            limparCampos();

        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "Capacidade deve ser um número inteiro.");
        } catch (Exception e) {
            e.printStackTrace(); // Isso imprime o erro no console do IntelliJ
            mostrarAlerta("Erro no Sistema", "Falha ao salvar: " + e.getMessage());
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