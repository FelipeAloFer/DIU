package GestionHotel.modelo;

import java.util.Date;

public class ReservaVO {
    private Integer id_reserva;
    private Date fecha_llegada;
    private Date fecha_salida;
    private Integer num_habitaciones;
    private String tipo_habitacion;
    private boolean fumador;
    private String tipo_alojamiento;
    private String dni_cliente;

    public ReservaVO(Integer id_reserva, Date fecha_llegada, Date fecha_salida, Integer num_habitaciones, String tipo_habitacion, boolean fumador, String tipo_alojamiento, String dni_cliente) {
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

    public Integer getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(Integer id_reserva) {
        this.id_reserva = id_reserva;
    }

    public Date getFecha_llegada() {
        return fecha_llegada;
    }

    public void setFecha_llegada(Date fecha_llegada) {
        this.fecha_llegada = fecha_llegada;
    }

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(Date fecha_salida) {
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
