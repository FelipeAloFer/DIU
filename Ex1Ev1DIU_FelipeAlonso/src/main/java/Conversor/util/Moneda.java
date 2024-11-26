package Conversor.util;

import javafx.beans.property.*;

public class Moneda {
    IntegerProperty codigo;
    StringProperty nombre;
    FloatProperty multiplicador;

    public Moneda () {
    }

    public Moneda(Integer codigo, String nombre, Float multiplicador) {
        this.codigo = new SimpleIntegerProperty(codigo);
        this.nombre = new SimpleStringProperty(nombre);
        this.multiplicador = new SimpleFloatProperty(multiplicador);
    }

    public int getCodigo() {
        return codigo.get();
    }

    public IntegerProperty codigoProperty() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo.set(codigo);
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public float getMultiplicador() {
        return multiplicador.get();
    }

    public FloatProperty multiplicadorProperty() {
        return multiplicador;
    }

    public void setMultiplicador(float multiplicador) {
        this.multiplicador.set(multiplicador);
    }

    @Override
    public String toString() {
        return "Moneda{" +
                "codigo=" + codigo +
                ", nombre=" + nombre +
                ", multiplicador=" + multiplicador +
                '}';
    }
}
