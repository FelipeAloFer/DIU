package Conversor.controller;

import Conversor.modelo.ConversorModelo;
import Modelo.ExcepcionMoneda;
import Modelo.repository.impl.MonedaRepositoryImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;


public class Main extends Application {
    private Stage primaryStage;
    private AnchorPane  conversorView;

    MonedaRepositoryImpl monedaRepositoryImpl = new MonedaRepositoryImpl();
    ConversorModelo modelo = new ConversorModelo();

    public void start(Stage primaryStage) throws ExcepcionMoneda {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/Conversor/ConversorView.fxml"));
            conversorView = (AnchorPane) loader.load();

            Scene scene = new Scene(conversorView);
            primaryStage.setScene(scene);
            primaryStage.show();
            this.primaryStage = primaryStage;
            this.primaryStage.setTitle("Conversor");
            modelo.setConversorModelo(monedaRepositoryImpl);
            ConversorController controller = loader.getController();
            controller.setController(modelo);
        } catch (ExcepcionMoneda e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("No se pudo conectar con la base de datos");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
