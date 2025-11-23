package com.gestaoentregas;

import com.gestaoentregas.FXController.ListaDePedidosController;
import com.gestaoentregas.excecoes.ECException;
import com.gestaoentregas.excecoes.PCException;
import com.gestaoentregas.negocio.ServicoEntrega;
import com.gestaoentregas.negocio.ServicoProduto;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@SpringBootApplication
@Component
public class HelloApplication extends Application {

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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com.gestaoentregas/MapaView.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);

        Scene scene = new Scene(fxmlLoader.load(), 1250, 800);
        stage.setTitle("Mapa");

        ListaDePedidosController controller = fxmlLoader.getController();


        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        springContext.close();
    }

}
