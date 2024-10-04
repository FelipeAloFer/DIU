package ejercicios;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SelectorColor extends Application {

    @Override
    public void start(Stage escenarioPrincipal) {
        try {
            HBox raiz = new HBox();
            raiz.setPadding(new Insets(40));
            raiz.setSpacing(10);
            raiz.setAlignment(Pos.CENTER_LEFT);

            Label lbElige;
            lbElige = new Label("Elige el color:");
            lbElige.setFont(Font.font(20));

            ColorPicker cpColor = new ColorPicker(Color.BLUE);

            raiz.getChildren().addAll(lbElige, cpColor);

            Scene escena = new Scene(raiz, 400, 120);
            escenarioPrincipal.setTitle("Selector de color");
            escenarioPrincipal.setScene(escena);
            escenarioPrincipal.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

