package it.ispw.daniele.backpacker.boundary;

import it.ispw.daniele.backpacker.bean.RegisterBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileInputStream;
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

    public SignUpController() throws IOException {
    }

    public void switchToGenericUserSignUpPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/it/ispw/daniele/backpacker/SignUp-Page.fxml");
        fxmlLoader = loader.load(fileInputStream);
        stage = (Stage) LabelProfile.getScene().getWindow();
        scene = new Scene(fxmlLoader, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
        stackScene.push(scene);
        System.out.println(stackScene);
    }

    public void switchToRestaurantOwnerSignUpPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/it/ispw/daniele/backpacker/Restaurant-Owner-SignUp-Page.fxml");
        fxmlLoader = loader.load(fileInputStream);
        stage = (Stage) LabelProfile.getScene().getWindow();
        scene = new Scene(fxmlLoader, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
        stackScene.push(scene);
        System.out.println(stackScene);
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
