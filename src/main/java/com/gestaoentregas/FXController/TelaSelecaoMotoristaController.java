package com.gestaoentregas.FXController;

import com.gestaoentregas.dados.beans.VeiculoMotorista;
import com.gestaoentregas.dados.beans.entrega.Entrega;
import com.gestaoentregas.dados.beans.entrega.Rota;
import com.gestaoentregas.dados.beans.motorista.Motorista;
import com.gestaoentregas.negocio.ServicoEntrega;
import com.gestaoentregas.negocio.ServicoUsuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.gestaoentregas.negocio.ServicoMotorista;
import com.gestaoentregas.negocio.ServicoRota;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


@Component
public class TelaSelecaoMotoristaController implements Initializable {

    private final ServicoUsuario servicoUsuario;
    @FXML private TableView<Motorista> tabelaMotoristas;
    @FXML private TableColumn<Motorista, String> colunaNomeMotorista;
    @FXML private TableColumn<Motorista, String> colunaCpfMotorista;

    @FXML private Label lblIdRota;

    @FXML private Button btnFinalizar;
    @FXML private Button btnCancelar;

    private Rota rotaAtual;

    private final ServicoRota servicoRota;
    private final ServicoMotorista servicoMotorista;

    private final ObservableList<Motorista> listaMotorista = FXCollections.observableArrayList();

    public TelaSelecaoMotoristaController(ServicoRota servicoRota, ServicoMotorista servicoMotorista, ServicoUsuario servicoUsuario) {
        this.servicoRota = servicoRota;
        this.servicoMotorista = servicoMotorista;
        this.servicoUsuario = servicoUsuario;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarTabela();
        carregarMotoristas();
    }

    private void configurarTabela() {
        colunaNomeMotorista.setCellValueFactory(new PropertyValueFactory<>("nomeMotorista"));
        colunaCpfMotorista.setCellValueFactory(new PropertyValueFactory<>("cpfMotorista"));
    }

    private void carregarMotoristas() {
        listaMotorista.addAll(servicoMotorista.listarMotoristas());
        tabelaMotoristas.setItems(listaMotorista);
    }

    /**
     * IMPORTANTE: Este método deve ser chamado pela tela anterior (ListaDePedidosController)
     * logo após carregar o FXMLLoader, para passar a rota que está sendo criada.
     */
    public void initData(Rota rota) {
        this.rotaAtual = rota;

        if (rota != null) {
            lblIdRota.setText(String.valueOf(rota.getIdRota()));
        }
    }

    @FXML
    void acaoFinalizarVinculo(ActionEvent event) {
        Motorista motoristaSelecionado = tabelaMotoristas.getSelectionModel().getSelectedItem();

        if (motoristaSelecionado == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atenção");
            alert.setHeaderText("Nenhum motorista selecionado");
            alert.setContentText("Por favor, selecione um motorista na lista à esquerda.");
            alert.showAndWait();
            return;
        }

        try {
            if (rotaAtual != null) {
                if (rotaAtual.getVeiculoMotoristaRota() == null) {
                    rotaAtual.setVeiculoMotoristaRota(new VeiculoMotorista());
                }
                rotaAtual.getVeiculoMotoristaRota().setMotoristaEntrega(motoristaSelecionado);
                servicoRota.atualizarRota(rotaAtual);

                System.out.println("Rota " + rotaAtual.getIdRota() + " vinculada ao motorista " + motoristaSelecionado.getNomeMotorista());

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Vinculação completa");
                alert.setHeaderText("Motorista vinculado com sucesso.");
                alert.showAndWait();

                fecharJanela(event);
            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            System.out.println("Erro ao vincular rota: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void acaoCancelar(ActionEvent event) {
        fecharJanela(event);
    }

    private void fecharJanela(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
