package Agenda.controller;
import Agenda.util.Persona;
import Agenda.modelo.AgendaModelo;
import Agenda.modelo.ExcepcionAgenda;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class PersonaController {
    private AgendaModelo modelo;
    private Main main;
    private ArrayList<Persona> personas;

    @FXML
    private TableView<Persona> personTable;
    @FXML
    private TableColumn<Persona, String> firstNameColumn;
    @FXML
    private TableColumn<Persona, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postcodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    public void setController(AgendaModelo modelo) {
        this.modelo = modelo;
    }
    public void setPersona() throws ExcepcionAgenda {
        personas = modelo.setPersonas();
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public ArrayList<Persona> getPersonas() {
        return personas;
    }

    public void setDatosPersonas(ObservableList<Persona> lista){
        personTable.setItems(lista);
    }

    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        lastNameColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getApellido()));

        // Clear person details.
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    private void showPersonDetails(Persona persona) {
        if (persona != null) {
            // Fill the labels with info from the person object.
            firstNameLabel.setText(persona.getNombre());
            lastNameLabel.setText(persona.getApellido());
            streetLabel.setText(persona.getCalle());
            cityLabel.setText(persona.getCiudad());
            postcodeLabel.setText(persona.getCodigoPostal());
            birthdayLabel.setText(persona.getFechaNacimiento());
        } else {
            // Person is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            cityLabel.setText("");
            postcodeLabel.setText("");
            birthdayLabel.setText("");
        }
    }

    @FXML
    private Persona handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Persona selectedPerson = personTable.getItems().get(selectedIndex);
            try {
                modelo.borrarPersona(selectedPerson); // Borra de la base de datos
                personTable.getItems().remove(selectedIndex); // Borra de la tabla
            } catch (ExcepcionAgenda e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error al eliminar la persona: " + e.getMessage());
                alert.show();
            }
            return selectedPerson;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Debes seleccionar una persona para borrarla");
            alert.show();
            return null;
        }
    }



    @FXML
    private void handleNewPerson() {
        Persona personaNueva = new Persona();
        boolean okClicked = main.showPersonEditDialog(personaNueva);  // Llama al diálogo de edición

        if (okClicked) {
            try {
                modelo.añadirPersona(personaNueva); // Guarda en la base de datos
                personTable.getItems().add(personaNueva); // Añade a la tabla
            } catch (ExcepcionAgenda e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @FXML
    private void handleEditPerson() {
        Persona selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = main.showPersonEditDialog(selectedPerson);  // Llama al diálogo de edición
            if (okClicked) {
                try {
                    modelo.modificarPersona(selectedPerson); // Guarda los cambios en la base de datos
                    showPersonDetails(selectedPerson); // Actualiza los detalles en pantalla
                } catch (ExcepcionAgenda e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Debes seleccionar una persona para editarla");
            alert.show();
        }
    }

}