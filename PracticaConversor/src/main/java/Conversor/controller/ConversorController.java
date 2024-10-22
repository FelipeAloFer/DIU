package Conversor.controller;

import Conversor.modelo.ConversorModelo;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ConversorController {
    ConversorModelo modelo;
    @FXML
    protected TextField cuadroEuros;

    @FXML
    protected TextField cuadroResultado;

    @FXML
    public void accionarConversion(KeyEvent event) {
        if (event.getCode() == javafx.scene.input.KeyCode.ENTER) {
            float resultado = modelo.conversor(Float.valueOf(cuadroEuros.getText()));
            if (modelo.conversor(Float.valueOf(cuadroEuros.getText())) == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("La base de datos no esta conectada");
            }
            cuadroResultado.setText(String.valueOf(resultado));
        }
    }

    public void setController(ConversorModelo modelo) {
        this.modelo = modelo;
    }
}
