package ejercicios;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Esqueleto extends Application {
    @Override
    public void start(Stage escenarioPrincipal) {
        try {
            BorderPane raiz = new BorderPane();

            Scene escena = new Scene(raiz, 400, 400);
            escenarioPrincipal.setTitle("Esqueleto JavaFX");
            escenarioPrincipal.setScene(escena);
            escenarioPrincipal.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}


