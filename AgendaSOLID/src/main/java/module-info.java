module com.example.agendasolid {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires AccesoBBDDMoneda;

    exports Agenda.controller;
    exports Agenda.modelo;
    exports Agenda.util;

    opens Agenda.controller to javafx.fxml; // Abre el paquete controller para FXML
    opens Agenda to javafx.fxml; // Esto abre el paquete general Agenda
}
