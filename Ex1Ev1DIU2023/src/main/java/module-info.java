module com.example.PracticaFS {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires Modelo;

    opens com.example.PracticaFS to javafx.fxml;
    exports com.example.PracticaFS;
    opens com.example.PracticaFS.controller to javafx.fxml;
    exports com.example.PracticaFS.controller;
}