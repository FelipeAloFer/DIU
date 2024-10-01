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

public class ContadorDoble extends Application {

    private Button btMas, btMenos, btCero, btMas2, btMenos2, btCero2;
    private Label numero, numero2;


    @Override
    public void start(Stage escenarioPrincipal) {
        try {
            VBox contenedorNumero = new VBox();
            contenedorNumero.setAlignment(Pos.CENTER);

            VBox contenedorNumero2 = new VBox();
            contenedorNumero2.setAlignment(Pos.CENTER);

            numero = new Label();
            numero.setText("0");
            numero.setAlignment(Pos.CENTER);
            numero.setFont(Font.font("Ani", 30));
            numero.getStyleClass().add("numero");

            numero2= new Label();
            numero2.setText("0");
            numero2.setAlignment(Pos.CENTER);
            numero2.setFont(Font.font("Ani", 30));
            numero2.getStyleClass().add("numero");


            HBox raiz = new HBox();
            raiz.setPadding(new Insets(20, 20, 20, 20));
            raiz.setSpacing(10);
            raiz.setAlignment(Pos.CENTER);

            HBox raiz2 = new HBox();
            raiz2.setPadding(new Insets(20, 20, 20, 20));
            raiz2.setSpacing(10);
            raiz2.setAlignment(Pos.CENTER);

            btMas = new Button();
            btMas2 = new Button();
            btMenos = new Button();
            btMenos2 = new Button();
            btCero = new Button();
            btCero2 = new Button();

            btMas.setText("+");
            btMas.setTextAlignment(TextAlignment.CENTER);
            btMas.getStyleClass().add("boton");
            btMas.setOnAction(e -> botonPulsado(1));

            btMas2.setText("+");
            btMas2.setTextAlignment(TextAlignment.CENTER);
            btMas2.getStyleClass().add("boton");
            btMas2.setOnAction(e -> botonPulsado(1));


            btMenos.setText("-");
            btMenos.setTextAlignment(TextAlignment.CENTER);
            btMenos.getStyleClass().add("boton");
            btMenos.setOnAction(e -> botonPulsado(-1));

            btMenos2.setText("-");
            btMenos2.setTextAlignment(TextAlignment.CENTER);
            btMenos2.getStyleClass().add("boton");
            btMenos2.setOnAction(e -> botonPulsado(-1));


            btCero.setText("0");
            btCero.setTextAlignment(TextAlignment.CENTER);
            btCero.getStyleClass().add("boton");
            btCero.setOnAction(e -> botonPulsado(0));

            btCero2.setText("0");
            btCero2.setTextAlignment(TextAlignment.CENTER);
            btCero2.getStyleClass().add("boton");
            btCero2.setOnAction(e -> botonPulsado(0));

            raiz.getChildren().addAll(btMenos, btCero, btMas);
            raiz.getStyleClass().add("raiz");

            raiz2.getChildren().addAll(btMenos2, btCero2, btMas2);
            raiz2.getStyleClass().add("raiz");

            contenedorNumero.getChildren().addAll(raiz, numero);
            contenedorNumero.getStyleClass().add("contenedor");

            contenedorNumero2.getChildren().addAll(raiz2, numero2);
            contenedorNumero2.getStyleClass().add("contenedor");

            numero.textProperty().bindBidirectional(numero2.textProperty());

            Scene escena = new Scene(contenedorNumero, 800, 400);
            escena.getStylesheets().add(getClass().getResource("/styles/contador.css").toExternalForm());
            escenarioPrincipal.setTitle("Contador");
            escenarioPrincipal.setScene(escena);
            escenarioPrincipal.show();

            Stage escenarioPrincipal2 = new Stage();

            Scene escena2 = new Scene(contenedorNumero2, 800, 400);
            escena2.getStylesheets().add(getClass().getResource("/styles/contador.css").toExternalForm());
            escenarioPrincipal2.setTitle("Contador 2");
            escenarioPrincipal2.setScene(escena2);
            escenarioPrincipal2.show();
        } catch (Exception e) {
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

