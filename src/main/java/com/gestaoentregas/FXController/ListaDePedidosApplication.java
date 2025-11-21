package com.gestaoentregas.FXController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ListaDePedidosApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ListaDePedidosApplication.class.getResource("/com.gestaoentregas/ListaDePedidosView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Motorista Aba");

        stage.setScene(scene);
        stage.show();
    }
}
