package GestionHotel.modelo;

import GestionHotel.modelo.repository.HotelRepository;
import GestionHotel.util.Cliente;
import GestionHotel.util.ClienteUtil;
import javafx.scene.control.Alert;

import java.util.ArrayList;

public class HotelModelo {
    HotelRepository hotelRepository;

    public HotelModelo() {
    }

    public void setHotelModelo(HotelRepository implementacion) {
        this.hotelRepository = implementacion;
    }

    public ArrayList<Cliente> setClientes() {
        try {
            ArrayList<ClienteVO> clientesVO = this.hotelRepository.obtenerListaClientes();
            System.out.println(clientesVO.size());
            return ClienteUtil.conversion(clientesVO);
        } catch (ExcepcionHotel e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de conexión");
            alerta.setHeaderText("La base de datos no está conectada.");
            alerta.setContentText(e.getMessage());
            alerta.showAndWait();
            return null;
        }
    }
}
