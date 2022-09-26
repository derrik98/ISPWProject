package it.ispw.daniele.backpacker.fxmlView;

//import com.jfoenix.controls.JFXSnackbar;
//import com.jfoenix.controls.JFXSnackbarLayout;
import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.bean.UserBean;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import javafx.event.ActionEvent;
//import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Key;
import java.util.Objects;

public class LoginViewController {//extends App {

    @FXML
    public Label LabelHome;
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
    private TextField textFieldUsername = new TextField();
    @FXML
    private TextField textFieldPassword = new TextField();

    @FXML
    public void loginButtonAction() throws IOException {

        this.textFieldUsername.setStyle("-fx-border-style: none; -fx-border-width: none; -fx-border-color: none");
        this.textFieldPassword.setStyle("-fx-border-style: none; -fx-border-width: none; -fx-border-color: none");

        GeneralUserBean gub = new GeneralUserBean();
        gub.setUsername(this.textFieldUsername.getText());
        gub.setPassword(this.textFieldPassword.getText());

        LoginController controller = new LoginController();
        GeneralUserBean gu;
        try{
            gu = controller.login(gub);
            if(gu == null){
                this.textFieldUsername.setStyle("-fx-border-style: solid; -fx-border-width: 1; -fx-border-color: red");
                this.textFieldPassword.setStyle("-fx-border-style: solid; -fx-border-width: 1; -fx-border-color: red");
                //System.out.println("Login failed!");
            }
            else{
                String role = gu.getRole();

                //SET SESSION GENERAL USER
                SessionUser su = SessionUser.getInstance();
                su.setSession(gu);

                switch (role) {
                    case "user" -> {
                        UserGraphicChange.getInstance().switchToHomePage(this.textFieldUsername.getScene());
                    }
                    case "tourist_guide" ->
                            TouristGuideGraphicChange.getInstance().switchToHomePage(this.textFieldUsername.getScene());
                    default -> {
                    }
                }
            }
        }catch (LoginEmptyFieldException exception){
            this.textFieldUsername.setStyle("-fx-border-style: solid; -fx-border-width: 1; -fx-border-color: red");
            this.textFieldPassword.setStyle("-fx-border-style: solid; -fx-border-width: 1; -fx-border-color: red");
            System.out.println("Login error");
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

  /*  private void showFeedback(int i){
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
    }*/

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

    public void switchToHome() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/Home-Page.fxml");
        //loader.setController(loginViewController);
        Parent fxmlLoader = loader.load(fileInputStream);
        this.LabelLogin.getScene().setRoot(fxmlLoader);
        this.LabelSignUp.setUnderline(false);
        this.LabelLogin.setUnderline(true);
    }

    public void undoScene(MouseEvent mouseEvent) {
    }

    @FXML
    public void enterKeyPressed(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
            this.loginButtonAction();
        }
    }
}
