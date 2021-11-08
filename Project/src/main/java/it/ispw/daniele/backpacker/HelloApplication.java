package it.ispw.daniele.backpacker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;
import java.util.Stack;

public class HelloApplication extends Application {

    Parent fxmlLoader;
    protected static Scene scene;
    protected static Stack<Scene> stackScene = new Stack<>();


    @Override
    public void start(Stage stage) throws IOException {


        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        System.out.println(bounds);
        fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Home-Page.fxml")));
        scene = new Scene(fxmlLoader);
        stackScene.push(scene);
        //scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        stage.setTitle("Backpacker");
        stage.setScene(scene);
        //stage.setMaximized(true);
        stage.show();

        System.out.println(stackScene);

    }

    public static void main(String[] args) {
        launch();
    }

}