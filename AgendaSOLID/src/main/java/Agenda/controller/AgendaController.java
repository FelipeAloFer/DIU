package Agenda.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class AgendaController {
    Main agenda = new Main();

    @FXML
    private void handleShowBirthdayStatistics() {
        agenda.showBirthdayStatistics();
    }
}

