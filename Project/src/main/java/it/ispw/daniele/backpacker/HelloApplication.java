package it.ispw.daniele.backpacker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;
import java.util.Stack;

public class HelloApplication extends Application {

    Parent fxmlLoader;
    public static Scene scene;
    public static Stack<Scene> stackScene = new Stack<>();

    @Override
    public void start(Stage stage) throws IOException {

        fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Home-Page.fxml")));
        scene = new Scene(fxmlLoader);
        stackScene.push(scene);
        //scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        stage.setTitle("Backpacker");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

        System.out.println(stackScene);

    }

    public static void main(String[] args) {
        launch();
    }

}