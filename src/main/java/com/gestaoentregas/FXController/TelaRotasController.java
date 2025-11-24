package com.gestaoentregas.FXController;

import com.gestaoentregas.dados.beans.entrega.Rota;
import com.gestaoentregas.excecoes.RCException;
import com.gestaoentregas.negocio.ServicoRota;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TelaRotasController {

    private Rota rota;
    private final ServicoRota servicoRota;

    public TelaRotasController(ServicoRota servicoRota) {
        this.servicoRota = servicoRota;
    }

    public void setRota(Rota rota) {
        this.rota = rota;
    }

    @FXML
    private ListView<String> listViewPontosParada; // Exemplo de injeção da lista
    @FXML
    private Label lblOrigem;

    private ObservableList<String> listaPontosParada;


    @FXML
    void acaoBotaoVoltar(ActionEvent event) throws IOException {
        // Lógica padrão para voltar para a tela anterior
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void acaoBotaoFinalizar() throws RCException {
        rota.setIdRota(0);
        if (servicoRota.listarRotas().isEmpty()) {
            rota.setIdRota(1);
        } else {
            rota.setIdRota(servicoRota.listarRotas().getLast().getIdRota() + 1);
        }
        servicoRota.cadastrarRota(this.rota);
        System.out.println("Rota cadastrada com sucesso");
        System.out.println(servicoRota.listarRotas());
    }


    @FXML
    public void initialize() {
        System.out.println("Tela de rotas carregada!");

        if (this.rota != null){
            lblOrigem.setText(this.rota.getOrigemRota());
            this.listaPontosParada = FXCollections.observableArrayList(rota.getPontosParada());
        }

        listViewPontosParada.setItems(listaPontosParada);
    }
}