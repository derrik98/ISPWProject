package it.ispw.daniele.backpacker;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.Icon;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Stack;

import javax.swing.*;

public class GUIController extends Application {

    protected Stage stage = new Stage();
    Parent fxmlLoader;
    protected static Scene scene;
    protected static Stack<Scene> stackScene = new Stack<>();

    @FXML
    Label LabelHome;
    @FXML
    Label LabelResult;
    @FXML
    Label LabelLogin;
    @FXML
    Label LabelSignUp;
    @FXML
    Label LabelProfile;
    @FXML
    ImageView imageUndo;

    @Override
    public void start(Stage stage) throws IOException {

        //Screen screen = Screen.getPrimary();
        //Rectangle2D bounds = screen.getVisualBounds();
//        stage.setWidth(bounds.getWidth());
//        stage.setHeight(bounds.getHeight());
//        System.out.println(bounds);
        fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Home-Page.fxml")));
        scene = new Scene(fxmlLoader);
        stackScene.push(scene);
        stage.setTitle("Backpacker");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

        System.out.println(stackScene);

    }

    public void switchToHome() throws IOException {
        fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Home-Page.fxml")));
        stage = (Stage) LabelHome.getScene().getWindow();
        scene = new Scene(fxmlLoader, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
        stackScene.push(scene);
        System.out.println(stackScene);
    }


    public void switchToResult() throws IOException {
        fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Result-Page.fxml")));
        stage = (Stage) LabelResult.getScene().getWindow();
        scene = new Scene(fxmlLoader, stage.getScene().getWidth(), stage.getScene().getHeight());
        stackScene.push(scene);
        stage.setScene(scene);
    }


    public void switchToProfile() throws IOException {
        fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Profile-Page.fxml")));
        stage = (Stage) LabelProfile.getScene().getWindow();
        scene = new Scene(fxmlLoader, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
        stackScene.push(scene);
    }

    public void switchToLogin() throws IOException {
        fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login-Page.fxml")));
        stage = (Stage) LabelHome.getScene().getWindow();
        scene = new Scene(fxmlLoader, stage.getScene().getWidth(), stage.getScene().getHeight());
        //scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());

        //scene.getStylesheets().add((Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm()));
        stage.setScene(scene);
        stackScene.push(scene);
    }

    public void switchToSignUp() throws IOException {
        fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SignUp-Page.fxml")));
        stage = (Stage) LabelHome.getScene().getWindow();
        scene = new Scene(fxmlLoader, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
        stackScene.push(scene);
    }

    public void undoScene() {
        if (stackScene.size() > 1) {
            stage = (Stage) imageUndo.getScene().getWindow().getScene().getWindow();
            stackScene.remove(stackScene.size()-1);
            stage.setScene(stackScene.get(stackScene.size()-1));

            System.out.println(stackScene);
        }
        else{
            imageUndo.isDisable();
            imageUndo.setOpacity(0.5);
            imageUndo.setCursor(null);

        }
    }

    public static void main(String[] args) {
        launch();
    }

}