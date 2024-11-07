package GestionHotel.controller;

import GestionHotel.modelo.ExcepcionHotel;
import GestionHotel.modelo.HotelModelo;
import GestionHotel.modelo.repository.impl.HotelRepositoryImpl;
import GestionHotel.util.Cliente;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    BorderPane vistaRaiz;
    HotelModelo modelo = new HotelModelo();
    HotelRepositoryImpl personaRepository = new HotelRepositoryImpl();
    ClienteController clienteController;
    ObservableList<Cliente> clientes;


    @Override
    public void start(Stage primaryStage) throws ExcepcionHotel {
        try {
            // Cargar el archivo VistaRaiz.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Agenda/VistaRaiz.fxml"));
            vistaRaiz = loader.load();  // Asumimos que VistaRaiz.fxml tiene un BorderPane como raíz

            Scene scene = new Scene(vistaRaiz);  // Usamos vistaRaiz para la escena
            primaryStage.setScene(scene);
            primaryStage.setTitle("Agenda");

            // Mostrar la vista de personas
            modelo.setHotelModelo(personaRepository);
            showPersonaOverview();
            primaryStage.show();

            // Obtener la lista de personas
            ArrayList<Cliente> listaPersonas = clienteController.getPersonas(); // Llama internamente a modelo.setPersonas()

            // Verificar si la lista es null antes de crear el ObservableList
            if (listaPersonas != null) {
                clientes = FXCollections.observableArrayList(listaPersonas);
                clienteController.setDatosPersonas(clientes);
            } else {
                System.out.println("No se pudo obtener la lista de personas debido a un error de conexión con la base de datos.");
                // Podrías manejar algún caso alternativo si la lista es null
            }
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
            clienteController = loader.getController();
            clienteController.setController(modelo);
            clienteController.setPersona();
            clienteController.setMain(this);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExcepcionHotel e) {
            throw new RuntimeException(e);
        }
    }
}
