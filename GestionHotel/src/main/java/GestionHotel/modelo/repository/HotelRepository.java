package GestionHotel.modelo.repository;

import GestionHotel.modelo.ClienteVO;
import GestionHotel.modelo.ExcepcionHotel;

import java.util.ArrayList;

public interface HotelRepository {
    ArrayList<ClienteVO> obtenerListaClientes() throws ExcepcionHotel;

    void addCliente(ClienteVO var1) throws ExcepcionHotel;

    void deleteCliente(Integer var1) throws ExcepcionHotel;

    void editCliente(ClienteVO var1) throws ExcepcionHotel;

    int lastId() throws ExcepcionHotel;
}
