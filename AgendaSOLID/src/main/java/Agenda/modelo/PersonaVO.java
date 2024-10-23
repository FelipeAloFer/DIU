package Agenda.modelo;


public class PersonaVO {
    private int codigoPersona;
    private String nombre;
    private String apellido;
    private String calle;
    private String ciudad;
    private String codigoPostal;
    private String fechaNacimiento;

    public PersonaVO(int codigoPersona, String nombre, String apellido, String calle, String ciudad, String codigoPostal, String fechaNacimiento) {
        this.codigoPersona = codigoPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.calle = calle;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.fechaNacimiento = fechaNacimiento;
    }

    public PersonaVO() {

    }

    public int getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(int codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
