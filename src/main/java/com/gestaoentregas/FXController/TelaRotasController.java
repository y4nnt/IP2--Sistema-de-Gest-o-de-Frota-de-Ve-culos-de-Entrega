package com.gestaoentregas.FXController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TelaRotasController {

    @FXML
    private ListView<?> listViewRotas; // Exemplo de injeção da lista

    @FXML
    void acaoBotaoVoltar(ActionEvent event) throws IOException {
        // Lógica padrão para voltar para a tela anterior
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    // Você pode usar um método initialize() se precisar carregar dados ao abrir a tela
    @FXML
    public void initialize() {
        System.out.println("Tela de rotas carregada!");
    }
}