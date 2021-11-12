package it.ispw.daniele.backpacker;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController extends GUIController{

    @FXML
    Button ButtonLogin;
    @FXML
    Button buttonFacebookLogin;
    @FXML
    Button buttonGoogleLogin;
    @FXML
    TextField textFieldUserLogin;
    @FXML
    TextField textFieldPassLogin;

    public void googleLogin(MouseEvent mouseEvent) {
        System.out.println("Login with Google");
    }

    public void facebookLogin(MouseEvent mouseEvent) {
        System.out.println("Login with Facebook");
    }

    public void login() {
        System.out.println("User " + textFieldUserLogin.getText() + "\tPass " + textFieldPassLogin.getText());
    }

}
