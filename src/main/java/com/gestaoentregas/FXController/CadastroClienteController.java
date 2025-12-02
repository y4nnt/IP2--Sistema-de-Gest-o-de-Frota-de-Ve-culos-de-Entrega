package com.gestaoentregas.FXController;

import com.gestaoentregas.dados.beans.cliente.Cliente;
import com.gestaoentregas.negocio.ServicoUsuario;
import com.gestaoentregas.excecoes.UIException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;

@Component
public class CadastroClienteController {

    private final ApplicationContext context;
    private final ServicoUsuario servicoUsuario;

    // Injeção de Dependência pelo Spring:
    public CadastroClienteController(ApplicationContext context, ServicoUsuario servicoUsuario) {
        this.context = context;
        this.servicoUsuario = servicoUsuario;
    }

    // --- CAMPOS FXML (do TelaCadastroCliente.fxml) ---
    @FXML private TextField txtNomeCliente;
    @FXML private TextField txtTelefoneCliente;
    @FXML private TextField txtCpfCliente;
    @FXML private DatePicker dpDataNascimentoCliente;
    @FXML private TextField txtEmailCliente;
    @FXML private PasswordField txtSenhaCliente;

    // --- AÇÕES ---

    /**
     * Tenta registrar um novo Cliente no sistema.
     */
    @FXML
    public void acaoCadastrarCliente(ActionEvent event) {
        try {
            // 1. Coleta e validação simples dos dados
            String nome = txtNomeCliente.getText();
            String telefone = txtTelefoneCliente.getText();
            String cpf = txtCpfCliente.getText();
            LocalDate dataNasc = dpDataNascimentoCliente.getValue();
            String email = txtEmailCliente.getText();
            String senha = txtSenhaCliente.getText();

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || dataNasc == null) {
                throw new UIException("Todos os campos obrigatórios devem ser preenchidos.");
            }

            // 2. Cria a instância do Cliente
            // *Assumindo que o ServicoUsuario fornece um ID único (pegarProximoId) ou a lógica de ID está no construtor
            int newId = servicoUsuario.pegarProximoId(); // Este método precisa ser implementado no ServicoUsuario

            Cliente novoCliente = new Cliente(
                    nome, telefone, cpf, email, newId, dataNasc, senha
            );

            // 3. Cadastra o cliente
            servicoUsuario.cadastrarUsuario(novoCliente);

            // 4. Feedback e navegação
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso!", "Cadastro realizado.", "Cliente " + nome + " cadastrado com sucesso!");
            acaoVoltar(event); // Volta para a tela de Login

        } catch (UIException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro de Cadastro", "Falha na validação.", e.getMessage());
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro Inesperado", "Ocorreu um erro ao cadastrar.", e.getMessage());
        }
    }

    /**
     * Retorna para a tela de Login.
     */
    @FXML
    public void acaoVoltar(ActionEvent event) throws IOException {
        abrirTela(event, "/com.gestaoentregas/TelaLogon.fxml", "Login do Sistema");
    }

    // --- MÉTODOS DE UTILIDADE (Copiados do LogonController) ---

    private void mostrarAlerta(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void abrirTela(ActionEvent event, String fxmlPath, String titulo) {
        try {
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            currentStage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            loader.setControllerFactory(context::getBean);
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao abrir a tela: " + fxmlPath);
        }
    }
}