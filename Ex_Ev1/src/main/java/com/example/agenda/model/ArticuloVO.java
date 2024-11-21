package com.example.agenda.model;

import java.time.LocalDate;

public class ArticuloVO {

    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private int id;

    // Constructor con todos los parámetros
    public ArticuloVO(int id, String nombre, String descripcion, double precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = cantidad;
    }

    // Constructor sin el ID
    public ArticuloVO(String nombre, String descripcion, double precio, int cantidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = cantidad;
        this.id = 0;  // Valor por defecto para el ID
    }

    // Métodos getter y setter para los campos
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int cantidad) {
        this.stock = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ArticuloVO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", cantidad=" + stock +
                '}';
    }
}
