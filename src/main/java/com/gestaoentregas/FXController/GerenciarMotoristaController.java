package com.gestaoentregas.FXController;

import com.gestaoentregas.dados.beans.motorista.Motorista;
import com.gestaoentregas.dados.beans.veiculo.Veiculo;
import com.gestaoentregas.excecoes.MIException;
import com.gestaoentregas.negocio.ServicoMotorista;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;

import com.gestaoentregas.negocio.ServicoUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class GerenciarMotoristaController implements Initializable {

    // --- Tabela Principal (Esquerda) ---
    @FXML
    private TableView<Motorista> tabelaMotoristas;
    @FXML
    private TableColumn<Motorista, Long> colunaID;
    @FXML
    private TableColumn<Motorista, String> colunaNome;

    // --- Painel de Detalhes (Direita) ---
    @FXML
    private Pane painelDetalhes;

    // Labels de Informações Pessoais
    @FXML
    private Label lblDetalhesID;
    @FXML
    private Label lblDetalhesNome;
    @FXML
    private Label lblDetalhesCPF;
    @FXML
    private Label lblDetalhesCNH;
    @FXML
    private Label lblDetalhesTelefone;
    @FXML
    private Label lblDetalhesVeiculo;

    // --- Botões ---
    @FXML
    private Button btnNovoMotorista;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnVoltar;

    private Motorista motoristaSelecionado;
    private final ServicoMotorista servicoMotorista;
    private final ServicoUsuario servicoUsuario;
    private final ApplicationContext context;
    private final ObservableList<Motorista> listaMotoristas = FXCollections.observableArrayList();

    public GerenciarMotoristaController(ServicoMotorista servicoMotorista, ServicoUsuario servicoUsuario, ApplicationContext context) {
        this.servicoMotorista = servicoMotorista;
        this.servicoUsuario = servicoUsuario;
        this.context = context;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listaMotoristas.clear();

        // Esconde painel de detalhes inicialmente
        painelDetalhes.setVisible(false);
        painelDetalhes.setManaged(false);

        // Configuração das Colunas
        colunaID.setCellValueFactory(new PropertyValueFactory<>("idMotorista"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nomeMotorista"));

        // Carrega dados
        listaMotoristas.addAll(servicoMotorista.listarMotoristas());
        tabelaMotoristas.setItems(listaMotoristas);

        // Listener de Seleção
        tabelaMotoristas.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        mostrarDetalhes(newSelection);
                    } else {
                        painelDetalhes.setVisible(false);
                        painelDetalhes.setManaged(false);
                    }
                }
        );
    }

    private void mostrarDetalhes(Motorista motorista) {
        this.motoristaSelecionado = motorista;

        lblDetalhesID.setText(String.valueOf(motorista.getId()));
        lblDetalhesNome.setText(motorista.getNomeMotorista());

        // Exemplo de getters (ajuste conforme sua classe Bean real)
        lblDetalhesCPF.setText(motorista.getCpfMotorista());
        lblDetalhesCNH.setText(motorista.getCnhMotorista());
        lblDetalhesTelefone.setText(motorista.getTelefoneMotorista());

        Veiculo veiculo = motorista.getVeiculoMotorista();

        if (veiculo != null) {
            // Se tiver veículo, mostra Modelo e Placa
            lblDetalhesVeiculo.setText(veiculo.getModeloVeiculo() + " (" + veiculo.getPlacaVeiculo() + ")");
        } else {
            // Se for null, avisa que está a pé
            lblDetalhesVeiculo.setText("Sem veículo");
        }

        // Mostra o painel
        painelDetalhes.setVisible(true);
        painelDetalhes.setManaged(true);
    }

    @FXML
    void acaoCadastrarNovoMotorista(ActionEvent event) {
        abrirFormularioMotorista(null, event);
    }


    @FXML
    void acaoExcluirMotorista(ActionEvent event) throws MIException {
        if (motoristaSelecionado != null) {
            servicoUsuario.removerUsuario(motoristaSelecionado.getId());
            listaMotoristas.remove(motoristaSelecionado);

            painelDetalhes.setVisible(false);
            painelDetalhes.setManaged(false);
            tabelaMotoristas.getSelectionModel().clearSelection();
        }
    }

    @FXML
    public void acaoVoltarMenu(ActionEvent event) {
        abrirTela(event, "/com.gestaoentregas/MenuAdm.fxml", "Menu do Adm");
    }

    // Método auxiliar para abrir tela de cadastro (mantido do exemplo anterior)
    private void abrirFormularioMotorista(Motorista motorista, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.gestaoentregas/CadastroMotorista.fxml"));
            loader.setControllerFactory(context::getBean);
            Parent parent = loader.load();

            // Lógica para passar o motorista ao controller de edição, se necessário
            // if (motorista != null) {
            //      FormularioMotoristaController controller = loader.getController();
            //      controller.setMotorista(motorista);
            // }

            Stage stageNovo = new Stage();
            Scene scene = new Scene(parent);
            stageNovo.setScene(scene);
            stageNovo.setTitle(motorista == null ? "Novo Motorista" : "Editar Motorista");
            stageNovo.setResizable(false);
            stageNovo.initModality(javafx.stage.Modality.APPLICATION_MODAL);

            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageNovo.initOwner(stageAtual);

            stageNovo.showAndWait();

            // Refresh na lista
            listaMotoristas.clear();
            listaMotoristas.addAll(servicoMotorista.listarMotoristas());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirTela(ActionEvent event, String fxmlPath, String titulo) {
        try {
            // 1. Carrega o Loader da NOVA tela
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            loader.setControllerFactory(context::getBean);
            Parent root = loader.load();

            // 2. Prepara a nova janela
            Stage stageNovo = new Stage();
            stageNovo.setScene(new Scene(root));
            stageNovo.setTitle(titulo);
            stageNovo.setResizable(false);

            // 3. Mostra a nova janela
            stageNovo.show();

            // 4. FECHA A JANELA ANTIGA (Só chega aqui se a nova abriu sem erros)
            // Pegamos a janela atual através do botão que foi clicado
            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageAtual.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro crítico ao abrir a tela: " + fxmlPath);
            // Aqui você pode mostrar um Alert de erro para o usuário se quiser
        }
    }
}