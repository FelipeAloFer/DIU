package GestionHotel.util;

import javafx.beans.property.*;

import java.util.Date;

public class Reserva {
    private IntegerProperty idReserva;
    private ObjectProperty<Date> fecha_llegada;
    private ObjectProperty<Date> fecha_salida;
    private IntegerProperty num_habitaciones;
    private StringProperty tipo_habitacion;
    private BooleanProperty fumador;
    private StringProperty tipo_alojamiento;
    private StringProperty dni_cliente;

    public Reserva() {
        this.idReserva = new SimpleIntegerProperty();
        this.fecha_llegada = new SimpleObjectProperty();
        this.fecha_salida = new SimpleObjectProperty();
        this.num_habitaciones = new SimpleIntegerProperty();
        this.tipo_habitacion = new SimpleStringProperty();
        this.fumador = new SimpleBooleanProperty();
        this.tipo_alojamiento = new SimpleStringProperty();
        this.dni_cliente = new SimpleStringProperty();
    }

    public Reserva(int idReserva, Date fecha_llegada, Date fecha_salida, int num_habitaciones, String tipo_habitacion, boolean fumador, String tipo_alojamiento, String dni_cliente) {
        this.idReserva = new SimpleIntegerProperty(idReserva);
        this.fecha_llegada = new SimpleObjectProperty(fecha_llegada);
        this.fecha_salida = new SimpleObjectProperty(fecha_salida);
        this.num_habitaciones = new SimpleIntegerProperty(num_habitaciones);
        this.tipo_habitacion = new SimpleStringProperty(tipo_habitacion);
        this.fumador = new SimpleBooleanProperty(fumador);
        this.tipo_alojamiento = new SimpleStringProperty(tipo_alojamiento);
        this.dni_cliente = new SimpleStringProperty(dni_cliente);
    }

    public int getIdReserva() {
        return idReserva.get();
    }

    public IntegerProperty idReservaProperty() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva.set(idReserva);
    }

    public Date getFecha_llegada() {
        return fecha_llegada.get();
    }

    public ObjectProperty<Date> fecha_llegadaProperty() {
        return fecha_llegada;
    }

    public void setFecha_llegada(Date fecha_llegada) {
        this.fecha_llegada.set(fecha_llegada);
    }

    public Date getFecha_salida() {
        return fecha_salida.get();
    }

    public ObjectProperty<Date> fecha_salidaProperty() {
        return fecha_salida;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida.set(fecha_salida);
    }

    public int getNum_habitaciones() {
        return num_habitaciones.get();
    }

    public IntegerProperty num_habitacionesProperty() {
        return num_habitaciones;
    }

    public void setNum_habitaciones(int num_habitaciones) {
        this.num_habitaciones.set(num_habitaciones);
    }

    public String getTipo_habitacion() {
        return tipo_habitacion.get();
    }

    public StringProperty tipo_habitacionProperty() {
        return tipo_habitacion;
    }

    public void setTipo_habitacion(String tipo_habitacion) {
        this.tipo_habitacion.set(tipo_habitacion);
    }

    public boolean isFumador() {
        return fumador.get();
    }

    public BooleanProperty fumadorProperty() {
        return fumador;
    }

    public void setFumador(boolean fumador) {
        this.fumador.set(fumador);
    }

    public String getTipo_alojamiento() {
        return tipo_alojamiento.get();
    }

    public StringProperty tipo_alojamientoProperty() {
        return tipo_alojamiento;
    }

    public void setTipo_alojamiento(String tipo_alojamiento) {
        this.tipo_alojamiento.set(tipo_alojamiento);
    }

    public String getDni_cliente() {
        return dni_cliente.get();
    }

    public StringProperty dni_clienteProperty() {
        return dni_cliente;
    }

    public void setDni_cliente(String dni_cliente) {
        this.dni_cliente.set(dni_cliente);
    }
}
