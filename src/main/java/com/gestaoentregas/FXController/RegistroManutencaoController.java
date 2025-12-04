package com.gestaoentregas.FXController;

import com.gestaoentregas.dados.beans.Usuario;
import com.gestaoentregas.dados.beans.motorista.Motorista;
import com.gestaoentregas.dados.beans.veiculo.Veiculo;
import com.gestaoentregas.negocio.ServicoManutencao;
import com.gestaoentregas.negocio.ServicoMotorista;
import com.gestaoentregas.negocio.ServicoUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component // Use @Component, não @Controller
public class RegistroManutencaoController {

    @FXML private TextField txtPlaca;
    @FXML private TextField txtMotivo;
    @FXML private DatePicker dpDataInicio;
    @FXML private DatePicker dpDataFim;

    private final ServicoManutencao servicoManutencao;
    private final ServicoMotorista servicoMotorista;
    private final ServicoUsuario servicoUsuario;

    private int idMotoristaAtual;
    private Veiculo veiculoDoMotorista; // Guarda o veículo encontrado

    public RegistroManutencaoController(ServicoManutencao servicoManutencao, ServicoMotorista servicoMotorista, ServicoUsuario servicoUsuario) {
        this.servicoManutencao = servicoManutencao;
        this.servicoMotorista = servicoMotorista;
        this.servicoUsuario = servicoUsuario;
    }

    // --- CORREÇÃO: Preenche a placa automaticamente ao receber o ID ---
    public void setIdMotorista(int id) {
        this.idMotoristaAtual = id;
        try {
            Usuario u = servicoUsuario.buscarUsuario(id);
            if (u instanceof Motorista) {
                Motorista m = (Motorista) u;
                if (m.getVeiculoMotorista() != null) {
                    this.veiculoDoMotorista = m.getVeiculoMotorista(); // Salva para usar depois

                    // Preenche a tela e trava o campo
                    txtPlaca.setText(this.veiculoDoMotorista.getPlacaVeiculo()); // Ajuste getPlacaVeiculo conforme seu bean
                    txtPlaca.setEditable(false);
                } else {
                    mostrarAlerta("Aviso", "Motorista não possui veículo vinculado. Cadastre um veículo primeiro.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleRegistrarManutencao(ActionEvent event) {
        try {
            String motivo = txtMotivo.getText().trim();
            LocalDate inicio = dpDataInicio.getValue();
            LocalDate fim = dpDataFim.getValue();

            // 1. Validações
            if (motivo.isEmpty() || inicio == null || fim == null) {
                mostrarAlerta("Campos Vazios", "Preencha motivo e datas.");
                return;
            }
            if (fim.isBefore(inicio)) {
                mostrarAlerta("Data Inválida", "Data fim não pode ser antes do início.");
                return;
            }

            // 2. Verifica se temos o veículo
            if (this.veiculoDoMotorista == null) {
                mostrarAlerta("Erro", "Nenhum veículo vinculado a este motorista.");
                return;
            }

            // 3. Salva a Manutenção usando o ID do veículo recuperado
            // Assumindo que seu método aceita (String idVeiculo, ...)
            this.servicoManutencao.registrarManutencao(
                    this.veiculoDoMotorista.getIdVeiculo(),
                    motivo,
                    inicio,
                    fim
            );

            mostrarMensagemSucesso("Sucesso", "Manutenção registrada!");
            limparCampos();

        } catch (Exception e) {
            mostrarAlerta("Erro", "Falha ao registrar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void limparCampos() {
        txtMotivo.clear();
        dpDataInicio.setValue(null);
        dpDataFim.setValue(null);
    }

    private void mostrarAlerta(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(titulo);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void mostrarMensagemSucesso(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText(titulo);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}