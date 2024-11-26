package GestionHotel.controller;

import GestionHotel.util.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


//Controlador para la ventana de diálogo de edición de cliente.
//Permite al usuario editar los detalles de un cliente y validar la entrada.

public class DialogoClienteController {

    @FXML
    private TextField dniField;

    @FXML
    private TextField nombreField;

    @FXML
    private TextField apellidosField;

    @FXML
    private TextField direccionField;

    @FXML
    private TextField localidadField;

    @FXML
    private TextField provinciaField;

    private Stage dialogStage;
    private Cliente cliente;
    private boolean okClicked = false;

    //Metodo de inicialización, se llama automáticamente al cargar el FXML.
    @FXML
    private void initialize() {
    }

    //Establece la ventana de diálogo.
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    //Establece los datos del cliente cuando vamos a editarlo
    public void setPerson(Cliente cliente) {
        this.cliente = cliente;

        dniField.setText(cliente.getDni());
        nombreField.setText(cliente.getNombre());
        apellidosField.setText(cliente.getApellidos());
        direccionField.setText(cliente.getDireccion());
        localidadField.setText(cliente.getLocalidad());
        provinciaField.setText(cliente.getProvincia());
    }

    // Indica si se ha presionado el botón OK.
    // Devuelve true si se ha presionado OK, false de lo contrario.
    public boolean isOkClicked() {
        return okClicked;
    }

    // Maneja la acción del botón OK.
    // Si la entrada es válida, actualiza el cliente y cierra la ventana.
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            cliente.setDni(dniField.getText());
            cliente.setNombre(nombreField.getText());
            cliente.setApellidos(apellidosField.getText());
            cliente.setDireccion(direccionField.getText());
            cliente.setLocalidad(localidadField.getText());
            cliente.setProvincia(provinciaField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    // Maneja la acción del botón Cancelar.
    // Cierra la ventana sin hacer cambios.
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    // Valida la entrada del usuario.
    // devuelve true si todos los campos son válidos, false si alguno no lo es.
    private boolean isInputValid() {
        String errorMessage = "";

        if (dniField.getText() == null || dniField.getText().length() == 0) {
            errorMessage += "DNI no válido!\n";
        }
        if (nombreField.getText() == null || nombreField.getText().length() == 0) {
            errorMessage += "Nombre no válido!\n";
        }
        if (apellidosField.getText() == null || apellidosField.getText().length() == 0) {
            errorMessage += "Apellidos no válidos!\n";
        }
        if (direccionField.getText() == null || direccionField.getText().length() == 0) {
            errorMessage += "Dirección no válida!\n";
        }

        if (localidadField.getText() == null || localidadField.getText().length() == 0) {
            errorMessage += "Localidad no válida!\n";
        }

        if (provinciaField.getText() == null || provinciaField.getText().length() == 0) {
            errorMessage += "Provincia no válida!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage);
            alert.show();
            return false;
        }
    }
}
