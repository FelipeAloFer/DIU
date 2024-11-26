package PracticaFS.modelo;

import javafx.beans.property.*;
import javafx.fxml.FXML;

public class Articulo {
    private int codigo;
    @FXML
    private final StringProperty nombre;
    @FXML
    private final StringProperty descripcion;
    @FXML
    private final FloatProperty precio;
    @FXML
    private final IntegerProperty stock;

    public Articulo() {
        this(null,0,0);
    }

    public Articulo(String nombre, float precio, int stock) {
        this.codigo=0;
        this.nombre = new SimpleStringProperty(nombre);
        this.descripcion=new SimpleStringProperty("Por defecto");
        this.precio = new SimpleFloatProperty(precio);
        this.stock = new SimpleIntegerProperty(stock);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public String getDescripcion() {
        return descripcion.get();
    }

    public StringProperty descripcionProperty() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public double getPrecio() {
        return precio.get();
    }

    public FloatProperty precioProperty() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio.set(precio);
    }

    public int getStock() {
        return stock.get();
    }

    public IntegerProperty stockProperty() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }
}
