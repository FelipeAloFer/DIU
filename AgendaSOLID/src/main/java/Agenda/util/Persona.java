package Agenda.util;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Persona {
    private IntegerProperty codigoPersona;
    private StringProperty nombre;
    private StringProperty apellido;
    private StringProperty calle;
    private StringProperty codigoPostal;
    private StringProperty ciudad;
    private StringProperty fechaNacimiento;

    public Persona() {

    }

    public Persona(Integer codigoPersona, String nombre, String apellido, String calle, String codigoPostal, String ciudad, String fechaNacimiento) {
        this.codigoPersona = new SimpleIntegerProperty(codigoPersona);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.calle = new SimpleStringProperty(calle);
        this.codigoPostal = new SimpleStringProperty(codigoPostal);
        this.ciudad = new SimpleStringProperty(ciudad);
        this.fechaNacimiento = new SimpleStringProperty(fechaNacimiento);
    }


    // Getters y setters para los StringProperty (opcional)


    public Integer getCodigoPersona() {
        return codigoPersona.get();
    }

    public IntegerProperty codigoPersonaProperty() {
        return codigoPersona;
    }

    public void setCodigoPersona(Integer codigoPersona) {
        this.codigoPersona.set(codigoPersona);
    }

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