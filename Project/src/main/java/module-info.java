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

    opens it.ispw.daniele.backpacker to javafx.fxml;
    exports it.ispw.daniele.backpacker;
}