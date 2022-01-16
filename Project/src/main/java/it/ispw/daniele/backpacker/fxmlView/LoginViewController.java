package it.ispw.daniele.backpacker.fxmlView;

import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbarLayout;
import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.controller.login.LoginController;
import it.ispw.daniele.backpacker.exceptions.LoginEmptyFieldException;
import it.ispw.daniele.backpacker.utils.SessionUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class LoginViewController extends App {

    @FXML
    public Label LabelLogin;
    @FXML
    public Label LabelSignUp;
    public ImageView imageUndo;
    @FXML
    public VBox dynamicZone;
    @FXML
    private AnchorPane APLogin;
    @FXML
    private Button ButtonLogin;
    @FXML
    private TextField textFieldUserLogin = new TextField();
    @FXML
    private TextField textFieldPassLogin = new TextField();

    @FXML
    public void loginAction() throws IOException {

        GeneralUserBean gub = new GeneralUserBean();
        gub.setUsername(this.textFieldUserLogin.getText());
        gub.setPassword(this.textFieldPassLogin.getText());

        LoginController loginController = new LoginController();
        GeneralUserBean gu;
        try{
            gu = loginController.login(gub);
            if(gu == null){
                System.out.println("errore nel LOGIN");
            }
            else{
                String role = gu.getRole();

                //SET SESSION
                SessionUser su = SessionUser.getInstance();
                su.setSession(gu);

                switch (role){
                    case "user":
                        UserGraphicChange.getInstance().switchToHomePage(this.textFieldUserLogin.getScene());
                        //ProfileController.init();
                        System.out.println(this.textFieldUserLogin.getScene());
                        break;
                    case "tourist_guide":
                        TouristGuideGraphicChange.getInstance().switchToHomePage(this.textFieldUserLogin.getScene());
                        break;
                    default:
                        break;
                }
            }
        }catch (LoginEmptyFieldException emptyFieldException){
            System.out.println("ERRORE NEL LOGIN");
        }



//        if(!textFieldUserLogin.getText().equals("") || !textFieldPassLogin.getText().equals("")){
//            GeneralUserBean loginBean = new GeneralUserBean(textFieldUserLogin.getText(), textFieldPassLogin.getText()).getInstance();
//            System.out.println("User " + textFieldUserLogin.getText() + "\tPass " + textFieldPassLogin.getText());
//            showFeedback(1);
//        }
//        else{
//            showFeedback(0);
//        }


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

    public void switchToSignUp() throws IOException {
        SignUpController signUpController = new SignUpController();
        FXMLLoader loader = new FXMLLoader();
        FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/SignUp-Page.fxml");
        //loader.setController(loginViewController);
        Parent fxmlLoader = loader.load(fileInputStream);

        dynamicZone.getChildren().remove(0, dynamicZone.getChildren().size());
        dynamicZone.getChildren().add(fxmlLoader);
        this.LabelSignUp.setUnderline(true);
        this.LabelLogin.setUnderline(false);
    }

    public void switchToLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/LoginViewPage.fxml");
        //loader.setController(loginViewController);
        Parent fxmlLoader = loader.load(fileInputStream);
        this.LabelLogin.getScene().setRoot(fxmlLoader);
        this.LabelSignUp.setUnderline(false);
        this.LabelLogin.setUnderline(true);
    }

    public void undoScene(MouseEvent mouseEvent) {
    }
}
