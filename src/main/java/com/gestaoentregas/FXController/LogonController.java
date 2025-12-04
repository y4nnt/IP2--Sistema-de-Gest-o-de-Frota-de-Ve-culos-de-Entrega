package com.gestaoentregas.FXController;

import com.gestaoentregas.dados.beans.TipoUsuario; // Adicionar o TipoUsuario
import com.gestaoentregas.dados.beans.Usuario; // Adicionar o Usuario
import com.gestaoentregas.negocio.ServicoUsuario;
import com.gestaoentregas.dados.repositorios.RepositorioUsuario;
import com.gestaoentregas.excecoes.UIException; // Adicionar a exceção para tratar erros de login

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert; // Import para exibir mensagens de erro
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LogonController {

    private final ApplicationContext context;
    private final ServicoUsuario servicoUsuario;

    public LogonController(ApplicationContext context, ServicoUsuario servicoUsuario) {
        this.context = context;
        this.servicoUsuario = servicoUsuario;
    }

    @FXML
    private TextField txtUsuario; // Usuário (Email)

    @FXML
    private PasswordField txtSenha; // Senha

    @FXML
    private Button btnLoginCliente;

    @FXML
    private Button btnLoginAdmin;

    @FXML
    private Button btnLoginMotorista;

    // --- MÉTODOS DE AÇÃO (onAction) ---

    /**
     * Lida com a ação de login do Cliente.
     */
    @FXML
    public void acaoLoginCliente(ActionEvent event) throws IOException {
        String email = txtUsuario.getText();
        String senha = txtSenha.getText();

        try {
            // 1. Chama o serviço, esperando um usuário do tipo CLIENTE
            Usuario clienteAutenticado = servicoUsuario.autenticar(email, senha, TipoUsuario.CLIENTE);

            // 2. Login BEM-SUCEDIDO:
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Login de Cliente", "Bem-vindo, " + clienteAutenticado.getEmail() + "!");
            abrirTela(event, "/com.gestaoentregas/MenuCliente.fxml", "Menu do Cliente");

        } catch (UIException e) {
            // 3. Login FALHOU: Exibe a mensagem de erro fornecida pelo serviço
            mostrarAlerta(Alert.AlertType.ERROR, "Erro de Login", "Falha na Autenticação", e.getMessage());
        }
    }

    /**
     * Lida com a ação de login do Administrador.
     */
    @FXML
    public void acaoLoginAdmin(ActionEvent event) throws IOException {
        String email = txtUsuario.getText();
        String senha = txtSenha.getText();

        try {
            // 1. Chama o serviço, esperando um usuário do tipo ADMIN
            Usuario adminAutenticado = servicoUsuario.autenticar(email, senha, TipoUsuario.ADMIN);

            // 2. Login BEM-SUCEDIDO:
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Login de Administrador", "Bem-vindo, Admin!");
            abrirTela(event, "/com.gestaoentregas/MenuAdm.fxml", "Menu Inicial ADM");

        } catch (UIException e) {
            // 3. Login FALHOU:
            mostrarAlerta(Alert.AlertType.ERROR, "Erro de Login", "Falha na Autenticação", e.getMessage());
        }
    }

    /**
     * Lida com a ação de login do Motorista.
     */
    @FXML
    public void acaoLoginMotorista(ActionEvent event) throws IOException {
        String email = txtUsuario.getText();
        String senha = txtSenha.getText();

        try {
            Usuario usuarioAutenticado = servicoUsuario.autenticar(email, senha, TipoUsuario.MOTORISTA);

            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Login de Motorista", "Bem-vindo, Motorista!");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.gestaoentregas/MenuMotorista.fxml"));
            loader.setControllerFactory(context::getBean);
            Parent root = loader.load();

            MenuMotoristaController controller = loader.getController();
            controller.setIdMotorista(usuarioAutenticado.getId());

            Stage stageNovo = new Stage();
            stageNovo.setScene(new Scene(root));
            stageNovo.setTitle("Menu Principal Motorista");
            stageNovo.setResizable(false);
            stageNovo.show();

            Stage stageLogin = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageLogin.close();

        } catch (UIException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro de Login", "Falha na Autenticação", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // Para ver erros de FXML no console
        }
    }

    // --- MÉTODOS DE UTILIDADE E CADASTRO ---

    /**
     * Método auxiliar para mostrar alertas ao usuário (JavaFX).
     */
    private void mostrarAlerta(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }


    /**
     * Navega para a tela de cadastro de Cliente.
     */
    @FXML
    public void acaoCadastroCliente(ActionEvent event) throws IOException {
        abrirTela(event, "/com.gestaoentregas/TelaCadastroCliente.fxml", "Menu Cadastro Cliente");
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

            stage.setResizable(false);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao abrir a tela: " + fxmlPath);
        }
    }
}