package Conversor.modelo;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.MonedaRepository;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;

import java.util.ArrayList;

public class Moneda {

    String nombre;
    FloatProperty multiplicador;
    ArrayList<Moneda> listaMonedas = new ArrayList<Moneda>();

    public Moneda(String nombre, FloatProperty multiplicador) {
        this.nombre = nombre;
        this.multiplicador = multiplicador;
    }

//    public void añadirMoneda(Moneda dolar) throws ExcepcionMoneda {
//        listaMonedas.add(dolar);
//    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public FloatProperty getMultiplicador() {
        return this.multiplicador;
    }

    public void setMultiplicador(FloatProperty multiplicador) {
        this.multiplicador = multiplicador;
    }

//    public String toString() {
//        return "MonedaVO{nombre=" + this.nombre + ", multiplicador=" + this.multiplicador + '}';
//    }
}
