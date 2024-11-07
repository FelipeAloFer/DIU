package GestionHotel.controller;

import GestionHotel.modelo.ExcepcionHotel;
import GestionHotel.modelo.HotelModelo;
import GestionHotel.util.Cliente;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class ClienteController {
    private HotelModelo modelo;
    private ArrayList<Cliente> clientes;
    private Main main;

    @FXML
    private TableView<Cliente> tablaClientes;
    @FXML
    private TableColumn<Cliente, String> nombreColumna;
    @FXML
    private TableColumn<Cliente, String> apellidosColumna;

    @FXML
    private Label dniLabel;
    @FXML
    private Label nombreLabel;
    @FXML
    private Label apellidosLabel;
    @FXML
    private Label direccionLabel;
    @FXML
    private Label localidadLabel;
    @FXML
    private Label provinciaLabel;

    public void setController(HotelModelo modelo) {
        this.modelo = modelo;
    }

    public void setPersona() throws ExcepcionHotel {
        clientes = modelo.setClientes();
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public ArrayList<Cliente> getPersonas() {
        return clientes;
    }

    public void setDatosPersonas(ObservableList<Cliente> lista){
        tablaClientes.setItems(lista);
    }

    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        nombreColumna.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        apellidosColumna.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getApellidos()));

        // Clear person details.
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        tablaClientes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    private void showPersonDetails(Cliente persona) {
        if (persona != null) {
            // Fill the labels with info from the person object.
            dniLabel.setText(String.valueOf(persona.getDni()));
            nombreLabel.setText(persona.getNombre());
            apellidosLabel.setText(persona.getApellidos());
            direccionLabel.setText(persona.getDireccion());
            localidadLabel.setText(persona.getLocalidad());
            provinciaLabel.setText(persona.getProvincia());
        } else {
            // Person is null, remove all the text.
            dniLabel.setText("");
            nombreLabel.setText("");
            apellidosLabel.setText("");
            direccionLabel.setText("");
            localidadLabel.setText("");
            provinciaLabel.setText("");
        }
    }
}
