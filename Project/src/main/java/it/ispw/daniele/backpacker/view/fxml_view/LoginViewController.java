package it.ispw.daniele.backpacker.view.fxml_view;

import animatefx.animation.Shake;
import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.controller.login.LoginController;
import it.ispw.daniele.backpacker.exceptions.EmptyFieldException;
import it.ispw.daniele.backpacker.utils.SessionUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.IOException;

public class LoginViewController {

    @FXML
    public Label LabelLogin;
    @FXML
    public Label LabelSignUp;
    @FXML
    public VBox dynamicZone;
    @FXML
    public Text errorText;
    @FXML
    public Button buttonLogin;
    @FXML
    private TextField textFieldUsername = new TextField();
    @FXML
    private TextField textFieldPassword = new TextField();

    @FXML
    public void loginButtonAction() {

        GeneralUserBean gub = new GeneralUserBean();
        gub.setUsername(this.textFieldUsername.getText());
        gub.setPassword(this.textFieldPassword.getText());

        LoginController controller = new LoginController();
        GeneralUserBean gu;
        try{
            gu = controller.login(gub);

            if(gu == null){
                String styleErrorLabel = "-fx-border-style: solid; -fx-border-width: 1; -fx-border-color: red";
                this.textFieldUsername.setStyle(styleErrorLabel);
                this.textFieldPassword.setStyle(styleErrorLabel);
            }
            else{
                String role = gu.getRole();

                //SET SESSION GENERAL USER
                SessionUser su = SessionUser.getInstance();
                su.setSession(gu);

                switch (role) {
                    case "user" -> UserGraphicChange.getInstance().switchToHomePage(this.textFieldUsername.getScene());
                    case "tourist_guide" -> TouristGuideGraphicChange.getInstance().switchToHomePage(this.textFieldUsername.getScene());
                    default -> {
                    }
                }
            }
        }catch (EmptyFieldException exception){
            this.errorText.setStyle("-fx-font-size: 27px;");
            this.errorText.setText(exception.getMessage());
            //this.textFieldUsername.setStyle("-fx-border-style: solid; -fx-border-width: 1; -fx-border-color: red");
            //this.textFieldPassword.setStyle("-fx-border-style: solid; -fx-border-width: 1; -fx-border-color: red");

            new Shake(this.buttonLogin).play();
        }

    }

    public void switchToSignUp() throws IOException {
        SignUpController suc = new SignUpController();
        FXMLLoader loader = new FXMLLoader();
        FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/view/fxml_view/SignUp-Page.fxml");
        Parent fxmlLoader = loader.load(fileInputStream);
        loader.setController(suc);

        dynamicZone.getChildren().remove(0, dynamicZone.getChildren().size());
        dynamicZone.getChildren().add(fxmlLoader);
        this.LabelSignUp.setUnderline(true);
        this.LabelLogin.setUnderline(false);
    }

    public void switchToLogin() throws IOException {
        LoginViewController lvc = new LoginViewController();
        FXMLLoader loader = new FXMLLoader();
        FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/view/fxml_view/LoginView-Page.fxml");
        Parent fxmlLoader = loader.load(fileInputStream);
        loader.setController(lvc);
        this.LabelLogin.getScene().setRoot(fxmlLoader);
        this.LabelSignUp.setUnderline(false);
        this.LabelLogin.setUnderline(true);

    }

    @FXML
    public void enterKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
            this.loginButtonAction();
        }
    }

    public void init() {
        String styleDefault = "-fx-border-style: none; -fx-border-width: none; -fx-border-color: none";
        this.textFieldUsername.setStyle(styleDefault);
        this.textFieldPassword.setStyle(styleDefault);
    }

}
