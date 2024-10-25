package Agenda.util;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Persona {

    private StringProperty nombre;
    private StringProperty apellido;
    private StringProperty calle;
    private StringProperty codigoPostal;
    private StringProperty ciudad;
    private StringProperty fechaNacimiento;

    public Persona() {

    }

    public Persona(String nombre, String apellido, String calle, String codigoPostal, String ciudad, String fechaNacimiento) {
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.calle = new SimpleStringProperty("some street");
        this.codigoPostal = new SimpleStringProperty("12345");
        this.ciudad = new SimpleStringProperty("some city");
        this.fechaNacimiento = new SimpleStringProperty("2020-01-01");
    }


    // Getters y setters para los StringProperty (opcional)
    public StringProperty nombreProperty() {
        return nombre;
    }

    public StringProperty apellidoProperty() {
        return apellido;
    }

    public StringProperty direccionProperty() {
        return calle;
    }

    public StringProperty ciudadProperty() {
        return ciudad;
    }

    public StringProperty codigoPostalProperty() {
        return codigoPostal;
    }

    public StringProperty fechaNacimientoProperty() {
        return fechaNacimiento;
    }

    public String getNombre() {
        return nombre.get();
    }



    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getApellido() {
        return apellido.get();
    }



    public void setApellido(String apellido) {
        this.apellido.set(apellido);
    }

    public String getCalle() {
        return calle.get();
    }



    public void setCalle(String direccion) {
        this.calle.set(direccion);
    }

    public String getCiudad() {
        return ciudad.get();
    }



    public void setCiudad(String ciudad) {
        this.ciudad.set(ciudad);
    }

    public String getCodigoPostal() {
        return codigoPostal.get();
    }



    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal.set(codigoPostal);
    }

    public String getFechaNacimiento() {
        return fechaNacimiento.get();
    }



    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento.set(fechaNacimiento);
    }
}