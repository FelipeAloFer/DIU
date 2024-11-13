package GestionHotel.modelo;

import GestionHotel.modelo.repository.ClienteRepository;
import GestionHotel.modelo.repository.ReservaRepository;
import GestionHotel.util.Cliente;
import GestionHotel.util.ClienteUtil;
import GestionHotel.util.Reserva;
import GestionHotel.util.ReservaUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.util.ArrayList;

public class ReservaModelo {
    ReservaRepository reservaRepository;

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
    }

    public void borrarReserva(Reserva selectedReserva) throws ExcepcionHotel {
        //Borrar persona de la base de datos
        reservaRepository.deleteReserva(selectedReserva.getIdReserva());
    }

    public void modificarReserva(Reserva selectedReserva) throws ExcepcionHotel {
        ReservaVO reservaSelectedVO = ReservaUtil.conversionReservaVO(selectedReserva);
        // Actualizamos en la base de datos
        reservaRepository.editReserva(reservaSelectedVO);
    }
}
