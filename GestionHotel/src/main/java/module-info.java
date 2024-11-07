module com.example.gestionhotel {
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

    exports GestionHotel.controller;
    exports GestionHotel.modelo;
    exports GestionHotel.util;

    opens GestionHotel.controller to javafx.fxml;
}