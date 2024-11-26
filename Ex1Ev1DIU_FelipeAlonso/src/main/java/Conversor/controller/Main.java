package Conversor.controller;

import Conversor.modelo.MonedaModelo;
import Conversor.util.Moneda;
import Modelo.ExcepcionMoneda;
import Modelo.repository.impl.MonedaRepositoryImpl;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
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
    private static ObservableList<Moneda> listaMonedas = FXCollections.observableArrayList();
    private static Stage primaryStage;
    private BorderPane vistaRaiz;
    private MonedaModelo monedaModelo;
    public IntegerProperty numeroMonedasDisponibles;


    public Main() {
        monedaModelo = new MonedaModelo();
        MonedaRepositoryImpl monedaRepositoryImpl = new MonedaRepositoryImpl();

        try {
            monedaModelo.setMonedaRepository(monedaRepositoryImpl);
            listaMonedas.addAll(monedaModelo.setMonedas());
            
        } catch (ExcepcionMoneda e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText("No se ha conectar a la base de datos");
            alert.setContentText("Por favor, inicie el servidor con la base de datos");
            alert.showAndWait();
            throw new RuntimeException(e);        }
    }

    public ObservableList<Moneda> getMonedasData() {
        return listaMonedas;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Conversor");
        initRootLayout();
        showConversor();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/Conversor/VistaRaiz.fxml"));
            vistaRaiz = (BorderPane) loader.load();

            Scene scene = new Scene(vistaRaiz);
            primaryStage.setScene(scene);
            primaryStage.show();

            ConversorController conversorController = loader.getController();
            conversorController.setMain(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showConversor() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/Conversor/VistaMoneda.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            vistaRaiz.setCenter(personOverview);

            MonedaController controller = loader.getController();
            controller.setMain(this);
            controller.setMonedaModelo(monedaModelo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showVentana() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/Conversor/VistaDialogoMoneda.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ventana con foto");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            DialogoMonedaController controller = loader.getController();


            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}