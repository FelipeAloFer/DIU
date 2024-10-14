package Agenda.controller;

import javafx.fxml.FXML;

public class RootLayoutController {
    MainApp agenda=new MainApp();
    @FXML
    private void handleShowBirthdayStatistics() {
        agenda.showBirthdayStatistics();
    }
}
