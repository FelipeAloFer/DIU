package GestionHotel.modelo;

import java.time.LocalDate;

public class ReservaVO {
    private Integer id_reserva;
    private LocalDate fecha_llegada;
    private LocalDate fecha_salida;
    private Integer num_habitaciones;
    private String tipo_habitacion;
    private boolean fumador;
    private String tipo_alojamiento;
    private String dni_cliente;

    public ReservaVO(Integer id_reserva, LocalDate fecha_llegada, LocalDate fecha_salida, Integer num_habitaciones, String tipo_habitacion, boolean fumador, String tipo_alojamiento, String dni_cliente) {
        this.id_reserva = id_reserva;
        this.fecha_llegada = fecha_llegada;
        this.fecha_salida = fecha_salida;
        this.num_habitaciones = num_habitaciones;
        this.tipo_habitacion = tipo_habitacion;
        this.fumador = fumador;
        this.tipo_alojamiento = tipo_alojamiento;
        this.dni_cliente = dni_cliente;
    }

    public String getTipo_habitacion() {
        return tipo_habitacion;
    }

    public void setTipo_habitacion(String tipo_habitacion) {
        this.tipo_habitacion = tipo_habitacion;
    }

    public String getTipo_alojamiento() {
        return tipo_alojamiento;
    }

    public void setTipo_alojamiento(String tipo_alojamiento) {
        this.tipo_alojamiento = tipo_alojamiento;
    }

    public Integer getIdReserva() {
        return id_reserva;
    }

    public void setId_reserva(Integer id_reserva) {
        this.id_reserva = id_reserva;
    }

    public LocalDate getFecha_llegada() {
        return fecha_llegada;
    }

    public void setFecha_llegada(LocalDate fecha_llegada) {
        this.fecha_llegada = fecha_llegada;
    }

    public LocalDate getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(LocalDate fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public Integer getNum_habitaciones() {
        return num_habitaciones;
    }

    public void setNum_habitaciones(Integer  num_habitaciones) {
        this.num_habitaciones = num_habitaciones;
    }

    public boolean isFumador() {
        return fumador;
    }

    public void setFumador(boolean fumador) {
        this.fumador = fumador;
    }

    public String getDni_cliente() {
        return dni_cliente;
    }

    public void setDni_cliente(String dni_cliente) {
        this.dni_cliente = dni_cliente;
    }
}
