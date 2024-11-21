package PracticaEx.controller;

import PracticaEx.modelo.ArticuloModelo;
import PracticaEx.modelo.ExcepcionArticulo;
import PracticaEx.modelo.repository.impl.ArticuloRepositoryImpl;
import PracticaEx.util.Articulo;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static ObservableList<Articulo> listaArticulos = FXCollections.observableArrayList();

    private static Stage primaryStage;
    private BorderPane rootLayout;
    private ArticuloModelo articuloModelo;
    public IntegerProperty numeroProductosDisponibles;

    // Propiedad para la barra de progreso
    private final SimpleDoubleProperty progressProperty = new SimpleDoubleProperty(0);

    public Main() {
        articuloModelo = new ArticuloModelo();
        ArticuloRepositoryImpl articuloRepositoryImpl = new ArticuloRepositoryImpl();
        try {
            articuloModelo.setArticuloRepository(articuloRepositoryImpl);
            listaArticulos.addAll(articuloModelo.setArticulos());
            updateProgress();

            listaArticulos.addListener((ListChangeListener<Articulo>) change -> {
                while (change.next()) {
                    updateProgress();
                }
            });

            numeroProductosDisponibles = new SimpleIntegerProperty();

            numeroProductosDisponibles.bind(Bindings.createIntegerBinding(
                    () -> 50 - listaArticulos.size(),
                    listaArticulos
            ));

        } catch (ExcepcionArticulo e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText("No se ha conectar a la base de datos para la agenda");
            alert.setContentText("Por favor, inicie el servidor con la base de datos");
            alert.showAndWait();
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Articulo> getPersonData() {
        return listaArticulos;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Catalogo");
        initRootLayout();
        showCatalogo();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/PracticaEx/VistaRaiz.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCatalogo() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/PracticaEx/VistaArticulo.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            rootLayout.setCenter(personOverview);

            ArticuloController controller = loader.getController();
            controller.setMain(this);
            controller.setCatalogoModel(articuloModelo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showPersonEditDialog(Articulo articulo) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/PracticaEx/VistaDialogoArticulo.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Artículo");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            DialogoArticuloController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setArticulo(articulo);
            controller.setCatalogoModel(articuloModelo);
            controller.setTotalProductos();

            controller.bindVariable(numeroProductosDisponibles);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void updateProgress() {
        double progress = (double) listaArticulos.size() / 50;
        progressProperty.set(progress);
    }

    public static void main(String[] args) {
        launch(args);
    }
}