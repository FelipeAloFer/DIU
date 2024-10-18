package Conversor.controller;

import Conversor.modelo.ConversorModelo;
import javafx.fxml.FXML;
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
            cuadroResultado.setText(String.valueOf(resultado));
        }
    }

    public void setController(ConversorModelo modelo) {
        this.modelo = modelo;
    }
}
