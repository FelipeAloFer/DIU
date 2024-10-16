package Conversor.controller;

import Conversor.modelo.Moneda;
import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.impl.MonedaRepositoryImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    ConversorController controller = new ConversorController();

    private Stage primaryStage;
    private AnchorPane  conversorView;

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Conversor");
        initConversorView();
        try {
            MonedaRepositoryImpl monedarepositoryImpl = new MonedaRepositoryImpl();
            MonedaVO monedaPrueba = new MonedaVO("prueba", 1.2F);
            monedarepositoryImpl.addMoneda(monedaPrueba);
        } catch (ExcepcionMoneda var5) {
            ExcepcionMoneda e = var5;
            System.out.println(e.imprimirMensaje());
        }
    }

    // Initializes the root layout
    public void initConversorView() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/Conversor/ConversorView.fxml"));
            conversorView = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(conversorView);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void conversion() {
//        controller.
//    }
}
