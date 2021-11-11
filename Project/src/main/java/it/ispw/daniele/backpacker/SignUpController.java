package it.ispw.daniele.backpacker;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodRequests;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SignUpController extends GUIController{

    @FXML
    Label genericUserSignUp;
    @FXML
    Label restaurantOwnerSignUp;
    @FXML
    TextField textFieldEmailSignUp,textFieldNameSignUp, textFieldSurnameSignUp, textFieldPassSignUp, textFieldConfPassSignUp;
    @FXML
    TextField textFieldVATNumber;
    @FXML
    Button buttonSignUp;
    @FXML
    Button buttonSignUpRest;

    public void switchToGenericUserSignUpPage() throws IOException {
        fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SignUp-Page.fxml")));
        stage = (Stage) LabelProfile.getScene().getWindow();
        scene = new Scene(fxmlLoader, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
        stackScene.push(scene);
        System.out.println(stackScene);
    }

    public void switchToRestaurantOwnerSignUpPage() throws IOException {
        fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Restaurant-Owner-SignUp-Page.fxml")));
        stage = (Stage) LabelProfile.getScene().getWindow();
        scene = new Scene(fxmlLoader, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
        stackScene.push(scene);
        System.out.println(stackScene);
    }

    public void signUp(MouseEvent mouseEvent) {
        System.out.println("Email " + textFieldEmailSignUp.getText() + "\tName " + textFieldNameSignUp.getText() + "\tSurname " + textFieldSurnameSignUp.getText()
                + "\tPass " + textFieldPassSignUp.getText() + "\t ConfPass " + textFieldConfPassSignUp.getText());
    }

    public void signUpRest(MouseEvent mouseEvent) {
        System.out.println("Email " + textFieldEmailSignUp.getText() + "\tName " + textFieldNameSignUp.getText() + "\tSurname " + textFieldSurnameSignUp.getText()
                + "\tPass " + textFieldPassSignUp.getText() + "\t ConfPass " + textFieldConfPassSignUp.getText() + "\t VAT " + textFieldVATNumber.getText());
    }
}
