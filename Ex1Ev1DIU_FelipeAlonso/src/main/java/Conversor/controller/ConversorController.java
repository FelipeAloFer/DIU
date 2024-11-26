package Conversor.controller;

import javafx.fxml.FXML;

public class ConversorController {
    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private void handleShowVentana() {
        main.showVentana();
    }
}