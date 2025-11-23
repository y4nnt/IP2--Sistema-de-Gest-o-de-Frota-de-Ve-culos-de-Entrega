package com.gestaoentregas;

import com.gestaoentregas.FXController.ListaDePedidosController;
import com.gestaoentregas.excecoes.ECException;
import com.gestaoentregas.excecoes.PCException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class ListaDePedidosApplication extends Application {

    private static ConfigurableApplicationContext springContext;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        springContext = SpringApplication.run(ListaDePedidosApplication.class);
    }

    @Override
    public void start(Stage stage) throws IOException, ECException, PCException {
        FXMLLoader fxmlLoader = new FXMLLoader(ListaDePedidosApplication.class.getResource("/com.gestaoentregas/ListaDePedidosView.fxml"));

        fxmlLoader.setControllerFactory(springContext::getBean);

        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Motorista Aba");

        ListaDePedidosController controller = fxmlLoader.getController();

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        springContext.close();
    }

}
