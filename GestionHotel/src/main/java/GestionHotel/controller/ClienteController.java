package GestionHotel.controller;

import GestionHotel.manager.ThemeManager;
import GestionHotel.manager.ThemeManagerImpl;
import GestionHotel.modelo.ExcepcionHotel;
import GestionHotel.modelo.ClienteModelo;
import GestionHotel.modelo.ReservaModelo;
import GestionHotel.util.Cliente;
import GestionHotel.util.Reserva;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ClienteController {
    private ClienteModelo modeloCliente;
    private ReservaModelo reservaModelo;
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

    @FXML
    private ListView listaReservas;

    @FXML
    private ComboBox<String> temaComboBox;

    private ThemeManager themeManager;

    // Constructor donde inyectamos el ThemeManager
    public ClienteController() {
        // Suponemos que el Stage se obtiene en algún punto de la aplicación, como en el main
        this.themeManager = new ThemeManagerImpl(new Stage()); // Ajusta esto para que se obtenga el stage actual
    }

    public void setThemeManager(ThemeManager themeManager) {
        this.themeManager = themeManager;
    }

    public void setController(ClienteModelo modelo, ReservaModelo reservaModelo) {
        this.modeloCliente = modelo;
        this.reservaModelo = reservaModelo;  // Asegúrate de inicializar reservaModelo
    }

    public void setPersona() throws ExcepcionHotel {
        clientes = modeloCliente.setClientes();
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
//        // Inicializamos el ComboBox con los valores de temas disponibles
//        temaComboBox.getItems().addAll(
//                "Tema Gris", "Tema Rojo", "Tema Azul", "Tema Verde", "Tema Arcoiris"
//        );
        // Seteamos el evento de cambio
        temaComboBox.setOnAction(this::handleThemeChange);

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

    private void showPersonDetails(Cliente cliente) {
        if (cliente != null) {
            // Rellenar los detalles del cliente
            dniLabel.setText(String.valueOf(cliente.getDni()));
            nombreLabel.setText(cliente.getNombre());
            apellidosLabel.setText(cliente.getApellidos());
            direccionLabel.setText(cliente.getDireccion());
            localidadLabel.setText(cliente.getLocalidad());
            provinciaLabel.setText(cliente.getProvincia());

            // Limpiar las reservas anteriores y establecer las nuevas
            listaReservas.getItems().clear();  // Limpiar la lista de reservas
            listaReservas.setItems(reservaModelo.setReservas(cliente.getDni()));  // Establecer nuevas reservas
        } else {
            // Si el cliente es nulo, limpiar todo
            dniLabel.setText("");
            nombreLabel.setText("");
            apellidosLabel.setText("");
            direccionLabel.setText("");
            localidadLabel.setText("");
            provinciaLabel.setText("");

            // Limpiar las reservas
            listaReservas.getItems().clear();
        }
    }

    @FXML
    private Cliente handleDeleteCliente() {
        int selectedIndex = tablaClientes.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Cliente selectedCliente = tablaClientes.getItems().get(selectedIndex);
            try {
                modeloCliente.borrarPersona(selectedCliente); // Borra de la base de datos
                tablaClientes.getItems().remove(selectedIndex); // Borra de la tabla
            } catch (ExcepcionHotel e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error al eliminar la persona: " + e.getMessage());
                alert.show();
            }
            return selectedCliente;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Debes seleccionar una persona para borrarla");
            alert.show();
            return null;
        }
    }



    @FXML
    private void handleNewCliente() {
        Cliente clienteNuevo = new Cliente();
        boolean okClicked;
        if (modeloCliente.setClientes() == null) {
        } else {
            okClicked = main.showPersonEditDialog(clienteNuevo);  // Llama al diálogo de edición
            if (okClicked) {
                try {
                    modeloCliente.añadirCliente(clienteNuevo); // Guarda en la base de datos
                    tablaClientes.getItems().add(clienteNuevo); // Añade a la tabla
                } catch (ExcepcionHotel e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @FXML
    private void handleEditCliente() {
        Cliente selectedCliente = tablaClientes.getSelectionModel().getSelectedItem();
        if (selectedCliente != null) {
            boolean okClicked = main.showPersonEditDialog(selectedCliente);  // Llama al diálogo de edición
            if (okClicked) {
                try {
                    showPersonDetails(selectedCliente); // Actualiza los detalles en pantalla
                    modeloCliente.modificarPersona(selectedCliente); // Guarda los cambios en la base de datos
                } catch (ExcepcionHotel e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Debes seleccionar una persona para editarla");
            alert.show();
        }
    }

    @FXML
    private Reserva handleDeleteReserva() {
        int selectedIndex = listaReservas.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Reserva selectedReserva = (Reserva) listaReservas.getItems().get(selectedIndex); // Asegúrate de que el tipo sea consistente
            try {
                reservaModelo.borrarReserva(selectedReserva); // Borra la reserva de la base de datos
                listaReservas.getItems().remove(selectedIndex); // Borra de la lista
            } catch (ExcepcionHotel e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error al eliminar la reserva: " + e.getMessage());
                alert.show();
            }
            return selectedReserva;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Debes seleccionar una reserva para borrarla");
            alert.show();
            return null;
        }
    }

    @FXML
    private void handleNewReserva() {
        Cliente selectedCliente = tablaClientes.getSelectionModel().getSelectedItem();
        Reserva reservaNueva = new Reserva(); // Cambia a la clase específica de reservas
        boolean okClicked = main.showReservaEditDialog(selectedCliente.getDni(), reservaNueva); // Llama al diálogo de edición (debes crearlo)
        if (okClicked) {
            try {
                reservaModelo.añadirReserva(reservaNueva); // Guarda en la base de datos
                listaReservas.getItems().add(reservaNueva); // Añade a la lista
            } catch (ExcepcionHotel e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error al añadir la reserva: " + e.getMessage());
                alert.show();
            }
        }
    }

    @FXML
    private void handleEditReserva() {
        Cliente selectedCliente = tablaClientes.getSelectionModel().getSelectedItem();
        Reserva selectedReserva = (Reserva) listaReservas.getSelectionModel().getSelectedItem();
        if (selectedReserva != null) {
            boolean okClicked = main.showReservaEditDialog(selectedCliente.getDni(), selectedReserva); // Llama al diálogo de edición
            if (okClicked) {
                try {
                    reservaModelo.modificarReserva(selectedReserva); // Guarda los cambios en la base de datos
                    // Si la lista no actualiza automáticamente, puedes hacerlo manualmente.
                } catch (ExcepcionHotel e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Error al modificar la reserva: " + e.getMessage());
                    alert.show();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Debes seleccionar una reserva para editarla");
            alert.show();
        }
    }


    private void handleThemeChange(ActionEvent event) {
        String selectedTheme = temaComboBox.getValue();
        if (selectedTheme != null) {
            themeManager.applyTheme(selectedTheme);
        }
    }
}
