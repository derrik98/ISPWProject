package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.bean.RegisterBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class SignUpController extends App {

    @FXML
    private Label genericUserSignUp;
    @FXML
    private Label restaurantOwnerSignUp;
    @FXML
    private TextField textFieldEmailSignUp,textFieldNameSignUp, textFieldSurnameSignUp, textFieldPassSignUp, textFieldConfPassSignUp;
    @FXML
    private TextField textFieldVATNumber;
    @FXML
    private Button buttonSignUp;
    @FXML
    private Button buttonSignUpRest;

    public SignUpController() throws IOException {
    }

    public void switchToGenericUserSignUpPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/it/ispw/daniele/backpacker/SignUp-Page.fxml");
        Parent fxmlLoader = loader.load(fileInputStream);
        Scene scene = this.genericUserSignUp.getScene();
        scene.setRoot(fxmlLoader);
        stackScene.push(fxmlLoader);
    }

    public void switchToRestaurantOwnerSignUpPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/it/ispw/daniele/backpacker/Restaurant-Owner-SignUp-Page.fxml");
        Parent fxmlLoader = loader.load(fileInputStream);
        Scene scene = this.restaurantOwnerSignUp.getScene();
        scene.setRoot(fxmlLoader);
        stackScene.push(fxmlLoader);
    }

    public void signUp(MouseEvent mouseEvent) {
        RegisterBean registerBean = new RegisterBean(textFieldEmailSignUp.getText(),
                textFieldNameSignUp.getText(), textFieldSurnameSignUp.getText(),
                textFieldPassSignUp.getText(), textFieldConfPassSignUp.getText()).getInstance();

        System.out.println("Email " + textFieldEmailSignUp.getText() + "\tName " + textFieldNameSignUp.getText() + "\tSurname " + textFieldSurnameSignUp.getText()
                + "\tPass " + textFieldPassSignUp.getText() + "\t ConfPass " + textFieldConfPassSignUp.getText());
    }

    public void signUpRest(MouseEvent mouseEvent) {
        System.out.println("Email " + textFieldEmailSignUp.getText() + "\tName " + textFieldNameSignUp.getText() + "\tSurname " + textFieldSurnameSignUp.getText()
                + "\tPass " + textFieldPassSignUp.getText() + "\t ConfPass " + textFieldConfPassSignUp.getText() + "\t VAT " + textFieldVATNumber.getText());
    }
}
