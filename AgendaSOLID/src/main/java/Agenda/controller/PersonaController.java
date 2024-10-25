package Agenda.controller;
import Agenda.util.Persona;
import Agenda.modelo.AgendaModelo;
import Agenda.modelo.ExcepcionAgenda;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class PersonaController {
    private AgendaModelo modelo;
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

    // Reference to the main application.
    private Main main;

    public void setController(AgendaModelo modelo) {
        this.modelo = modelo;
    }
    public void setPersona() throws ExcepcionAgenda {
        personas = modelo.setPersonas();
    }

    public ArrayList<Persona> getPersonas() {
        return personas;
    }
    public void setData(ObservableList<Persona> lista){
        personTable.setItems(lista);
    }

    public void setMainApp(Main main) {
        this.main = main;

        // Add observable list data to the table
        personTable.setItems(main.getPersonData());
    }
}

//@FXML
//private void handleDeletePerson() {
//    int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
//    if (selectedIndex >= 0) {
//        Persona selectedPerson = personTable.getItems().get(selectedIndex);
//        // Borrar persona de la base de datos
//        // personaRepository.delete(selectedPerson);
//        personTable.getItems().remove(selectedIndex);
//    } else {
//        showAlert("Debes tener seleccionada a una persona para borrarla");
//    }
//}
//
//@FXML
//private void handleNewPerson() {
//    Persona tempPerson = new Persona();
//    boolean okClicked = main.showPersonEditDialog(tempPerson);
//    if (okClicked) {
//        // Añadir a la base de datos
//        // personaRepository.create(tempPerson);
//        personTable.getItems().add(tempPerson); // Lo añadimos a la tabla
//    }
//}
//
//@FXML
//private void handleEditPerson() {
//    Persona selectedPerson = personTable.getSelectionModel().getSelectedItem();
//    if (selectedPerson != null) {
//        boolean okClicked = main.showPersonEditDialog(selectedPerson);
//        if (okClicked) {
//            // Actualizamos en la base de datos
//            // personaRepository.update(selectedPerson);
//            // showPersonDetails(selectedPerson);
//        }
//    } else {
//        showAlert("Debes seleccionar a una persona para editarla");
//    }
//}