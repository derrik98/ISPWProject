package it.ispw.daniele.backpacker.fxmlView;

import animatefx.animation.Shake;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("Backpacker");
        stage.getIcons().add(new Image("/backpack.png"));
        FXMLLoader loader = new FXMLLoader();
        FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/LoginView-Page.fxml");
        Parent fxmlLoader = loader.load(fileInputStream);
        Scene scene = new Scene(fxmlLoader);
        LoginViewController lvc = loader.getController();
        lvc.init();
        stage.setScene(scene);
        //stage.setMaximized(true);
        stage.show();

        new Shake(fxmlLoader).play();
    }

    public static void main(String[] args) {
        launch();
    }

}