package GestionHotel.modelo.repository;

import GestionHotel.modelo.ClienteVO;
import GestionHotel.modelo.ExcepcionHotel;
import GestionHotel.modelo.ReservaVO;

import java.util.ArrayList;

public interface ReservaRepository {
    ArrayList<ReservaVO> obtenerListaReservas() throws ExcepcionHotel;

    void addReserva(ReservaVO var1) throws ExcepcionHotel;

    void deleteReserva(int var1) throws ExcepcionHotel;

    void editReserva(ReservaVO var1) throws ExcepcionHotel;

    int lastId() throws ExcepcionHotel;
}
