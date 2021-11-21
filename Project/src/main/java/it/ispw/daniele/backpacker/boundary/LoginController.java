package it.ispw.daniele.backpacker.boundary;

import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbarLayout;
import it.ispw.daniele.backpacker.bean.LoginBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
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

    public LoginController() throws IOException {
    }

    public void googleLogin() {
        System.out.println("Login with Google");
    }

    public void facebookLogin() {
        System.out.println("Login with Facebook");
    }

    public void login() {
        if(!textFieldUserLogin.getText().equals("") || !textFieldPassLogin.getText().equals("")){
            LoginBean loginBean = new LoginBean(textFieldUserLogin.getText(), textFieldPassLogin.getText()).getInstance();
            System.out.println("User " + textFieldUserLogin.getText() + "\tPass " + textFieldPassLogin.getText());
            showFeedback(1);
        }
        else{
            showFeedback(0);
        }


       //);
//        homeBean.setCountry(textFieldCountry.getText());
//        homeBean.setCity(textFieldCity.getText());
//        homeBean.setAddress(textFieldAddress.getText());
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