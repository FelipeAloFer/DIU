package Agenda.modelo;

public class PersonaVO {
    int codigoPersona;
    String nombre;
    String apellido;
    String calle;
    String ciudad;
    String codigoPostal;
    String FechaNacimiento;

    public PersonaVO(int codigo, String nombre, String apellido, String calle, String ciudad, String codigoPostal, String fechaNacimiento) {
        this.codigoPersona = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.calle = calle;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        FechaNacimiento = fechaNacimiento;
    }

    public PersonaVO(String nombre, String apellido, String calle, String ciudad, String codigoPostal, String fechaNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.calle = calle;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        FechaNacimiento = fechaNacimiento;
    }

    public int getCodigoPersona() {
        return codigoPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCalle() {
        return calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setCodigoPersona(int codigo) {
        this.codigoPersona = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "PersonaVO{" +
                "codigoPersona=" + codigoPersona +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", calle='" + calle + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                ", FechaNacimiento='" + FechaNacimiento + '\'' +
                '}';
    }
}