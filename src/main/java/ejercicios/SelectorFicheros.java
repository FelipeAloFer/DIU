package ejercicios;

import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class SelectorFicheros extends Application {
    @Override
    public void start(Stage escenarioPrincipal) {
        try {
            VBox raiz = new VBox(10);
            raiz.setPadding(new Insets(20, 20, 20, 20));

            FileChooser fcAbrir = new FileChooser();
            fcAbrir.setTitle("Abrir imagen");
            fcAbrir.setInitialDirectory(
                    new File("/home/usuario/Escritorio/DIU/resources/images/foto-caca")
            );
            fcAbrir.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Todos los ficheros", "*.*"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png")
            );

            Button btAbrir = new Button("Abir imagen");
            btAbrir.setFont(Font.font(30));
            btAbrir.setOnAction((ActionEvent e) -> {
                fcAbrir.showOpenDialog(escenarioPrincipal);
            });

            raiz.getChildren().add(btAbrir);

            Scene escena = new Scene(raiz, 300, 100);
            escenarioPrincipal.setTitle("Selector de ficheros");
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
