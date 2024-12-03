package GestionHotel.controller;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import java.io.IOException;

public class TraductorController {

    @FXML
    private WebView webView; // El WebView que está definido en tu archivo FXML

    // Método que carga la página del traductor en el WebView
    public void mostrarTraductor() throws IOException {
        // Obtener el WebEngine del WebView
        WebEngine webEngine = webView.getEngine();

        // Cargar la página de Google Translate
        webEngine.load("https://translate.google.com/");
    }

    // Este método puede ser llamado desde otro controlador o desde el archivo Main
    public void initialize() {
        try {
            mostrarTraductor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

