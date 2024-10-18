package Conversor.modelo;

public class Moneda {
    String nombre;
    Float multiplicador;

    public Moneda(String nombre, Float multiplicador) {
        this.nombre = nombre;
        this.multiplicador = multiplicador;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getMultiplicador() {
        return this.multiplicador;
    }

    public void setMultiplicador(Float multiplicador) {
        this.multiplicador = multiplicador;
    }

    public String toString() {
        return "MonedaVO{nombre=" + this.nombre + ", multiplicador=" + this.multiplicador + '}';
    }
}
