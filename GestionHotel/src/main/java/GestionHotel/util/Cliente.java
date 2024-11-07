package GestionHotel.util;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cliente {
    private IntegerProperty dni;
    private StringProperty nombre;
    private StringProperty apellidos;
    private StringProperty direccion;
    private StringProperty localidad;
    private StringProperty provincia;

    public Cliente() {
        this.dni = new SimpleIntegerProperty();
        this.nombre = new SimpleStringProperty();
        this.apellidos = new SimpleStringProperty();
        this.direccion = new SimpleStringProperty();
        this.localidad = new SimpleStringProperty();
        this.provincia = new SimpleStringProperty();
    }

    public Cliente(Integer dni, String nombre, String apellidos, String direccion, String localidad, String provincia) {
        this.dni = new SimpleIntegerProperty(dni);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellidos = new SimpleStringProperty(apellidos);
        this.direccion = new SimpleStringProperty(direccion);
        this.localidad = new SimpleStringProperty(localidad);
        this.provincia = new SimpleStringProperty(provincia);
    }

    public int getDni() {
        return dni.get();
    }

    public IntegerProperty dniProperty() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni.set(dni);
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

    public String getApellidos() {
        return apellidos.get();
    }

    public StringProperty apellidosProperty() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos.set(apellidos);
    }

    public String getDireccion() {
        return direccion.get();
    }

    public StringProperty direccionProperty() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public String getLocalidad() {
        return localidad.get();
    }

    public StringProperty localidadProperty() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad.set(localidad);
    }

    public String getProvincia() {
        return provincia.get();
    }

    public StringProperty provinciaProperty() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia.set(provincia);
    }
}
