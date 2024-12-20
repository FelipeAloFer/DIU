module com.example.practicafs {
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
    requires Modelo; // Dependencia que mencionaste.
    requires java.sql; // ¡Nuevo módulo añadido!

    opens PracticaFS to javafx.fxml;
    opens PracticaFS.controller to javafx.fxml;
    exports PracticaFS.controller to javafx.fxml;
    exports PracticaFS;
}
