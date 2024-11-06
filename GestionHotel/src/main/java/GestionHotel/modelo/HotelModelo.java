package GestionHotel.modelo;

import GestionHotel.modelo.repository.HotelRepository;
import GestionHotel.util.Cliente;
import javafx.scene.control.Alert;

import java.util.ArrayList;

public class HotelModelo {
    HotelRepository hotelRepository;

    public HotelModelo() {
    }

    public void setHotelModelo(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public ArrayList<Cliente> setClientes() {
        try {
            ArrayList<ClienteVO> personasVO = this.hotelRepository.obtenerListaClientes();
            return PersonaUtil.conversion(personasVO);
        } catch (ExcepcionAgenda e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de conexión");
            alerta.setHeaderText("La base de datos no está conectada.");
            alerta.setContentText(e.getMessage());
            alerta.showAndWait();
            return null;
        }
    }
}
