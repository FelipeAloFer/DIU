package GestionHotel.controller;

import GestionHotel.manager.ThemeManager;
import GestionHotel.manager.ThemeManagerImpl;
import GestionHotel.modelo.ExcepcionHotel;
import GestionHotel.modelo.ClienteModelo;
import GestionHotel.modelo.ReservaModelo;
import GestionHotel.modelo.repository.impl.ClienteRepositoryImpl;
import GestionHotel.modelo.repository.impl.ReservaRepositoryImpl;
import GestionHotel.util.Cliente;
import GestionHotel.util.Reserva;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.util.ArrayList;


//Clase principal de la aplicación.
//Se encarga de iniciar la interfaz gráfica y gestionar la vista principal y los diálogos de cliente y reserva.

public class Main extends Application {
    Stage primaryStage;
    BorderPane vistaRaiz;
    ClienteModelo modeloCliente = new ClienteModelo();
    ReservaModelo modeloReserva = new ReservaModelo();
    ClienteRepositoryImpl clienteRepositoryImpl = new ClienteRepositoryImpl();
    ReservaRepositoryImpl reservaRepositoryImpl = new ReservaRepositoryImpl();
    HotelController hotelController;
    ClienteController clienteController;
    ObservableList<Cliente> clientes;
    ThemeManager themeManager;


//Metodo principal que inicia la aplicación.

    @Override
    public void start(Stage primaryStage) throws ExcepcionHotel {
        try {
            this.primaryStage = primaryStage;

            // Cargar el archivo VistaRaiz.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GestionHotel/VistaRaiz.fxml"));
            vistaRaiz = loader.load(); // Asumimos que VistaRaiz.fxml tiene un BorderPane como raíz
            hotelController = loader.getController();

            // Crear la escena con vistaRaiz
            Scene scene = new Scene(vistaRaiz);  // Usamos vistaRaiz para la escena
            primaryStage.setScene(scene);       // Configuramos la escena en el Stage
            primaryStage.setTitle("Gestión Hotel");

            // Crear el ThemeManager con el Stage principal
            themeManager = new ThemeManagerImpl(primaryStage);

            // Registrar la escena en ThemeManager
            themeManager.registerScene(scene);

            // Aplicar el tema predeterminado
            themeManager.applyTheme("Tema Jacobo");


            // Mostrar la vista de personas
            modeloCliente.setHotelModelo(clienteRepositoryImpl);

            modeloReserva.setReservaModelo(reservaRepositoryImpl);
            modeloReserva.obtenerHabitacionesDobles();
            modeloReserva.obtenerHabitacionesIndividuales();
            modeloReserva.obtenerHabitacionesJunior();
            modeloReserva.obtenerHabitacionesSuite();

            hotelController.setMain(this);

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


    //Muestra la vista principal de clientes.

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
            clienteController.setController(modeloCliente, modeloReserva);
            clienteController.setPersona();
            clienteController.setMain(this);
            clienteController.setThemeManager(themeManager);  // Pasar el ThemeManager al controlador

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExcepcionHotel e) {
            throw new RuntimeException(e);
        }
    }


    //Muestra el diálogo de edición de un cliente.

    public boolean showPersonEditDialog(Cliente cliente) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/GestionHotel/VistaDialogoCliente.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar persona");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Registra la escena en ThemeManager
            themeManager.registerScene(scene);

            // Configura el controlador
            DialogoClienteController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(cliente);

            dialogStage.showAndWait();

            // Desregistra la escena al cerrarla
            themeManager.unregisterScene(scene);

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    //Muestra el diálogo de añadir de una reserva.
    public boolean showReservaEditDialog(String dni_cliente, Reserva reserva, int valor) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/GestionHotel/VistaDialogoReserva.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Añadir reserva");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Registra la escena en ThemeManager
            themeManager.registerScene(scene);

            // Configura el controlador
            DialogoReservaController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setModelo(modeloReserva);

            controller.setReserva(dni_cliente, reserva, 0);

            modeloReserva.setReservaModelo(reservaRepositoryImpl);

            dialogStage.showAndWait();

            // Desregistra la escena al cerrarla
            themeManager.unregisterScene(scene);

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Muestra el diálogo de editar de una reserva.
    public boolean showReservaEditDialog(String dni_cliente, Reserva reserva) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/GestionHotel/VistaDialogoReserva.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar reserva");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Registra la escena en ThemeManager
            themeManager.registerScene(scene);

            // Configura el controlador
            DialogoReservaController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setModelo(modeloReserva);

            controller.setReserva(dni_cliente, reserva);

            modeloReserva.setReservaModelo(reservaRepositoryImpl);

            dialogStage.showAndWait();

            // Desregistra la escena al cerrarla
            themeManager.unregisterScene(scene);

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String[] obtenerRutasImagenes(String tipoHabitacion) throws IOException {
        if (tipoHabitacion.equals("Doble")) {
            return new String[]{
                    "file:resources/habitacion_doble_1.jpg",
                    "file:resources/habitacion_doble_2.jpg",
                    "file:resources/habitacion_doble_3.jpg"
            };
        } else if (tipoHabitacion.equals("Individual")) {
            return new String[]{
                    "file:resources/habitacion_individual_1.jpg",
                    "file:resources/habitacion_individual_2.jpg",
                    "file:resources/habitacion_individual_3.jpg"
            };
        } else if (tipoHabitacion.equals("Junior Suite")) {
            return new String[]{
                    "file:resources/habitacion_junior_suite_1.jpg",
                    "file:resources/habitacion_junior_suite_2.jpg",
                    "file:resources/habitacion_junior_suite_3.jpg"
            };
        }
        return new String[]{
                "file:resources/habitacion_suite_1.jpg",
                "file:resources/habitacion_suite_2.jpg",
                "file:resources/habitacion_suite_3.jpg"
        };
    }


    public void mostrarHabitaciones(String tipoHabitacion) throws IOException {
        // Obtener rutas de las imágenes basadas en el tipo de habitación
        String[] rutasImagenes = obtenerRutasImagenes(tipoHabitacion);

        // Cargar el archivo FXML del deslizador
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GestionHotel/VistaHabitaciones.fxml"));
        Parent root = loader.load();

        // Obtener el controlador del deslizador
        HabitacionesController controlador = loader.getController();

        // Configurar las imágenes y otros datos en el controlador
        controlador.setRutasImagenes(rutasImagenes);
        controlador.setReservaModelo(modeloReserva, tipoHabitacion);

        // Crear la escena y configurar la ventana
        Scene escena = new Scene(root, 800, 500);
        Stage ventana = new Stage();
        ventana.setTitle("Galería de " + tipoHabitacion);
        ventana.setScene(escena);
        ventana.initModality(Modality.NONE);
        ventana.initOwner(primaryStage);

        ventana.show();
    }


    public void mostrarEstadisticas(String opcion) throws IOException {
        // Cargar la vista FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GestionHotel/VistaEstadisticas.fxml"));
        Parent root = loader.load();

        // Obtener el controlador de la vista
        EstadisticasController controller2 = loader.getController();

        // Establecer los datos en el gráfico, configurarlo y cambiar el color
        controller2.setReservaModelo(modeloReserva);
        controller2.setMeses(opcion);

        // Crear la escena y configurar la ventana
        Scene scene = new Scene(root, 800, 550);
        Stage stage = new Stage();
        stage.setTitle(opcion);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
    }

    public void mostrarTraductor() throws IOException {
        // Cargar el archivo FXML del traductor
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GestionHotel/VistaTraductor.fxml"));
        AnchorPane root = loader.load(); // Cargar la vista Traductor

        // Obtener el controlador de Traductor
        TraductorController controlador = loader.getController();

        // Crear una nueva escena para el traductor
        Scene escena = new Scene(root, 800, 500);
        Stage ventana = new Stage();
        ventana.setTitle("Traductor");
        ventana.setScene(escena);
        ventana.initModality(Modality.NONE);
        ventana.initOwner(primaryStage);

        ventana.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
