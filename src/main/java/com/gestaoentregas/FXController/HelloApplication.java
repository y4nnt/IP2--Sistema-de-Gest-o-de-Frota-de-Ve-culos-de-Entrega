package com.gestaoentregas.FXController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com.gestaoentregas/MapaView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1250, 800);
        stage.setTitle("Mapa");
        stage.setScene(scene);
        stage.show();
    }
}
