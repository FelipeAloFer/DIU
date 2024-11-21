package com.example.agenda.model;

public class ExcepcionArticulo extends Exception {
    private String mensaje;

    public ExcepcionArticulo(String s, Exception e) {
    }

    public ExcepcionArticulo(String ms) {
        this.mensaje = ms;
    }

    public String imprimirMensaje() {
        return this.mensaje;
    }
}