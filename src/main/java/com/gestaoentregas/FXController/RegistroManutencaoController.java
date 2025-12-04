package com.gestaoentregas.FXController;

import com.gestaoentregas.negocio.ServicoManutencao;
import com.gestaoentregas.negocio.ServicoMotorista;
import com.gestaoentregas.negocio.ServicoVeiculo;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import com.gestaoentregas.dados.beans.veiculo.Manutencao;
import com.gestaoentregas.dados.repositorios.IRepositorioManutencao;
import com.gestaoentregas.dados.repositorios.RepositorioManutencao;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@Controller
public class RegistroManutencaoController {

    @FXML
    private TextField txtPlaca;

    @FXML
    private TextField txtMotivo;

    @FXML
    private DatePicker dpDataInicio;

    @FXML
    private DatePicker dpDataFim;

    private final ServicoManutencao servicoManutencao;
    private final ServicoMotorista  servicoMotorista;
    private int idMotoristaAtual;

    public RegistroManutencaoController(ServicoManutencao servicoManutencao,  ServicoMotorista servicoMotorista) {
        this.servicoManutencao = servicoManutencao;
        this.servicoMotorista = servicoMotorista;
    }

    public void setIdMotorista(int id) {
        this.idMotoristaAtual = id;
        System.out.println("ID do Motorista recebido: " + this.idMotoristaAtual);
    }

    @FXML
    protected void handleRegistrarManutencao(ActionEvent event) {
        try {
            String placa = txtPlaca.getText().trim();
            String motivo = txtMotivo.getText().trim();
            LocalDate inicio = dpDataInicio.getValue();
            LocalDate fim = dpDataFim.getValue();

            if (placa.isEmpty() || motivo.isEmpty() || inicio == null || fim == null) {
                System.out.println("Erro: Todos os campos devem ser preenchidos.");
                return;
            }

            if (fim.isBefore(inicio)) {
                System.out.println("Erro: A data final não pode ser anterior à data de início.");
                return;
            }


            this.servicoManutencao.registrarManutencao(this.servicoMotorista.buscarMotorista(idMotoristaAtual).getVeiculoMotorista().getIdVeiculo(), motivo, inicio, fim);

            System.out.println("Manutenção registrada com sucesso para o veículo " + placa + ".");
            System.out.println("Motivo: " + motivo + " | Período: " + inicio + " a " + fim);

            limparCampos();

        } catch (Exception e) {
            System.out.println("Erro inesperado ao registrar manutenção: " + e.getMessage());
        }
    }

    private void limparCampos() {
        txtPlaca.clear();
        txtMotivo.clear();
        dpDataInicio.setValue(null);
        dpDataFim.setValue(null);
    }
}