package ejercicios;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Contador extends Application {
    Button btMas, btMenos, btCero;
    Label numero;

    @Override
    public void start(Stage escenarioPrincipal) {
        try {
            VBox contenedorNumero = new VBox();
            contenedorNumero.setAlignment(Pos.CENTER);

            numero = new Label();
            numero.setText("0");
            numero.setAlignment(Pos.CENTER);
            numero.setFont(Font.font("Ani", 30));
            numero.getStyleClass().add("numero");

            HBox raiz = new HBox();
            raiz.setPadding(new Insets(20, 20, 20, 20));
            raiz.setSpacing(10);
            raiz.setAlignment(Pos.CENTER);

            btMas = new Button();
            btMenos = new Button();
            btCero = new Button();

            btMas.setText("+");
            btMas.setTextAlignment(TextAlignment.CENTER);
            btMas.getStyleClass().add("boton");
            btMas.setOnAction(e -> botonPulsado(1));


            btMenos.setText("-");
            btMenos.setTextAlignment(TextAlignment.CENTER);
            btMenos.getStyleClass().add("boton");
            btMenos.setOnAction(e -> botonPulsado(-1));


            btCero.setText("0");
            btCero.setTextAlignment(TextAlignment.CENTER);
            btCero.getStyleClass().add("boton");
            btCero.setOnAction(e -> botonPulsado(0));

            raiz.getChildren().addAll(btMenos, btCero, btMas);
            raiz.getStyleClass().add("raiz");

            contenedorNumero.getChildren().addAll(raiz, numero);
            contenedorNumero.getStyleClass().add("contenedor");

            Scene escena = new Scene(contenedorNumero, 800, 400);
            escena.getStylesheets().add(getClass().getResource("/styles/contador.css").toExternalForm());
            escenarioPrincipal.setTitle("Contador");
            escenarioPrincipal.setScene(escena);
            escenarioPrincipal.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }



    private int numPulsaciones = 0;

    private void botonPulsado(int opcion) {
        if (opcion == 1) {
            numero.setText(String.valueOf(++numPulsaciones));
        } else if  (opcion == -1) {
            numero.setText(String.valueOf(--numPulsaciones));
        } else {
            numPulsaciones = 0;
            numero.setText("0");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}