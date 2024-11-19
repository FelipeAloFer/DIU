package GestionHotel.controller;

import GestionHotel.util.Reserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

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

    @FXML
    private void initialize() {
        // Configuración inicial del Spinner
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1); // Rango: 1 a 10, inicial: 1
        numeroHabitacionesSpinner.setValueFactory(valueFactory);

        // Inicializa el ComboBox con los valores de tipo de habitación
        ObservableList<String> tipoHabitacionOptions = FXCollections.observableArrayList(
                "DOBLE_DE_USO_INDIVIDUAL", "DOBLE", "JUNIOR_SUITE", "SUITE"
        );
        tipoHabitacionComboBox.setItems(tipoHabitacionOptions);

        ObservableList<String> tipoAlojamientoOptions = FXCollections.observableArrayList(
                "ALOJAMIENTO_Y_DESAYUNO", "MEDIA_PENSION", "PENSION_COMPLETA"
        );
        tipoAlojamientoComboBox.setItems(tipoAlojamientoOptions);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

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

    public boolean isOkClicked() {
        return okClicked;
    }

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

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (dniClienteField.getText() == null || dniClienteField.getText().isEmpty()) {
            errorMessage += "DNI no válido!\n";
        }
        if (idReservaField.getText() == null || idReservaField.getText().isEmpty()) {
            errorMessage += "ID no válido!\n";
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
            // Mostrar el mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}
