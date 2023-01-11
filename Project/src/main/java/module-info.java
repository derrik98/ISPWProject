module it.ispw.daniele.backpacker {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.desktop;
    requires org.json;
    requires java.logging;
    requires java.sql;
    requires org.jetbrains.annotations;
    requires AnimateFX;
    requires mysql.connector.java;

    exports it.ispw.daniele.backpacker.view.fxmlView;
    opens it.ispw.daniele.backpacker.view.fxmlView to javafx.fxml;
    exports it.ispw.daniele.backpacker.view.commandlineinterface;
    opens it.ispw.daniele.backpacker.view.commandlineinterface to javafx.fxml;
}