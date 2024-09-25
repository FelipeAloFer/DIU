package ejercicios;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BarrasIndicadoresProgreso extends Application {

    @Override
    public void start(Stage escenarioPrincipal) {
        try {
            VBox raiz = new VBox();
            raiz.setPadding(new Insets(40));
            raiz.setSpacing(10);

            HBox hbProgreso1 = new HBox(100);
            HBox hbProgreso2 = new HBox(100);
            HBox hbProgreso3 = new HBox(100);
            HBox hbProgreso4 = new HBox(100);
            hbProgreso1.setAlignment(Pos.CENTER);
            hbProgreso2.setAlignment(Pos.CENTER);
            hbProgreso3.setAlignment(Pos.CENTER);
            hbProgreso4.setAlignment(Pos.CENTER);

            Label lb1 = new Label("-1");
            Label lb2 = new Label("0");
            Label lb3 = new Label("0.75");
            Label lb4 = new Label("1");

            ProgressBar pb1 = new ProgressBar(-1);
            ProgressBar pb2 = new ProgressBar(0);
            ProgressBar pb3 = new ProgressBar(0.75);
            ProgressBar pb4 = new ProgressBar(1);

            ProgressIndicator pi1 = new ProgressIndicator(-1);
            pi1.setPrefWidth(30);
            ProgressIndicator pi2 = new ProgressIndicator(0);
            ProgressIndicator pi3 = new ProgressIndicator(0.75);
            ProgressIndicator pi4 = new ProgressIndicator(1);

            hbProgreso1.getChildren().addAll(lb1, pb1, pi1);
            hbProgreso2.getChildren().addAll(lb2, pb2, pi2);
            hbProgreso3.getChildren().addAll(lb3, pb3, pi3);
            hbProgreso4.getChildren().addAll(lb4, pb4, pi4);

            raiz.getChildren().addAll(hbProgreso1, hbProgreso2, hbProgreso3, hbProgreso4);

            Scene escena = new Scene(raiz, 460, 500);
            escenarioPrincipal.setTitle("Progresos");
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
