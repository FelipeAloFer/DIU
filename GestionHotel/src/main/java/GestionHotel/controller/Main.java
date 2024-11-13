package GestionHotel.controller;

import GestionHotel.manager.ThemeManager;
import GestionHotel.manager.ThemeManagerImpl;
import GestionHotel.modelo.ExcepcionHotel;
import GestionHotel.modelo.ClienteModelo;
import GestionHotel.modelo.ReservaModelo;
import GestionHotel.modelo.repository.impl.ClienteRepositoryImpl;
import GestionHotel.modelo.repository.impl.ReservaRepositoryImpl;
import GestionHotel.util.Cliente;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    Stage primaryStage;
    BorderPane vistaRaiz;
    ClienteModelo modelo = new ClienteModelo();
    ReservaModelo reserva = new ReservaModelo();
    ClienteRepositoryImpl clienteRepositoryImpl = new ClienteRepositoryImpl();
    ReservaRepositoryImpl reservaRepositoryImpl = new ReservaRepositoryImpl();
    ClienteController clienteController;
    ObservableList<Cliente> clientes;
    ThemeManager themeManager;

    @Override
    public void start(Stage primaryStage) throws ExcepcionHotel {
        try {
            this.primaryStage = primaryStage;

            // Crear el ThemeManager con el Stage principal
            themeManager = new ThemeManagerImpl(primaryStage);

            // Cargar el archivo VistaRaiz.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GestionHotel/VistaRaiz.fxml"));
            vistaRaiz = loader.load();  // Asumimos que VistaRaiz.fxml tiene un BorderPane como raíz

            Scene scene = new Scene(vistaRaiz);  // Usamos vistaRaiz para la escena
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestión Hotel");

            // Mostrar la vista de personas
            modelo.setHotelModelo(clienteRepositoryImpl);
            reserva.setReservaModelo(reservaRepositoryImpl);
            showClienteOverview();
            primaryStage.show();

            // Obtener la lista de personas
            ArrayList<Cliente> listaPersonas = clienteController.getPersonas(); // Llama internamente a modelo.setPersonas()

            // Verificar si la lista es null antes de crear el ObservableList
            if (listaPersonas != null) {
                clientes = FXCollections.observableArrayList(listaPersonas);
                clienteController.setDatosPersonas(clientes);
            } else {
                System.out.println("No se pudo obtener la lista de personas debido a un error de conexión con la base de datos.");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void showClienteOverview() {
        try {
            // Cargar Personas.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/GestionHotel/VistaCliente.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Establecer el AnchorPane en el centro del BorderPane rootLayout
            vistaRaiz.setCenter(personOverview);

            // Si necesitas interactuar con el controlador:
            clienteController = loader.getController();
            clienteController.setController(modelo, reserva);
            clienteController.setPersona();
            clienteController.setMain(this);
            clienteController.setThemeManager(themeManager);  // Pasar el ThemeManager al controlador

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExcepcionHotel e) {
            throw new RuntimeException(e);
        }
    }

    public boolean showPersonEditDialog(Cliente cliente) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/GestionHotel/VistaDialogoCliente.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar persona");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            DialogoClienteController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(cliente);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
