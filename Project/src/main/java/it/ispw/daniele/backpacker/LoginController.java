package it.ispw.daniele.backpacker;

import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbarLayout;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.Objects;

public class LoginController extends GUIController{

    @FXML
    AnchorPane APLogin;
    @FXML
    Button ButtonLogin;
    @FXML
    Button buttonFacebookLogin;
    @FXML
    Button buttonGoogleLogin;
    @FXML
    TextField textFieldUserLogin = new TextField();
    @FXML
    TextField textFieldPassLogin = new TextField();

    public void googleLogin() {
        System.out.println("Login with Google");
    }

    public void facebookLogin() {
        System.out.println("Login with Facebook");
    }

    public void login() {
        if(!textFieldUserLogin.getText().equals("") || !textFieldPassLogin.getText().equals("")){
            showFeedback(1);
        }
        else{
            showFeedback(0);
        }
        System.out.println("User " + textFieldUserLogin.getText() + "\tPass " + textFieldPassLogin.getText());
    }

    private void showFeedback(int i){
        JFXSnackbar snackbar = new JFXSnackbar(APLogin);
        if(i == 1){
            snackbar.fireEvent(new JFXSnackbar.SnackbarEvent(new JFXSnackbarLayout("Login Success"), Duration.seconds(2.5), null));
            snackbar.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styleLoginSuccess.css")).toExternalForm());
        }
        else{
            snackbar.fireEvent(new JFXSnackbar.SnackbarEvent(new JFXSnackbarLayout("Login Denied"), Duration.seconds(2.5), null));
            snackbar.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styleLoginDenied.css")).toExternalForm());
        }
    }

}
