package ejercicios;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SelectorFecha extends Application {

    @Override
    public void start(Stage escenarioPrincipal) {
        try {
            HBox raiz = new HBox();
            raiz.setPadding(new Insets(40));
            raiz.setSpacing(10);
            raiz.setAlignment(Pos.CENTER_LEFT);

            Label lbFechaNacimiento;
            lbFechaNacimiento = new Label("Fecha de nacimiento:");
            lbFechaNacimiento.setFont(Font.font(15));

            DatePicker dpFechaNacimiento = new DatePicker();

            raiz.getChildren().addAll(lbFechaNacimiento, dpFechaNacimiento);

            Scene escena = new Scene(raiz, 450, 120);
            escenarioPrincipal.setTitle("Selector de fecha");
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