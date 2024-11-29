package GestionHotel.controller;

import GestionHotel.modelo.ClienteModelo;
import GestionHotel.modelo.ReservaModelo;
import GestionHotel.util.Reserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;


//Controlador para la ventana de diálogo de edición de reserva.
//Permite al usuario editar los detalles de una reserva y validar la entrada.

public class DialogoReservaController {

    @FXML
    private TextField dniClienteField;

    @FXML
    private TextField idReservaField;

    @FXML
    private DatePicker fechaLlegadaPicker;

    @FXML
    private DatePicker fechaSalidaPicker;

    @FXML
    private Spinner<Integer> numeroHabitacionesSpinner;

    @FXML
    private ComboBox<String> tipoHabitacionComboBox;

    @FXML
    private CheckBox fumadorCheckBox;

    @FXML
    private ComboBox<String> tipoAlojamientoComboBox;

    private Stage dialogStage;
    private Reserva reserva;
    private boolean okClicked = false;
    private ReservaModelo reservaModelo;

    //Metodo de inicialización, se llama automáticamente al cargar el FXML.
    @FXML
    private void initialize() {
        // Configuración inicial del Spinner para el número de habitaciones.
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1); // Rango: 1 a 10, valor inicial: 1
        numeroHabitacionesSpinner.setValueFactory(valueFactory);

        // Inicializa el ComboBox con las opciones de tipo de habitación.
        ObservableList<String> tipoHabitacionOptions = FXCollections.observableArrayList(
                "DOBLE_DE_USO_INDIVIDUAL", "DOBLE", "JUNIOR_SUITE", "SUITE"
        );
        tipoHabitacionComboBox.setItems(tipoHabitacionOptions);

        // Inicializa el ComboBox con las opciones de tipo de alojamiento.
        ObservableList<String> tipoAlojamientoOptions = FXCollections.observableArrayList(
                "ALOJAMIENTO_Y_DESAYUNO", "MEDIA_PENSION", "PENSION_COMPLETA"
        );
        tipoAlojamientoComboBox.setItems(tipoAlojamientoOptions);
    }

    // Establece la ventana de diálogo.
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setModelo(ReservaModelo modelo) {
        this.reservaModelo = modelo;
    }

    // Establece la reserva al crearla nueva
    public void setReserva(String dni_cliente, Reserva reserva, int valor) {
        this.reserva = reserva;

        // Asigna el DNI del cliente al campo de texto
        dniClienteField.setText(dni_cliente);

        idReservaField.setText(String.valueOf(reservaModelo.ultimoIDReserva() + 1));

        fechaLlegadaPicker.setValue(LocalDate.now());
        fechaSalidaPicker.setValue(LocalDate.now().plusDays(2));

        numeroHabitacionesSpinner.getValueFactory().setValue(1);

        if (!tipoHabitacionComboBox.getItems().isEmpty()) {
            tipoHabitacionComboBox.setValue(tipoHabitacionComboBox.getItems().get(0)); // Selecciona el primero
        }

        fumadorCheckBox.setSelected(false);

        if (!tipoAlojamientoComboBox.getItems().isEmpty()) {
            tipoAlojamientoComboBox.setValue(tipoAlojamientoComboBox.getItems().get(0)); // Selecciona el primero
        }
    }


    // Establece la reserva a editar.
    public void setReserva(String dni_cliente, Reserva reserva) {
        this.reserva = reserva;

        dniClienteField.setText(dni_cliente);
        idReservaField.setText(String.valueOf(reserva.getIdReserva()));
        fechaLlegadaPicker.setValue(reserva.getFecha_llegada());
        fechaSalidaPicker.setValue(reserva.getFecha_salida());
        numeroHabitacionesSpinner.getValueFactory().setValue(reserva.getNum_habitaciones());
        tipoHabitacionComboBox.setValue(reserva.getTipo_habitacion());
        fumadorCheckBox.setSelected(reserva.isFumador());
        tipoAlojamientoComboBox.setValue(reserva.getTipo_alojamiento());
    }

    public void handleLimpiar() {
        // Limpiar los DatePickers
        fechaLlegadaPicker.setValue(null);
        fechaSalidaPicker.setValue(null);

        // Limpiar los ComboBoxes
        tipoHabitacionComboBox.getSelectionModel().clearSelection();
        tipoAlojamientoComboBox.getSelectionModel().clearSelection();

        // Desmarcar el CheckBox
        fumadorCheckBox.setSelected(false);
    }


    // Indica si se ha presionado el botón OK.
    // Devuelve true si se ha presionado OK, false de lo contrario.
    public boolean isOkClicked() {
        return okClicked;
    }

    // Maneja la acción del botón OK.
    // Si la entrada es válida, actualiza la reserva y cierra la ventana.
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            reserva.setDni_cliente(dniClienteField.getText());
            reserva.setIdReserva(Integer.parseInt(idReservaField.getText()));
            reserva.setFecha_llegada(fechaLlegadaPicker.getValue());
            reserva.setFecha_salida(fechaSalidaPicker.getValue());
            reserva.setNum_habitaciones(numeroHabitacionesSpinner.getValue());
            reserva.setTipo_habitacion(tipoHabitacionComboBox.getValue());
            reserva.setFumador(fumadorCheckBox.isSelected());
            reserva.setTipo_alojamiento(tipoAlojamientoComboBox.getValue());

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
    // Devuelve true si todos los campos son válidos, false si alguno no lo es.
    private boolean isInputValid() {
        String errorMessage = "";

        if (dniClienteField.getText() == null || dniClienteField.getText().isEmpty()) {
            errorMessage += "DNI no válido!\n";
        }
        if (fechaLlegadaPicker.getValue() == null) {
            errorMessage += "Fecha de llegada no válida!\n";
        }
        if (fechaSalidaPicker.getValue() == null) {
            errorMessage += "Fecha de salida no válida!\n";
        }
        if (numeroHabitacionesSpinner.getValue() == null || numeroHabitacionesSpinner.getValue() <= 0) {
            errorMessage += "Número de habitaciones no válido!\n";
        }
        if (tipoHabitacionComboBox.getValue() == null || tipoHabitacionComboBox.getValue().isEmpty()) {
            errorMessage += "Tipo de habitación no válido!\n";
        }
        if (tipoAlojamientoComboBox.getValue() == null || tipoAlojamientoComboBox.getValue().isEmpty()) {
            errorMessage += "Tipo de alojamiento no válido!\n";
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            // Mostrar el mensaje de error en un cuadro de alerta.
            Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}
