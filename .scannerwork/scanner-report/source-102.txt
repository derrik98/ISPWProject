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
    requires com.jfoenix;
    requires org.json;
    requires java.logging;
    requires java.sql;
    requires org.jetbrains.annotations;

    exports it.ispw.daniele.backpacker.fxmlView;
    opens it.ispw.daniele.backpacker.fxmlView to javafx.fxml;
}