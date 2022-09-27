package it.ispw.daniele.backpacker.fxmlView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class App extends Application {

    //protected static Stack<Parent> stackScene = new Stack<>();


    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("Backpacker");
        LoginViewController lvc = new LoginViewController();
        FXMLLoader loader = new FXMLLoader();
        FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/LoginViewPage.fxml");
        Parent fxmlLoader = loader.load(fileInputStream);
        loader.setController(lvc);
        Scene scene = new Scene(fxmlLoader);

        //stackScene.push(fxmlLoader);

        stage.setScene(scene);
        //stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}