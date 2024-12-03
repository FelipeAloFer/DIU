package GestionHotel.controller;

import GestionHotel.modelo.ReservaModelo;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HabitacionesController {

    @FXML
    private ImageView vistaImagen; // ImageView para mostrar la imagen actual

    @FXML
    private Button botonIzquierda; // Botón para navegar a la imagen anterior

    @FXML
    private Button botonDerecha; // Botón para navegar a la siguiente imagen

    @FXML
    private Button botonVolver; // Botón para volver a la pantalla anterior

    @FXML
    private ProgressBar barraProgreso; // Barra de progreso

    private int indiceImagen = 0; // Índice de la imagen actual
    private String[] rutasImagenes; // Array con las rutas de las imágenes
    private ReservaModelo reservaModelo;

    @FXML
    public void initialize() {
        configurarBotones();
    }

    public void setReservaModelo(ReservaModelo reservaModelo, String opcion) {
        this.reservaModelo = reservaModelo;
        this.setProgresos(opcion);
    }

    private void setProgresos(String opcion){
        switch (opcion) {
            case "Doble":
                barraProgreso.progressProperty().bind(
                        reservaModelo.habitacionesDoblesProperty().divide(50)
                );
                break;

            case "Individual":
                barraProgreso.progressProperty().bind(
                        reservaModelo.habitacionesIndividualesProperty().divide(50)
                );
                break;

            case "Junior Suite":
                barraProgreso.progressProperty().bind(
                        reservaModelo.habitacionesJuniorProperty().divide(50)
                );
                break;

            case "Suite":
                barraProgreso.progressProperty().bind(
                        reservaModelo.habitacionesSuiteProperty().divide(50)
                );
                break;

            default:
                throw new IllegalArgumentException("Opción no válida: " + opcion);
        }
    }

    public void setRutasImagenes(String[] rutasImagenes) {
        this.rutasImagenes = rutasImagenes;
        cargarImagen(); // Cargar la primera imagen
    }


    private void cargarImagen() {
        if (rutasImagenes != null && rutasImagenes.length > 0) {
            // Cargar la imagen actual en el ImageView
            Image imagen = new Image(rutasImagenes[indiceImagen]);
            vistaImagen.setImage(imagen);
            vistaImagen.setFitWidth(400);
            vistaImagen.setFitHeight(300);
        }
    }

    @FXML
    private void cambiarImagenAnterior() {
        if (rutasImagenes != null && rutasImagenes.length > 0) {
            // Retroceder al índice anterior, con bucle al final
            indiceImagen = (indiceImagen - 1 + rutasImagenes.length) % rutasImagenes.length;
            realizarTransicionImagen();
        }
    }

    @FXML
    private void cambiarImagenSiguiente() {
        if (rutasImagenes != null && rutasImagenes.length > 0) {
            // Avanzar al siguiente índice, con bucle al inicio
            indiceImagen = (indiceImagen + 1) % rutasImagenes.length;
            realizarTransicionImagen();
        }
    }

    private void realizarTransicionImagen() {
        // Crear una transición de desvanecimiento para la imagen
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), vistaImagen);
        fadeOut.setFromValue(1); // Comienza visible
        fadeOut.setToValue(0); // Termina invisible

        fadeOut.setOnFinished(e -> {
            cargarImagen(); // Cambiar la imagen
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), vistaImagen);
            fadeIn.setFromValue(0); // Comienza invisible
            fadeIn.setToValue(1); // Termina visible
            fadeIn.play(); // Reproducir transición de entrada
        });

        fadeOut.play(); // Reproducir transición de salida
    }

    private void configurarBotones() {
        botonIzquierda.setOnAction(e -> cambiarImagenAnterior());
        botonDerecha.setOnAction(e -> cambiarImagenSiguiente());
    }

    @FXML
    private void volverPantallaAnterior() {
        // Cerrar la ventana actual
        Stage stage = (Stage) botonVolver.getScene().getWindow();
        stage.close();
    }
}

