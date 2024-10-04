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
    exports pack.scenebuilder.controller;
    opens pack.scenebuilder.controller to javafx.fxml;
}