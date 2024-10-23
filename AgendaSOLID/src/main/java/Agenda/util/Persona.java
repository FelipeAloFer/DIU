package Agenda.util;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Persona {

    private final StringProperty nombre;
    private final StringProperty apellido;
    private final StringProperty calle;
    private final StringProperty codigoPostal;
    private final StringProperty ciudad;
    private final StringProperty fechaNacimiento;

    /**
     * Default constructor.
     */
    public Persona() {
        this(null, null);
    }


    public Persona(String nombre, String apellido) {
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.calle = new SimpleStringProperty("some street");
        this.codigoPostal = new SimpleStringProperty("12345");
        this.ciudad = new SimpleStringProperty("some city");
        this.fechaNacimiento = new SimpleStringProperty("2020-01-01");
    }

    public String getFirstName() {
        return nombre.get();
    }

    public void setFirstName(String firstName) {
        this.nombre.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return nombre;
    }

    public String getLastName() {
        return apellido.get();
    }

    public void setLastName(String lastName) {
        this.apellido.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return apellido;
    }

    public String getStreet() {
        return calle.get();
    }

    public void setStreet(String street) {
        this.calle.set(street);
    }

    public StringProperty streetProperty() {
        return calle;
    }

    public String getPostalCode() {
        return codigoPostal.get();
    }

    public void setPostalCode(String postalCode) {
        this.codigoPostal.set(postalCode);
    }

    public StringProperty postalCodeProperty() {
        return codigoPostal;
    }

    public String getCity() {
        return ciudad.get();
    }

    public void setCity(String city) {
        this.ciudad.set(city);
    }

    public StringProperty cityProperty() {
        return ciudad;
    }

    public String getBirthday() {
        return fechaNacimiento.get();
    }

    public void setBirthday(String birthday) {
        this.fechaNacimiento.set(birthday);
    }

    public StringProperty birthdayProperty() {
        return fechaNacimiento;
    }
}