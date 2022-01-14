package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.utils.SessionUser;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Stack;

public class App extends Application {

    protected static Stack<Parent> stackScene = new Stack<>();


    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("Backpacker");
        //LoginViewController loginViewController = new LoginViewController();
        FXMLLoader loader = new FXMLLoader();
        FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/LoginViewPage.fxml");
        //loader.setController(loginViewController);
        Parent fxmlLoader = loader.load(fileInputStream);
        Scene scene = new Scene(fxmlLoader);
        //stackScene.push(fxmlLoader);


        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

//    public void switchToHome() throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/Home-Page.fxml");
//        Parent fxmlLoader = loader.load(fileInputStream);
//        Scene scene = this.LabelHome.getScene();
//        scene.setRoot(fxmlLoader);
//        stackScene.push(fxmlLoader);
//    }
//
//
//    public void switchToResult() throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/Result-Page.fxml");
//        Parent fxmlLoader = loader.load(fileInputStream);
//        Scene scene = this.LabelResult.getScene();
//        scene.setRoot(fxmlLoader);
//        stackScene.push(fxmlLoader);
//    }


//    public void switchToProfile() throws IOException {
//        ProfileController profileController = new ProfileController();
//        FXMLLoader loader = new FXMLLoader();
//        FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/Profile-Page.fxml");
//        Parent fxmlLoader = loader.load(fileInputStream);
//        Scene scene = this.LabelProfile.getScene();
//        loader.setController(profileController);
//        scene.setRoot(fxmlLoader);
//        stackScene.push(fxmlLoader);
//        System.out.println(SessionUser.getInstance().getSession().getUsername());
//        profileController.init(SessionUser.getInstance().getSession());
//        System.out.println(SessionUser.getInstance().getSession().getUsername());
//    }

//    public void switchToLogin(Scene scene) throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/LoginViewPage.fxml");
//        Parent fxmlLoader = loader.load(fileInputStream);
//        //Scene scene = this.LabelLogin.getScene();
//        scene.setRoot(fxmlLoader);
//        stackScene.push(fxmlLoader);
//    }

//    public void switchToSignUp() throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/SignUp-Page.fxml");
//        Parent fxmlLoader = loader.load(fileInputStream);
//        Scene scene = this.LabelSignUp.getScene();
//        scene.setRoot(fxmlLoader);
//        stackScene.push(fxmlLoader);
//    }
//
//    public void undoScene() {
//        if (stackScene.size() > 1) {
//            Scene scene = this.imageUndo.getScene();
//            stackScene.remove(stackScene.size()-1);
//            scene.setRoot(stackScene.get(stackScene.size()-1));
//        }
//        else{
//            imageUndo.isDisable();
//            imageUndo.setOpacity(0.5);
//            imageUndo.setCursor(null);
//        }
//    }

    public static void main(String[] args) {
        launch();
    }

}