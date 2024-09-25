package ejercicios;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VistaTablaArbol extends Application {

    public static class Personaje {
        private String nombre;
        private String apellidos;

        private Personaje(String nombre, String apellidos) {
            this.nombre = nombre;
            this.apellidos = apellidos;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellidos() {
            return apellidos;
        }

        public void setApellidos(String apellidos) {
            this.apellidos = apellidos;
        }

    }

    private TreeTableView<Personaje> ttvPersonajes;
    final ObservableList<Personaje> masculinos = FXCollections.observableArrayList(
            new Personaje("Pepito", "Grillo"),
            new Personaje("Bob", "Esponja"),
            new Personaje("Juan", "Sin Miedo"),
            new Personaje("Perico", "De Los Palotes"));
    final ObservableList<Personaje> femeninos = FXCollections.observableArrayList(
            new Personaje("Juana", "La Loca"));

    TreeTableColumn<Personaje, String> columnaNombre = new TreeTableColumn<Personaje, String>("Nombre");

    TreeTableColumn<Personaje, String> columnaApellidos = new TreeTableColumn<Personaje, String>("Apellidos");

    @Override
    public void start(Stage escenarioPrincipal) {
        try {
            VBox raiz = new VBox();
            raiz.setPadding(new Insets(40));
            raiz.setSpacing(10);

            Label lbPersonajes = new Label("Personajes:");

            //Creo los diferentes elementos del árbol
            TreeItem<Personaje> tiRaiz = new TreeItem<Personaje>(new Personaje("Personajes", ""));
            TreeItem<Personaje> tiMasculinos = new TreeItem<Personaje>(new Personaje("Masculinos", ""));
            for (Personaje masculino : masculinos) {
                tiMasculinos.getChildren().add(new TreeItem<Personaje>(masculino));
            }
            TreeItem<Personaje> tiFemeninos = new TreeItem<Personaje>(new Personaje("Femeninos", ""));
            for (Personaje femenino : femeninos) {
                tiFemeninos.getChildren().add(new TreeItem<Personaje>(femenino));
            }
            tiRaiz.getChildren().add(tiMasculinos);
            tiRaiz.getChildren().add(tiFemeninos);
            tiRaiz.setExpanded(true);
            tiMasculinos.setExpanded(true);
            tiFemeninos.setExpanded(true);

            //Creo las columnas y defino los contenidos de las celdas
            columnaNombre.setMinWidth(120);
            columnaNombre.setCellValueFactory((
                    TreeTableColumn.CellDataFeatures<Personaje, String> parametro) ->
                    new ReadOnlyStringWrapper(parametro.getValue().getValue().getNombre()));
            columnaApellidos.setMinWidth(150);
            columnaApellidos.setCellValueFactory((
                    TreeTableColumn.CellDataFeatures<Personaje, String> parametro) ->
                    new ReadOnlyStringWrapper(parametro.getValue().getValue().getApellidos()));

            //Creo la vista de tabla en árbol y le añado las columnas
            ttvPersonajes = new TreeTableView<Personaje>(tiRaiz);
            ttvPersonajes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            ttvPersonajes.getColumns().add(columnaNombre);
            ttvPersonajes.getColumns().add(columnaApellidos);

            raiz.getChildren().addAll(lbPersonajes, ttvPersonajes);

            Scene escena = new Scene(raiz, 320, 350);
            escenarioPrincipal.setTitle("Vista de tabla en árbol");
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