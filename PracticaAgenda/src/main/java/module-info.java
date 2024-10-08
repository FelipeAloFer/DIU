module pack.scenebuilder {
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

    opens pack.scenebuilder to javafx.fxml;
    exports pack.scenebuilder;
    exports Agenda;
    opens Agenda to javafx.fxml;
    exports Agenda.controller;
    opens Agenda.controller to javafx.fxml;
}