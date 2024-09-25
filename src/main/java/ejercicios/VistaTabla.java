package ejercicios;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VistaTabla extends Application {

    public static class Personaje {
        private final SimpleStringProperty nombre;
        private final SimpleStringProperty apellidos;

        private Personaje(String nombre, String apellidos) {
            this.nombre = new SimpleStringProperty(nombre);
            this.apellidos = new SimpleStringProperty(apellidos);
        }

        public String getNombre() {
            return nombre.get();
        }

        public void setNombre(String nombre) {
            this.nombre.set(nombre);
        }

        public String getApellidos() {
            return apellidos.get();
        }

        public void setApellidos(String apellidos) {
            this.apellidos.set(apellidos);
        }

    }

    private TableView<Personaje> tvPersonajes;
    final ObservableList<Personaje> personajes = FXCollections.observableArrayList(
            new Personaje("Pepito", "Grillo"),
            new Personaje("Bob", "Esponja"),
            new Personaje("Juan", "Sin Miedo"),
            new Personaje("Perico", "De Los Palotes"),
            new Personaje("Juana", "La Loca"),
            new Personaje("Guillermo", "El Feo"));

    TableColumn<Personaje, String> columnaNombre = new TableColumn<Personaje, String>("Nombre");

    TableColumn<Personaje, String> columnaApellidos = new TableColumn<Personaje, String>("Apellidos");

    @Override
    public void start(Stage escenarioPrincipal) {
        try {
            VBox raiz = new VBox();
            raiz.setPadding(new Insets(40));
            raiz.setSpacing(10);

            Label lbPersonajes = new Label("Personajes:");
            tvPersonajes = new TableView<Personaje>();
            tvPersonajes.getColumns().add(columnaNombre);
            tvPersonajes.getColumns().add(columnaApellidos);
            tvPersonajes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            columnaNombre.setMinWidth(100);
            columnaNombre.setCellValueFactory(new PropertyValueFactory<Personaje, String>("Nombre"));
            columnaApellidos.setMinWidth(100);
            columnaApellidos.setCellValueFactory(new PropertyValueFactory<Personaje, String>("Apellidos"));

            tvPersonajes.setItems(personajes);

            raiz.getChildren().addAll(lbPersonajes, tvPersonajes);

            Scene escena = new Scene(raiz, 300, 250);
            escenarioPrincipal.setTitle("Vista de tabla");
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