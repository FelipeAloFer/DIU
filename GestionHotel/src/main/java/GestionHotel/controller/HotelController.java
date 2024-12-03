package GestionHotel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

import java.io.IOException;

public class HotelController {
    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private void abrirMostrarHabitaciones(ActionEvent event) throws IOException {
        MenuItem menuItem = (MenuItem) event.getSource();
        String menuItemText = menuItem.getText();
        main.mostrarHabitaciones(menuItemText);
    }

    @FXML
    private void abrirMostrarEstadisticas(ActionEvent event) throws IOException {
        MenuItem menuItem = (MenuItem) event.getSource();
        String menuItemText = menuItem.getText();
        main.mostrarEstadisticas(menuItemText);
    }

    @FXML
    private void abrirTraductor(ActionEvent event) throws IOException {
        main.mostrarTraductor();
    }
}
