package com.gestaoentregas.FXController;

import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import org.springframework.stereotype.Component;

import java.net.URL;

@Component
public class MapaController {

    @FXML private WebView mapaWebView;

    // --- CLASSE PONTE AJUSTADA ---
    // 1. Tem que ser PUBLIC
    // 2. É melhor ser STATIC para evitar erros de contexto
    public static class JavaBridge {
        public void processarRota(String origem, String destino, String distancia) {
            System.out.println("=== JAVA RECEBEU ===");
            System.out.println("Origem: " + origem);
            System.out.println("Destino: " + destino);
            System.out.println("Distância: " + distancia);
        }

        public void log(String mensagem) {
            System.out.println("JS Log: " + mensagem);
        }
    }

    @FXML
    public void initialize() {
        WebEngine engine = mapaWebView.getEngine();

        // CORREÇÃO: O nome é apenas setUserAgent
        // Este User Agent finge ser um Chrome no Windows 10. Funciona em 99% dos casos.
        engine.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36");

        // ... resto do seu código (Load Worker, Ponte, Load URL) ...

        // Verifica carregamento
        engine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                try {
                    JSObject window = (JSObject) engine.executeScript("window");
                    window.setMember("appJava", new JavaBridge());
                } catch (Exception e) { e.printStackTrace(); }
            }
        });

        URL url = getClass().getResource("/mapa.html");
        if (url != null) engine.load(url.toExternalForm());
    }
}