package GestionHotel.util;

import GestionHotel.modelo.ReservaVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class ReservaUtil {
    static ObservableList<Reserva> reservas = FXCollections.observableArrayList();

    public  ReservaUtil(){

    }

    public static ObservableList<Reserva> conversionReserva(ObservableList<ReservaVO> reservasVO) {
        for (ReservaVO reserva : reservasVO) {
            reservas.add(new Reserva(reserva.getIdReserva(), reserva.getFecha_llegada(), reserva.getFecha_salida(), reserva.getNum_habitaciones(), reserva.getTipo_habitacion(), reserva.isFumador(), reserva.getTipo_alojamiento(), reserva.getDni_cliente()));
        }
        return reservas;
    }

    public static ReservaVO conversionReservaVO(Reserva reserva) {
        ReservaVO reservaVO = new ReservaVO(reserva.getIdReserva(), reserva.getFecha_llegada(), reserva.getFecha_salida(), reserva.getNum_habitaciones(), reserva.getTipo_habitacion(), reserva.isFumador(), reserva.getTipo_alojamiento(), reserva.getDni_cliente());
        return reservaVO;
    }
}
