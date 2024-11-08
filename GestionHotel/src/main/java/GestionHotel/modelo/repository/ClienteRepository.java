package GestionHotel.modelo.repository;

import GestionHotel.modelo.ClienteVO;
import GestionHotel.modelo.ExcepcionHotel;

import java.util.ArrayList;

public interface ClienteRepository {
    ArrayList<ClienteVO> obtenerListaClientes() throws ExcepcionHotel;

    void addCliente(ClienteVO var1) throws ExcepcionHotel;

    void deleteCliente(String var1) throws ExcepcionHotel;

    void editCliente(ClienteVO var1) throws ExcepcionHotel;

    int lastId() throws ExcepcionHotel;
}
