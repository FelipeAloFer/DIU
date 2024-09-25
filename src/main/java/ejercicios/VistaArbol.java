package ejercicios;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VistaArbol extends Application {

    @Override
    public void start(Stage escenarioPrincipal) {
        try {
            VBox raiz = new VBox();
            raiz.setPadding(new Insets(40));
            raiz.setSpacing(10);

            Label lbPersonajes = new Label("Personajes:");
            TreeView<String> tvPersonajes = new TreeView<String>();
            TreeItem<String> tiRaiz = new TreeItem<> ("Personajes");
            tiRaiz.setExpanded(true);
            TreeItem<String> tiMasculinos = new TreeItem<> ("Masculinos");
            tiRaiz.getChildren().add(tiMasculinos);
            TreeItem<String> ti1 = new TreeItem<> ("Pepito Grillo");
            tiMasculinos.getChildren().add(ti1);
            TreeItem<String> ti2 = new TreeItem<> ("Bob Esponja");
            tiMasculinos.getChildren().add(ti2);
            TreeItem<String> ti3 = new TreeItem<> ("Juan Sin Miedo");
            tiMasculinos.getChildren().add(ti3);
            TreeItem<String> ti4 = new TreeItem<> ("Perico De Los Palotes");
            tiMasculinos.getChildren().add(ti4);
            TreeItem<String> tiFemeninos = new TreeItem<> ("Femeninos");
            tiRaiz.getChildren().add(tiFemeninos);
            TreeItem<String> ti5 = new TreeItem<> ("Juana La Loca");
            tiFemeninos.getChildren().add(ti5);
            tvPersonajes = new TreeView<String> (tiRaiz);

            tvPersonajes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            raiz.getChildren().addAll(lbPersonajes, tvPersonajes);

            Scene escena = new Scene(raiz, 300, 280);
            escenarioPrincipal.setTitle("Vista de árbol");
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
