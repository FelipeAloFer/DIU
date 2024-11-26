package GestionHotel.controller;

import GestionHotel.manager.ThemeManager;
import GestionHotel.manager.ThemeManagerImpl;
import GestionHotel.modelo.ExcepcionHotel;
import GestionHotel.modelo.ClienteModelo;
import GestionHotel.modelo.ReservaModelo;
import GestionHotel.util.Cliente;
import GestionHotel.util.Reserva;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;


//Esta clase controla la interfaz de gestión de clientes y reservas en un hotel.
//Proporciona funcionalidades para añadir, editar, eliminar y buscar clientes y reservas,
//así como para cambiar temas de la aplicación.

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

    @FXML
    private TextField dniBuscarTextField;

    private ThemeManager themeManager;


    //Constructor que inicializa el gestor de temas.

    public ClienteController() {
        this.themeManager = new ThemeManagerImpl(new Stage()); // Ajusta esto para que se obtenga el stage actual
    }

    public void setThemeManager(ThemeManager themeManager) {
        this.themeManager = themeManager;
    }

    //Inicializa los componentes de la interfaz gráfica y enlaza las columnas de la tabla con los datos.
    @FXML
    private void initialize() {
        temaComboBox.setOnAction(this::handleThemeChange);

        // Inicializa las columnas de la tabla de clientes.
        nombreColumna.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        apellidosColumna.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getApellidos()));

        // Limpia los detalles del cliente.
        showPersonDetails(null);

        // Agrega un listener para actualizar los detalles del cliente seleccionado.
        tablaClientes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }


    //Establece los modelos para clientes y reservas.

    public void setController(ClienteModelo modelo, ReservaModelo reservaModelo) {
        this.modeloCliente = modelo;
        this.reservaModelo = reservaModelo;
    }


    //Devuelve la lista de clientes.

    public ArrayList<Cliente> getPersonas() {
        return clientes;
    }


    //Carga los clientes desde el modelo.

    public void setPersona() throws ExcepcionHotel {
        clientes = modeloCliente.setClientes();
    }


    //Asocia la clase principal al controlador.

    public void setMain(Main main) {
        this.main = main;
    }


    //Establece los datos de los clientes en la tabla.

    public void setDatosPersonas(ObservableList<Cliente> lista) {
        tablaClientes.setItems(lista);
    }


    //Muestra los detalles de un cliente seleccionado en los campos de la interfaz.

    private void showPersonDetails(Cliente cliente) {
        if (cliente != null) {
            dniLabel.setText(String.valueOf(cliente.getDni()));
            nombreLabel.setText(cliente.getNombre());
            apellidosLabel.setText(cliente.getApellidos());
            direccionLabel.setText(cliente.getDireccion());
            localidadLabel.setText(cliente.getLocalidad());
            provinciaLabel.setText(cliente.getProvincia());

            // Limpia y actualiza la lista de reservas.
            listaReservas.getItems().clear();
            listaReservas.setItems(reservaModelo.setReservas(cliente.getDni()));
        } else {
            // Limpia todos los campos si no hay cliente seleccionado.
            dniLabel.setText("");
            nombreLabel.setText("");
            apellidosLabel.setText("");
            direccionLabel.setText("");
            localidadLabel.setText("");
            provinciaLabel.setText("");
            listaReservas.getItems().clear();
        }
    }


    //Maneja la creación de un nuevo cliente, abriendo un diálogo de edición.

    @FXML
    private void handleNewCliente() {
        Cliente clienteNuevo = new Cliente();

        // Si todo esta bien, abrir el dialogo para editar el cliente
        boolean okClicked = main.showPersonEditDialog(clienteNuevo);
        if (okClicked) {
            // Verificar si el DNI es válido antes de continuar
            if (!esDniValido(clienteNuevo.getDni())) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "El DNI proporcionado no es válido.");
                alert.show();
                return; // Salir si el DNI no es válido
            }
            // Comprobar si el DNI ya existe
            for (Cliente cliente : tablaClientes.getItems()) {
                if (cliente.getDni().equals(clienteNuevo.getDni())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "El DNI ya existe. Por favor, ingrese un DNI diferente.");
                    alert.show();
                    return; // Salir si el DNI ya está en uso
                }
            }
            try {
                modeloCliente.añadirCliente(clienteNuevo);
                tablaClientes.getItems().add(clienteNuevo);
            } catch (ExcepcionHotel e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //Maneja la edición de un cliente seleccionado.

    @FXML
    private void handleEditCliente() {
        Cliente selectedCliente = tablaClientes.getSelectionModel().getSelectedItem();
        if (selectedCliente != null) {
            boolean okClicked = main.showPersonEditDialog(selectedCliente);
            if (okClicked) {
                try {
                    showPersonDetails(selectedCliente);
                    modeloCliente.modificarPersona(selectedCliente);
                    tablaClientes.refresh();
                } catch (ExcepcionHotel e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Debes seleccionar una persona para editarla");
            alert.show();
        }
    }


    //Maneja la eliminación de un cliente seleccionado.

    @FXML
    private Cliente handleDeleteCliente() {
        int selectedIndex = tablaClientes.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Cliente selectedCliente = tablaClientes.getItems().get(selectedIndex);
            try {
                modeloCliente.borrarPersona(selectedCliente);
                tablaClientes.getItems().remove(selectedIndex);
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


    //Maneja la creación de una nueva reserva asociada al cliente seleccionado.

    @FXML
    private void handleNewReserva() {
        Cliente selectedCliente = tablaClientes.getSelectionModel().getSelectedItem();
        Reserva reservaNueva = new Reserva();
        boolean okClicked = main.showReservaEditDialog(selectedCliente.getDni(), reservaNueva);
        if (okClicked) {
            try {
                reservaModelo.añadirReserva(reservaNueva);
                listaReservas.getItems().add(reservaNueva);
            } catch (ExcepcionHotel e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error al añadir la reserva: " + e.getMessage());
                alert.show();
            }
        }
    }


    //Maneja la edición de una reserva seleccionada.

    @FXML
    private void handleEditReserva() {
        Cliente selectedCliente = tablaClientes.getSelectionModel().getSelectedItem();
        Reserva selectedReserva = (Reserva) listaReservas.getSelectionModel().getSelectedItem();
        if (selectedReserva != null) {
            boolean okClicked = main.showReservaEditDialog(selectedCliente.getDni(), selectedReserva);
            if (okClicked) {
                try {
                    reservaModelo.modificarReserva(selectedReserva);
                    listaReservas.refresh();
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


    //Maneja la eliminación de una reserva seleccionada.
    @FXML
    private Reserva handleDeleteReserva() {
        int selectedIndex = listaReservas.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Reserva selectedReserva = (Reserva) listaReservas.getItems().get(selectedIndex);
            try {
                reservaModelo.borrarReserva(selectedReserva);
                listaReservas.getItems().remove(selectedIndex);
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


    //Maneja la búsqueda de un cliente por su DNI en la tabla.
    @FXML
    private void handleBuscarDni() {
        String dniBusqueda = dniBuscarTextField.getText().trim();
        if (dniBusqueda.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, ingrese un DNI para buscar.");
            alert.show();
            return;
        }

        Cliente clienteEncontrado = null;
        for (Cliente cliente : tablaClientes.getItems()) {
            if (cliente.getDni().equals(dniBusqueda)) {
                clienteEncontrado = cliente;
                break;
            }
        }

        if (clienteEncontrado != null) {
            tablaClientes.getSelectionModel().select(clienteEncontrado);
            showPersonDetails(clienteEncontrado);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No se encontró un cliente con el DNI proporcionado.");
            alert.show();
        }
    }

    // Metodo para verificar la validez del DNI (por ejemplo, un DNI español)
    private boolean esDniValido(String dni) {
        // Comprobar que el DNI no está vacío y tiene el formato adecuado
        if (dni == null || dni.length() != 9) {
            return false;
        }

        // El formato del DNI español debe tener 8 números seguidos de una letra
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        String numero = dni.substring(0, 8);
        char letra = dni.charAt(8);

        try {
            Integer.parseInt(numero); // Comprobar que los primeros 8 caracteres son números
            int indice = Integer.parseInt(numero) % 23;
            return letra == letras.charAt(indice); // Comprobar que la letra coincide con el cálculo
        } catch (NumberFormatException e) {
            return false; // Si no es un número válido, devolver false
        }
    }

    //Maneja el cambio de tema de la aplicación basado en la selección del usuario.
    @FXML
    private void handleThemeChange(ActionEvent event) {
        String selectedTheme = temaComboBox.getSelectionModel().getSelectedItem();
        if (selectedTheme != null) {
            themeManager.applyTheme(selectedTheme);
        }
    }
}
