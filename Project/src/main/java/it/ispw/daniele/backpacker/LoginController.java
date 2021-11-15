package it.ispw.daniele.backpacker;

import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbarLayout;
import javafx.css.StyleClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController extends GUIController implements Initializable {

    @FXML
    AnchorPane APLogin;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JFXSnackbar snackbar = new JFXSnackbar(APLogin);
        snackbar.fireEvent(new JFXSnackbar.SnackbarEvent(new JFXSnackbarLayout("ciao"), Duration.seconds(2), null));
       //snackbar.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        snackbar.setStyle("-fx-text-fill: RED; -fx-font-size: 27px");
        snackbar.enqueue(new JFXSnackbar.SnackbarEvent(new JFXSnackbarLayout("sdbvchjkdsfb"), Duration.seconds(5)));


//        snackbar.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
//        snackbar.setId("red-border");
//        snackbar.getStyleClass().add("red-border");
    }
}
