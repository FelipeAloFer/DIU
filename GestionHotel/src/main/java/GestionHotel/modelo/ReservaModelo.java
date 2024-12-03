package GestionHotel.modelo;

import GestionHotel.modelo.repository.ReservaRepository;
import GestionHotel.util.Reserva;
import GestionHotel.util.ReservaUtil;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.time.LocalDate;

public class ReservaModelo {
    ReservaRepository reservaRepository;
    DoubleProperty habitacionesDobles = new SimpleDoubleProperty(50);
    DoubleProperty habitacionesIndividuales = new SimpleDoubleProperty(50);
    DoubleProperty habitacionesJunior = new SimpleDoubleProperty(50);
    DoubleProperty habitacionesSuite = new SimpleDoubleProperty(50);

    public DoubleProperty habitacionesDoblesProperty() {
        return habitacionesDobles;
    }

    public DoubleProperty habitacionesIndividualesProperty() {
        return habitacionesIndividuales;
    }

    public DoubleProperty habitacionesJuniorProperty() {
        return habitacionesJunior;
    }

    public DoubleProperty habitacionesSuiteProperty() {
        return habitacionesSuite;
    }


    public void setReservaModelo(ReservaRepository implementacion) {
        this.reservaRepository = implementacion;
    }

    public ObservableList<Reserva> setReservas(String dni) {
        try {
            ObservableList<ReservaVO> reservasVO = this.reservaRepository.obtenerListaReservasCliente(dni);
            return ReservaUtil.conversionReserva(reservasVO); // Convierte las reservas a la forma esperada
        } catch (ExcepcionHotel e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de conexión");
            alerta.setHeaderText("La base de datos no está conectada.");
            alerta.setContentText(e.getMessage());
            alerta.showAndWait();
            return FXCollections.emptyObservableList(); // Devuelve una lista vacía en caso de error
        }
    }

    public void añadirReserva (Reserva reservaNueva) throws ExcepcionHotel {
        ReservaVO reservaNuevaVO = ReservaUtil.conversionReservaVO(reservaNueva);
        // Añadir a la base de datos
        reservaRepository.addReserva(reservaNuevaVO);
        if (!reservaNueva.getFecha_llegada().isAfter(LocalDate.now()) && !reservaNueva.getFecha_salida().isBefore(LocalDate.now())) {
            switch (reservaNueva.getTipo_habitacion()) {
                case "DOBLE":
                    habitacionesDobles.set(habitacionesDobles.get() - 1);
                    break;

                case "DOBLE_DE_USO_INDIVIDUAL":
                    habitacionesIndividuales.set(habitacionesIndividuales.get() - 1);
                    break;

                case "JUNIOR_SUITE":
                    habitacionesJunior.set(habitacionesJunior.get() - 1);
                    break;

                case "SUITE":
                    habitacionesSuite.set(habitacionesSuite.get() - 1);
                    break;

                default:
                    throw new IllegalArgumentException("Tipo de habitación no reconocido: " + reservaNueva.getTipo_habitacion());
            }
        }
    }

    public void borrarReserva(Reserva selectedReserva) throws ExcepcionHotel {
        //Borrar persona de la base de datos
        reservaRepository.deleteReserva(selectedReserva.getIdReserva());
        if (!selectedReserva.getFecha_llegada().isAfter(LocalDate.now()) && !selectedReserva.getFecha_salida().isBefore(LocalDate.now())) {
            switch (selectedReserva.getTipo_habitacion()) {
                case "DOBLE":
                    habitacionesDobles.set(habitacionesDobles.get() - 1);
                    break;

                case "DOBLE_DE_USO_INDIVIDUAL":
                    habitacionesIndividuales.set(habitacionesIndividuales.get() - 1);
                    break;

                case "JUNIOR_SUITE":
                    habitacionesJunior.set(habitacionesJunior.get() - 1);
                    break;

                case "SUITE":
                    habitacionesSuite.set(habitacionesSuite.get() - 1);
                    break;

                default:
                    throw new IllegalArgumentException("Tipo de habitación no reconocido: " +  selectedReserva.getTipo_habitacion());
            }
        }
    }

    public void modificarReserva(Reserva selectedReserva) throws ExcepcionHotel {
        ReservaVO reservaSelectedVO = ReservaUtil.conversionReservaVO(selectedReserva);
        // Actualizamos en la base de datos
        reservaRepository.editReserva(reservaSelectedVO);
    }

    public int ultimoIDReserva() throws ExcepcionHotel {
        int ultimoID = reservaRepository.lastId();
        return ultimoID;
    }

    public int[] contarReservasDobles() throws ExcepcionHotel {
        return reservaRepository.contarMeses("DOBLE");
    }

    public int[] contarReservasIndividual() throws ExcepcionHotel {
        return reservaRepository.contarMeses("DOBLE_DE_USO_INDIVIDUAL");
    }

    public int[] contarReservasJuniorSuite() throws ExcepcionHotel {
        return reservaRepository.contarMeses("JUNIOR_SUITE");
    }

    public int[] contarReservasSuite() throws ExcepcionHotel {
        return reservaRepository.contarMeses("SUITE");
    }

    public void obtenerHabitacionesDobles() throws ExcepcionHotel {
        double ocupadas = reservaRepository.obtenerHabitacionesDobles();
        habitacionesDobles.set(habitacionesDobles.get() - ocupadas);
    }

    public void obtenerHabitacionesIndividuales() throws ExcepcionHotel {
        double ocupadas = reservaRepository.obtenerHabitacionesIndividuales();
        habitacionesIndividuales.set(habitacionesDobles.get() - ocupadas);
    }

    public void obtenerHabitacionesJunior() throws ExcepcionHotel {
        double ocupadas = reservaRepository.obtenerHabitacionesJunior();
        habitacionesJunior.set(habitacionesJunior.get() - ocupadas);
    }

    public void obtenerHabitacionesSuite() throws ExcepcionHotel {
        double ocupadas = reservaRepository.obtenerHabitacionesSuite();
        habitacionesSuite.set(habitacionesSuite.get() - ocupadas);
    }
}
