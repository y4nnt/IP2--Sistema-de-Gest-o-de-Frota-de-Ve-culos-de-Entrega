package com.gestaoentregas;

import com.gestaoentregas.FXController.ListaDePedidosController;
import com.gestaoentregas.FXController.MenuMotoristaController;
import com.gestaoentregas.FXController.MenuPrincipalController;
import com.gestaoentregas.FXController.MotoristaPrincipalController;
import com.gestaoentregas.excecoes.ECException;
import com.gestaoentregas.excecoes.PCException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class DeliveryApplication extends Application {

    private static ConfigurableApplicationContext springContext;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        springContext = SpringApplication.run(DeliveryApplication.class);
    }

    @Override
    public void start(Stage stage) throws IOException, ECException, PCException {
        FXMLLoader fxmlLoader = new FXMLLoader(DeliveryApplication.class.getResource("/com.gestaoentregas/MenuAdm.fxml"));

        fxmlLoader.setControllerFactory(springContext::getBean);

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Menu Inicial ADM");
        stage.setResizable(false);

        MenuPrincipalController controller = fxmlLoader.getController();

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        springContext.close();
    }

}
