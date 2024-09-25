package ejercicios;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CasillasVerificacion extends Application {

    @Override
    public void start(Stage escenarioPrincipal) {
        try {
            VBox raiz = new VBox();
            raiz.setPadding(new Insets(40));
            raiz.setSpacing(10);

            Label lbElige;
            lbElige = new Label("Elige los extras:");
            lbElige.setFont(Font.font(20));

            CheckBox cbNavegador, cbManosLibres, cbLunas;
            cbNavegador = new CheckBox("Navegador");
            cbManosLibres = new CheckBox("Manos libres");
            cbLunas = new CheckBox("Lunas tintadas");
            cbLunas.setAllowIndeterminate(true);

            VBox.setMargin(cbNavegador, new Insets(0, 0, 0, 30));
            VBox.setMargin(cbManosLibres, new Insets(0, 0, 0, 30));
            VBox.setMargin(cbLunas, new Insets(0, 0, 0, 30));

            raiz.getChildren().addAll(lbElige, cbNavegador, cbManosLibres, cbLunas);

            Scene escena = new Scene(raiz, 350, 200);
            escenarioPrincipal.setTitle("Casillas de verificación");
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
