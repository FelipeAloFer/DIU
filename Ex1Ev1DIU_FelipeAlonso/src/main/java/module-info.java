module com.example.ex1ev1diu_felipealonso {
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

    requires AccesoBBDDExamenV2;

    exports Conversor.controller;
    exports Conversor.modelo;
    exports Conversor.util;

    opens Conversor.controller to javafx.fxml;
    opens Conversor to javafx.fxml;
}