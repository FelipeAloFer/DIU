package GestionHotel.controller;

import GestionHotel.util.Reserva;
import javafx.fxml.FXML;
import GestionHotel.util.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Date;


public class DialogoReservaController {

    @FXML
    private TextField dniClienteField;

    @FXML
    private TextField idReservaField;

    @FXML
    private TextField fechaLlegadaField;

    @FXML
    private TextField fechaSalidaField;

    @FXML
    private TextField numHabitacionesField;

    @FXML
    private ComboBox<String> tipoHabitacionComboBox;

    @FXML
    private TextField fumadorField;

    @FXML
    private ComboBox<String> tipoAlojamientoComboBox;

    private Stage dialogStage;
    private Reserva reserva;
    private boolean okClicked = false;

    @FXML
    private void initialize() {

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

//    public void setReserva(Reserva reserva) {
//        this.reserva = reserva;
//
//        dniClienteField.setText(reserva.getDni_cliente());
//        idReservaField.setText(String.valueOf(reserva.getIdReserva()));
//        fechaLlegadaField.setText(String.valueOf(reserva.getFecha_llegada()));
//        fechaSalidaField.setText(String.valueOf(reserva.getFecha_salida()));
//        numHabitacionesField.setText(String.valueOf(reserva.getNum_habitaciones()));
//        tipoHabitacionComboBox.set(String.valueOf(reserva.getTipo_habitacion()));
//        fumadorField.setText(String.valueOf(reserva.isFumador()));
//        tipoAlojamientoComboBox.setText(String.valueOf(reserva.getTipo_alojamiento()));
//    }


//    @FXML
//    private void handleOk() {
//        if (isInputValid()) {
//            reserva.setDni_cliente(dniClienteField.getText());
//            reserva.setIdReserva(Integer.valueOf(idReservaField.getText()));
//            reserva.setFecha_llegada(Date.valueOf(fechaLlegadaField.getText()));
//            reserva.setFecha_salida(Date.valueOf(fechaSalidaField.getText()));
//            reserva.setNum_habitaciones(Integer.valueOf(numHabitacionesField.getText()));
//            reserva.setTipo_habitacion(tipoHabitacionField.getText());
//            reserva.setFumador(Boolean.valueOf(fumadorField.getText()));
//            reserva.setTipo_alojamiento(tipoAlojamientoField.getText());
//
//            okClicked = true;
//            dialogStage.close();
//        }
//    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

//    private boolean isInputValid() {
//        String errorMessage = "";
//
//        if (dniClienteField.getText() == null || dniClienteField.getText().length() == 0) {
//            errorMessage += "DNI no valido!\n";
//        }
//        if (idReservaField.getText() == null || idReservaField.getText().length() == 0) {
//            errorMessage += "ID no valido!\n";
//        }
//        if (fechaLlegadaField.getText() == null || fechaLlegadaField.getText().length() == 0) {
//            errorMessage += "Fecha de llegada no valida!\n";
//        }
//        if (fechaLlegadaField.getText() == null || fechaSalidaField.getText().length() == 0) {
//            errorMessage += "Fecha de salida no valida!\n";
//        }
//
//        if (numHabitacionesField.getText() == null || numHabitacionesField.getText().length() == 0) {
//            errorMessage += "Numero de Habitaciones no valida!\n";
//        }
//
//        if (tipoHabitacionField.getText() == null || fumadorField.getText().length() == 0) {
//            errorMessage += "Tipo de Habitacion no valida!\n";
//        }
//
//        if (fumadorField.getText() == null || fumadorField.getText().length() == 0) {
//            errorMessage += "Provincia no valida!\n";
//        }
//
//        if (tipoAlojamientoField.getText() == null || tipoAlojamientoField.getText().length() == 0) {
//            errorMessage += "Provincia no valida!\n";
//        }
//
//        if (errorMessage.length() == 0) {
//            return true;
//        } else {
//            // Show the error message.
//            Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage);
//            alert.show();
//            return false;
//        }
//    }
}
