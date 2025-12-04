package com.gestaoentregas.FXController;

import com.gestaoentregas.dados.beans.motorista.Motorista; // Ajuste o pacote se necessário
import com.gestaoentregas.dados.beans.TipoUsuario;
import com.gestaoentregas.negocio.ServicoMotorista;
import com.gestaoentregas.negocio.ServicoUsuario;
import com.gestaoentregas.excecoes.UIException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CadastroMotoristaController {

    private final ApplicationContext context;
    private final ServicoUsuario servicoUsuario;
    private final ServicoMotorista servicoMotorista;

    // Injeção de Dependência pelo Spring:
    public CadastroMotoristaController(ApplicationContext context, ServicoUsuario servicoUsuario, ServicoMotorista servicoMotorista) {
        this.context = context;
        this.servicoUsuario = servicoUsuario;
        this.servicoMotorista = servicoMotorista;
    }

    // --- CAMPOS FXML (do TelaCadastroMotorista.fxml) ---
    @FXML private TextField txtNomeMotorista;
    @FXML private TextField txtTelefoneMotorista;
    @FXML private TextField txtCpfMotorista;
    @FXML private TextField txtCnhMotorista;
    @FXML private TextField txtIdadeMotorista;
    @FXML private TextField txtEmailMotorista;
    @FXML private PasswordField txtSenhaMotorista;

    // --- AÇÕES ---

    /**
     * Tenta registrar um novo Motorista no sistema.
     */
    @FXML
    public void acaoSalvar(ActionEvent event) {
        try {
            // 1. Coleta e validação simples dos dados
            String nome = txtNomeMotorista.getText();
            String telefone = txtTelefoneMotorista.getText();
            String cpf = txtCpfMotorista.getText();
            String cnh = txtCnhMotorista.getText();
            String idadeStr = txtIdadeMotorista.getText();
            String email = txtEmailMotorista.getText();
            String senha = txtSenhaMotorista.getText();

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || cnh.isEmpty() || idadeStr.isEmpty()) {
                throw new UIException("Todos os campos obrigatórios devem ser preenchidos.");
            }
            int idade = Integer.parseInt(idadeStr); // Converte idade

            // 2. Cria a instância do Motorista
            // *Assumindo que o ServicoUsuario fornece um ID único (pegarProximoId)


            Motorista novoMotorista = new Motorista(
                    nome, telefone, cpf, cnh, idade, null, 0, email, senha
            );


            // 3. Cadastra o motorista
            servicoMotorista.cadastrarMotorista(novoMotorista);

            // 4. Feedback e navegação
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso!", "Cadastro realizado.", "Motorista " + nome + " cadastrado com sucesso!");
            acaoVoltar(event); // Volta para a tela de Login

        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro de Validação", "Idade inválida.", "Por favor, digite a idade em formato numérico.");
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

    //MÉTODOS DE UTILIDADE

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