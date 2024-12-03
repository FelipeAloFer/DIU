package GestionHotel.modelo.repository;

import GestionHotel.modelo.ClienteVO;
import GestionHotel.modelo.ExcepcionHotel;
import GestionHotel.modelo.ReservaVO;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public interface ReservaRepository {
    ArrayList<ReservaVO> obtenerListaReservas() throws ExcepcionHotel;

    ObservableList<ReservaVO> obtenerListaReservasCliente(String dni_cliente) throws ExcepcionHotel;

    void addReserva(ReservaVO var1) throws ExcepcionHotel;

    void deleteReserva(int var1) throws ExcepcionHotel;

    void editReserva(ReservaVO var1) throws ExcepcionHotel;

    int obtenerHabitacionesDobles() throws ExcepcionHotel;

    int obtenerHabitacionesIndividuales() throws ExcepcionHotel;

    int obtenerHabitacionesJunior() throws ExcepcionHotel;

    int obtenerHabitacionesSuite() throws ExcepcionHotel;

    int lastId() throws ExcepcionHotel;

    int[] contarMeses(String tipo_habitacion) throws ExcepcionHotel;

}
