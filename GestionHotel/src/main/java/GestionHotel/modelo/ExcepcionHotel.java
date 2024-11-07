package GestionHotel.modelo;

public class ExcepcionHotel extends RuntimeException {
    private String mensaje;

    public ExcepcionHotel() {
    }

    public ExcepcionHotel(String ms) {
        this.mensaje = ms;
    }

    public String imprimirMensaje() {
        return this.mensaje;
    }
}
