package ejercicios;

import javafx.application.Application;
import javafx.stage.Stage;

public class ContadorDoble2 extends Application {
    Contador contador1 = new Contador();
    Contador contador2 = new Contador();

    public void start(Stage escenarioPrincipal) {
        Stage escenarioPrincipal2 = new Stage();

        contador1.start(escenarioPrincipal);
        contador2.start(escenarioPrincipal2);

        contador1.numPulsaciones.bindBidirectional(contador2.numPulsaciones);
    }
}