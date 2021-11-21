package it.ispw.daniele.backpacker.boundary;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Stack;

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

    public GUIController() throws IOException {
    }

    @Override
    public void start(Stage stage) throws IOException {


        //FARE METODO UNICO PER CAMBIARE LE SCENE
        FXMLLoader loader = new FXMLLoader();
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/it/ispw/daniele/backpacker/Home-Page.fxml");
        fxmlLoader = loader.load(fileInputStream);
        scene = new Scene(fxmlLoader);
        stackScene.push(scene);
        stage.setTitle("Backpacker");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public void switchToHome() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/it/ispw/daniele/backpacker/Home-Page.fxml");
        fxmlLoader = loader.load(fileInputStream);
        stage = (Stage) LabelHome.getScene().getWindow();
        scene = new Scene(fxmlLoader, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
        stackScene.push(scene);
    }


    public void switchToResult() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/it/ispw/daniele/backpacker/Result-Page.fxml");
        fxmlLoader = loader.load(fileInputStream);
        stage = (Stage) LabelResult.getScene().getWindow();
        scene = new Scene(fxmlLoader, stage.getScene().getWidth(), stage.getScene().getHeight());
        stackScene.push(scene);
        stage.setScene(scene);
    }


    public void switchToProfile() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/it/ispw/daniele/backpacker/Profile-Page.fxml");
        fxmlLoader = loader.load(fileInputStream);
        stage = (Stage) LabelProfile.getScene().getWindow();
        scene = new Scene(fxmlLoader, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
        stackScene.push(scene);
    }

    public void switchToLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/it/ispw/daniele/backpacker/Login-Page.fxml");
        fxmlLoader = loader.load(fileInputStream);
        stage = (Stage) LabelHome.getScene().getWindow();
        scene = new Scene(fxmlLoader, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
        stackScene.push(scene);
    }

    public void switchToSignUp() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/it/ispw/daniele/backpacker/SignUp-Page.fxml");
        fxmlLoader = loader.load(fileInputStream);
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