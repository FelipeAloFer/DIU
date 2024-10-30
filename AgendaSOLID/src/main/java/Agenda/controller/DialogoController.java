package Agenda.controller;

import Agenda.util.Persona;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DialogoController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField streetField;

    @FXML
    private TextField cityField;

    @FXML
    private TextField postalCodeField;

    @FXML
    private TextField birthdayField;

    @FXML
    private ProgressBar barraProgresoEdit;


    private Stage dialogStage;
    private Persona persona;
    private boolean okClicked = false;


    @FXML
    private void initialize() {

    }

    public ProgressBar setBarraProgreso () {
        return barraProgresoEdit;
    }

    public void actualizarBarraProgreso(ObservableList<Persona> lista) {
        barraProgresoEdit.setProgress(lista.size()*0.02);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPerson(Persona persona) {
        this.persona = persona;

        firstNameField.setText(persona.getNombre());
        lastNameField.setText(persona.getApellido());
        streetField.setText(persona.getCalle());
        cityField.setText(persona.getCiudad());
        postalCodeField.setText(persona.getCodigoPostal());
        birthdayField.setText(persona.getFechaNacimiento());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            persona.setNombre(firstNameField.getText());
            persona.setApellido(lastNameField.getText());
            persona.setCalle(streetField.getText());
            persona.setCiudad(cityField.getText());
            persona.setCodigoPostal(postalCodeField.getText());
            persona.setFechaNacimiento(birthdayField.getText());

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

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }

        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "No valid city!\n";
        }

        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(postalCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n";
            }
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage);
            alert.show();
            return false;
        }
    }
}
