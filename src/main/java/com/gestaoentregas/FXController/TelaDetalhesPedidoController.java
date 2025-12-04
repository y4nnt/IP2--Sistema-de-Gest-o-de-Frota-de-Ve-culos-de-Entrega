package com.gestaoentregas.FXController;

import com.gestaoentregas.dados.beans.entrega.Entrega;
import com.gestaoentregas.dados.beans.entrega.Rota;
import com.gestaoentregas.dados.beans.entrega.StatusEntrega;
import com.gestaoentregas.dados.beans.motorista.Motorista;
import com.gestaoentregas.negocio.ServicoMotorista;
import com.gestaoentregas.negocio.ServicoRota;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Component
public class TelaDetalhesPedidoController implements Initializable {

    private final ServicoRota servicoRota;
    private final ServicoMotorista servicoMotorista;
    private final ApplicationContext context;

    @FXML private Label lblId;
    @FXML private Label lblStatus;
    @FXML private Label lblEndereco;
    @FXML private Label lblObs;
    @FXML private Button btnVerMotorista;

    private Entrega entregaAtual;
    private String emailClienteLogado;

    public TelaDetalhesPedidoController(ServicoRota servicoRota,
                                        ServicoMotorista servicoMotorista,
                                        ApplicationContext context) {
        this.servicoRota = servicoRota;
        this.servicoMotorista = servicoMotorista;
        this.context = context;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setEntrega(Entrega entrega) {
        this.entregaAtual = entrega;
        atualizarUI();
    }

    public void setClienteLogado(String email) {
        this.emailClienteLogado = email;
    }

    private void atualizarUI() {
        if (entregaAtual != null) {
            lblId.setText(entregaAtual.getCodEntrega());
            lblStatus.setText(entregaAtual.getStatusEntrega().toString());
            lblEndereco.setText(entregaAtual.getLocalEntrega());
            lblObs.setText(entregaAtual.getObservacoesEntrega() != null ? entregaAtual.getObservacoesEntrega() : "Nenhuma");

            if (entregaAtual.getStatusEntrega() == StatusEntrega.PENDENTE) {
                btnVerMotorista.setDisable(true);
                btnVerMotorista.setText("Aguardando Atribuição");
            } else {
                btnVerMotorista.setDisable(false);
                btnVerMotorista.setText("Ver Motorista");
            }
        }
    }

    @FXML
    private void handleVerMotorista(ActionEvent event) {
        try {
            Motorista motoristaEncontrado = null;


            ArrayList<Rota> todasRotas = servicoRota.listarRotas();

            if (todasRotas != null) {
                for (Rota r : todasRotas) {
                    if (r.buscarEntregaRota(entregaAtual.getCodEntrega()) != null) {
                        if (r.getVeiculoMotoristaRota() != null) {
                            motoristaEncontrado = r.getVeiculoMotoristaRota().getMotoristaEntrega();
                        }
                        break;
                    }
                }
            }

            if (motoristaEncontrado != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.gestaoentregas/TelaPerfilMotorista.fxml"));
                loader.setControllerFactory(context::getBean);
                Parent root = loader.load();

                TelaPerfilMotoristaController controller = loader.getController();
                controller.setMotorista(motoristaEncontrado);
                controller.setDadosRetorno(emailClienteLogado, entregaAtual);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informação");
                alert.setHeaderText("Motorista não encontrado");
                alert.setContentText("A entrega está em uma rota, mas o motorista ainda não foi confirmado.");
                alert.showAndWait();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleVoltar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.gestaoentregas/TelaPrincipalCliente.fxml"));
            loader.setControllerFactory(context::getBean);
            Parent root = loader.load();

            TelaPrincipalClienteController controller = loader.getController();
            controller.setClienteLogado(emailClienteLogado);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}