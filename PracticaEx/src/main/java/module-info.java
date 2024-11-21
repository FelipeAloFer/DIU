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

    exports PracticaEx.controller;
    exports PracticaEx.modelo;
    exports PracticaEx.util;

    opens PracticaEx.controller to javafx.fxml; // Abre el paquete controller para FXML
    opens PracticaEx to javafx.fxml; // Esto abre el paquete general PracticaEx
}
