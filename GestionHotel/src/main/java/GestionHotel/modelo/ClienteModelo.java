package GestionHotel.modelo;

import GestionHotel.modelo.repository.ClienteRepository;
import GestionHotel.modelo.repository.ReservaRepository;
import GestionHotel.util.Cliente;
import GestionHotel.util.ClienteUtil;
import javafx.scene.control.Alert;

import java.util.ArrayList;

public class ClienteModelo {
    ClienteRepository clienteRepository;
    ReservaRepository reservaRepository;

    public ClienteModelo() {
    }

    public void setHotelModelo(ClienteRepository implementacion) {
        this.clienteRepository = implementacion;
    }

    public ArrayList<Cliente> setClientes() {
        try {
            ArrayList<ClienteVO> clientesVO = this.clienteRepository.obtenerListaClientes();
            return ClienteUtil.conversionCliente(clientesVO);
        } catch (ExcepcionHotel e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de conexión");
            alerta.setHeaderText("La base de datos no está conectada.");
            alerta.setContentText(e.getMessage());
            alerta.showAndWait();
            return null;
        }
    }

    public void añadirCliente (Cliente clienteNuevo) throws ExcepcionHotel {
        ClienteVO clienteNuevoVO = ClienteUtil.conversionClienteVO(clienteNuevo);
        // Añadir a la base de datos
        clienteRepository.addCliente(clienteNuevoVO);
    }

    public void borrarPersona(Cliente selectedCliente) throws ExcepcionHotel {
        //Borrar persona de la base de datos
        clienteRepository.deleteCliente(selectedCliente.getDni());
    }

    public void modificarPersona(Cliente selectedCliente) throws ExcepcionHotel {
        ClienteVO personaSelectedVO = ClienteUtil.conversionClienteVO(selectedCliente);
        // Actualizamos en la base de datos
        clienteRepository.editCliente(personaSelectedVO);
    }

    public int ultimoID() throws ExcepcionHotel {
        int ultimoID = clienteRepository.lastId();
        return ultimoID;
    }
}
