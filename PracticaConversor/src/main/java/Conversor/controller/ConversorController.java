package Conversor.controller;

import Conversor.modelo.Moneda;
import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class ConversorController {
    Moneda moneda;
    Main main;

    @FXML
    protected TextField cuadroEuros;

    @FXML
    protected TextField cuadroResultado;

    @FXML
    public void conversion(Moneda dolar) throws ExcepcionMoneda {
        FloatProperty multiplicador = dolar.getMultiplicador();

        IntegerProperty euros = new SimpleIntegerProperty(Integer.parseInt(cuadroEuros.getText()));

        IntegerProperty resultado = new SimpleIntegerProperty((int) (euros.get() * multiplicador.get()));

        cuadroResultado.setText(resultado.toString());
    }
}
