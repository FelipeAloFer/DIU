package Agenda.controller;


import Agenda.modelo.AgendaModelo;
import Agenda.modelo.ExcepcionAgenda;
import Agenda.modelo.repository.impl.PersonaRepositoryImpl;
import Agenda.util.Persona;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;



public class Main extends Application {
    Stage primaryStage;
    PersonaRepositoryImpl personaRepository = new PersonaRepositoryImpl();
    AgendaModelo modelo = new AgendaModelo();
    PersonaController controller;
    BorderPane vistaRaiz;
    ObservableList<Persona> personas;
    boolean isOkClicked = false;

    public boolean isOkClicked() {
        return isOkClicked;
    }

    public void setOkClicked(boolean okClicked) {
        isOkClicked = okClicked;
    }

    @Override
    public void start (Stage primaryStage) throws ExcepcionAgenda {
        try {
            // Cargar el archivo Principal.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Agenda/VistaRaiz.fxml"));
            vistaRaiz = (BorderPane) loader.load();  // Asumimos que Principal.fxml tiene un BorderPane como raíz

            Scene scene = new Scene(vistaRaiz);  // Usamos rootLayout para la escena
            primaryStage.setScene(scene);
            primaryStage.setTitle("Agenda");

            // Mostrar la vista de personas
            modelo.setAgendaModelo(personaRepository);
            showPersonaOverview();
            primaryStage.show();
            personas = FXCollections.observableArrayList(controller.getPersonas());
            System.out.println(personas);
            controller.setDatosPersonas(personas);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void showPersonaOverview() {
        try {
            // Cargar Personas.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Agenda/VistaPersona.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Establecer el AnchorPane en el centro del BorderPane rootLayout
            vistaRaiz.setCenter(personOverview);

            // Si necesitas interactuar con el controlador:
            controller = loader.getController();
            controller.setController(modelo);
            controller.setPersona();
            controller.setMain(this);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExcepcionAgenda e) {
            throw new RuntimeException(e);
        }
    }

    public void showBirthdayStatistics() {
        try {
            // Load the fxml file and create a new stage for the popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/Agenda/VistaCumpleaños.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Birthday Statistics");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the persons into the controller.
            CumpleañosController controller = loader.getController();
            controller.setDatosPersona(personas);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showPersonEditDialog(Persona persona) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/Agenda/VistaDialogo.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar persona");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            DialogoController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(persona);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}