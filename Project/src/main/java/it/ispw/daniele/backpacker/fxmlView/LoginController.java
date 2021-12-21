package it.ispw.daniele.backpacker.fxmlView;

import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbarLayout;
import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class LoginController extends App {

    @FXML
    private AnchorPane APLogin;
    @FXML
    private Button ButtonLogin;
    @FXML
    private Button buttonFacebookLogin;
    @FXML
    private Button buttonGoogleLogin;
    @FXML
    private TextField textFieldUserLogin = new TextField();
    @FXML
    private TextField textFieldPassLogin = new TextField();

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
            GeneralUserBean loginBean = new GeneralUserBean(textFieldUserLogin.getText(), textFieldPassLogin.getText()).getInstance();
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
            snackbar.getStylesheets().add(Objects.requireNonNull(getClass().getResource("src/main/resources/it/ispw/daniele/backpacker/styleLoginSuccess.css")).toExternalForm());
        }
        else{
            JFXSnackbarLayout layout = new JFXSnackbarLayout("Login Denied");
            snackbar.fireEvent(new JFXSnackbar.SnackbarEvent(layout, Duration.seconds(2.5), null));
            snackbar.setStyle("-fx-font-size: 27px;");
            //snackbar.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styleLoginDenied.css")).toExternalForm());
        }
    }

}
