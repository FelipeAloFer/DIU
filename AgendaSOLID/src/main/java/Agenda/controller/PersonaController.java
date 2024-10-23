package Agenda.controller;

import Agenda.modelo.ExcepcionAgenda;
import Agenda.modelo.PersonaVO;
import Agenda.modelo.repository.PersonaRepository;
import Agenda.modelo.repository.impl.PersonaRepositoryImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PersonaController {
    @FXML
    private TableView<PersonaVO> personTable;
    @FXML
    private TableColumn<PersonaVO, String> firstNameColumn;
    @FXML
    private TableColumn<PersonaVO, String> lastNameColumn;

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
    private PersonaRepository personaRepository;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonaController() {
        // Inicializamos el repositorio
        personaRepository = new PersonaRepositoryImpl();
    }

    private void showPersonDetails(PersonaVO persona) {
        if (persona != null) {
            firstNameLabel.setText(persona.getNombre());
            lastNameLabel.setText(persona.getApellido());
            streetLabel.setText(persona.getCalle());
            postcodeLabel.setText(persona.getCodigoPostal());
            cityLabel.setText(persona.getCiudad());
            birthdayLabel.setText(persona.getFechaNacimiento());
        } else {
            clearPersonDetails();
        }
    }

    private void clearPersonDetails() {
        firstNameLabel.setText("");
        lastNameLabel.setText("");
        streetLabel.setText("");
        postcodeLabel.setText("");
        cityLabel.setText("");
        birthdayLabel.setText("");
    }

    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            PersonaVO selectedPerson = personTable.getItems().get(selectedIndex);
            // Borrar persona de la base de datos
            // personaRepository.delete(selectedPerson);
            personTable.getItems().remove(selectedIndex);
        } else {
            showAlert("Debes tener seleccionada a una persona para borrarla");
        }
    }

    @FXML
    private void handleNewPerson() {
        PersonaVO tempPerson = new PersonaVO();
        boolean okClicked = main.showPersonEditDialog(tempPerson);
        if (okClicked) {
            // Añadir a la base de datos
            // personaRepository.create(tempPerson);
            personTable.getItems().add(tempPerson); // Lo añadimos a la tabla
        }
    }

    @FXML
    private void handleEditPerson() {
        PersonaVO selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = main.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                // Actualizamos en la base de datos
                // personaRepository.update(selectedPerson);
                // showPersonDetails(selectedPerson);
            }
        } else {
            showAlert("Debes seleccionar a una persona para editarla");
        }
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() throws ExcepcionAgenda {
        // Inicializamos las columnas de la tabla con los valores de la persona
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        // Limpiamos los detalles de la persona
        showPersonDetails(null);

        // Escuchamos los cambios de selección y mostramos los detalles cuando cambia la selección
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));

        // Cargamos los datos desde la base de datos
        cargaBaseDeDatos();
    }

    private void cargaBaseDeDatos() throws ExcepcionAgenda {
        ObservableList<PersonaVO> personData = FXCollections.observableArrayList(personaRepository.ObtenerListaPersonas());
        personTable.setItems(personData);
    }

    public void setMainApp(Main main) {
        this.main = main;

        // Add observable list data to the table
        personTable.setItems(main.getPersonData());
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.show();
    }
}
