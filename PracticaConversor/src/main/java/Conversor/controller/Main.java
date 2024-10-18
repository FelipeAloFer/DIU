package Conversor.controller;

import Conversor.modelo.Moneda;
import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.MonedaRepository;
import Modelo.repository.impl.MonedaRepositoryImpl;
import javafx.application.Application;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    ConversorController controller = new ConversorController();
    MonedaRepositoryImpl monedaRepositoryImpl = new MonedaRepositoryImpl();
    Moneda moneda;


    protected ArrayList<Moneda> listaMonedas = new ArrayList<>();
    private Stage primaryStage;
    private AnchorPane  conversorView;

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Conversor");
        initConversorView();
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


    public Moneda cambioMonedaVO(MonedaVO dolarVO) throws ExcepcionMoneda {
        ArrayList<MonedaVO> listaMonedasVO = monedaRepositoryImpl.ObtenerListaMonedas();
        dolarVO = listaMonedasVO.get(0);
        String nombre = dolarVO.getNombre();
        FloatProperty multiplicador = new SimpleFloatProperty(dolarVO.getMultiplicador());
        Moneda dolar = new Moneda(nombre, multiplicador);
        return dolar;
    }

    public Moneda obtenerMultiplicador() throws ExcepcionMoneda {
        ArrayList<MonedaVO> listaMonedas = monedaRepositoryImpl.ObtenerListaMonedas();
        Moneda dolar = cambioMonedaVO(listaMonedas.get(0));
        return dolar;
    }
}
