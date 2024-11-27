package GestionHotel.modelo;

public class ExcepcionHotel extends RuntimeException {
    private String mensaje;

    // Constructor vacío
    public ExcepcionHotel() {
        super();
    }

    // Constructor con mensaje
    public ExcepcionHotel(String ms) {
        super(ms); // Llama al constructor de RuntimeException con el mensaje
        this.mensaje = ms;
    }

    // Método para imprimir el mensaje personalizado
    public String imprimirMensaje() {
        return this.mensaje;
    }
}
