package Agenda.modelo;

public class ExcepcionAgenda extends Exception {
    private String mensaje;

    public ExcepcionAgenda() {
    }

    public ExcepcionAgenda(String ms) {
        this.mensaje = ms;
    }

    public String imprimirMensaje() {
        return this.mensaje;
    }
}
