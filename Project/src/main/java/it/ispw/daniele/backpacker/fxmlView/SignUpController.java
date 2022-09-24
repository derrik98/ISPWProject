package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.bean.TouristGuideBean;
import it.ispw.daniele.backpacker.bean.UserBean;
import it.ispw.daniele.backpacker.controller.login.LoginController;
import it.ispw.daniele.backpacker.utils.Roles;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SignUpController{

    @FXML
    private Label USER;
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
    @FXML
    private VBox dynamicZone;
    private File imageFile = null;

    public void switchToGenericUserSignUpPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/SignUp-Page.fxml");
        Parent fxmlLoader = loader.load(fileInputStream);
//        Scene scene = this.USER.getScene();
//        scene.setRoot(fxmlLoader);
        dynamicZone.getChildren().remove(0, dynamicZone.getChildren().size());
        dynamicZone.getChildren().add(fxmlLoader);
        //stackScene.push(fxmlLoader);
    }

    public void switchToRestaurantOwnerSignUpPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/Tourist-Guide-SignUp-Page.fxml" +
                "");
        Parent fxmlLoader = loader.load(fileInputStream);
//        Scene scene = this.restaurantOwnerSignUp.getScene();
//        scene.setRoot(fxmlLoader);
        dynamicZone.getChildren().remove(0, dynamicZone.getChildren().size());
        dynamicZone.getChildren().add(fxmlLoader);
        //stackScene.push(fxmlLoader);
    }

    @FXML
    public void signUp(ActionEvent event){
        LoginController loginController = new LoginController();

        boolean regResult = false;

        String email = "";
        String username = "";
        String password = "";
        String userType = "";
        String VATNumb = "";

        email = textFieldEmailSignUp.getText();
        username = textFieldNameSignUp.getText();
        password = textFieldPassSignUp.getText();
        userType = USER.getId();


        String filename;
        String newFileName;

        //        if(this.imageFile == null){
//            filename = "";
//            newFileName = "";
//        }
//        else{
//            filename = this.imageFile.getName();
//            newFileName = username + filename;
//        }
        if (userType.equals(Roles.USER.name())){
            String firstName = this.textFieldNameSignUp.getText();
            String lastName = this.textFieldSurnameSignUp.getText();
            UserBean ub = new UserBean();
            ub.setUsername(username);
            ub.setName(firstName);
            ub.setSurname(lastName);
            ub.setEmail(email);
            ub.setPassword(password);
            ub.setProfilePicture("");
            //u.setProfilePicture(newFileName);
            regResult = loginController.createUser(ub);
        }
        else if(userType.equals(Roles.TOURIST_GUIDE.name())){
            String firstName = this.textFieldNameSignUp.getText();
            String lastName = this.textFieldSurnameSignUp.getText();
            VATNumb = textFieldVATNumber.getText();
            TouristGuideBean tgb = new TouristGuideBean();
            tgb.setUsername(username);
            tgb.setName(firstName);
            tgb.setSurname(lastName);
            tgb.setEmail(email);
            tgb.setPassword(password);
            //tgb.setProfilePicture("");
            //u.setProfilePicture(newFileName);
            tgb.setIdentificationCode(VATNumb);
            regResult = loginController.createTouristGuide(tgb);
        }

        if(Boolean.TRUE.equals(regResult)){
            System.out.println("REGISTRATION SUCCESSFULL");
//            if(this.imageFile != null){
//                String path = FileManager.PROFILE;
//                File file = new File(path, filename);
//                File newFile = new File(path, newFileName);
//                try(InputStream inputStream = new FileInputStream(this.imageFile)){
//                    Files.copy(inputStream, file.toPath());
//                }catch (Exception e){
//                    System.out.println("Warning image");
//                }
//                if(!file.renameTo(newFile)){
//                    System.out.println("unable to rename");
//                }
            //           }
        }
        else{
            System.out.println("unsuccessfull registration");
        }

        this.textFieldEmailSignUp.setText("");
        this.textFieldNameSignUp.setText("");
        this.textFieldPassSignUp.setText("");
        this.textFieldConfPassSignUp.setText("");
        this.textFieldSurnameSignUp.setText("");
        this.textFieldVATNumber.setText("");
    }

    /*public void signUp(MouseEvent mouseEvent) {

        LoginController loginController = new LoginController();
        Boolean regResult = false;
        String email = "";
        String username = "";
        String password = "";
        String userType = "";

        email = textFieldEmailSignUp.getText();
        username = textFieldNameSignUp.getText();
        password = textFieldPassSignUp.getText();
        //userType = this.typeOfUserField.getValue();
        userType = USER.getId();

        String filename;
        String newFileName;

//        if(this.imageFile == null){
//            filename = "";
//            newFileName = "";
//        }
//        else{
//            filename = this.imageFile.getName();
//            newFileName = username + filename;
//        }
        if (userType.equals(Roles.USER.name())){
            String firstName = this.textFieldNameSignUp.getText();
            String lastName = this.textFieldSurnameSignUp.getText();
            UserBean u = new UserBean();
            u.setUsername(username);
            u.setName(firstName);
            u.setSurname(lastName);
            u.setEmail(email);
            u.setPassword(password);
            u.setProfilePicture("");
            //u.setProfilePicture(newFileName);
            regResult = loginController.createUser(u);
        }
//        else if(userType.equals(RESTAURANT_OWNER)){
//System.out.println("ristoratore");
//        }

        if(Boolean.TRUE.equals(regResult)){
            System.out.println("REGISTRATION SUCCESSFULL");
//            if(this.imageFile != null){
//                String path = FileManager.PROFILE;
//                File file = new File(path, filename);
//                File newFile = new File(path, newFileName);
//                try(InputStream inputStream = new FileInputStream(this.imageFile)){
//                    Files.copy(inputStream, file.toPath());
//                }catch (Exception e){
//                    System.out.println("Warning image");
//                }
//                if(!file.renameTo(newFile)){
//                    System.out.println("unable to rename");
//                }
 //           }
        }
        else{
            System.out.println("unsuccessfull registration");
        }

        this.textFieldEmailSignUp.setText("");
        this.textFieldNameSignUp.setText("");
        this.textFieldPassSignUp.setText("");
        this.textFieldConfPassSignUp.setText("");
        this.textFieldSurnameSignUp.setText("");

    }*/
//        RegisterBean registerBean = new RegisterBean(textFieldEmailSignUp.getText(),
//                textFieldNameSignUp.getText(), textFieldSurnameSignUp.getText(),
//                textFieldPassSignUp.getText(), textFieldConfPassSignUp.getText()).getInstance();
//
//        System.out.println("Email " + textFieldEmailSignUp.getText() + "\tName " + textFieldNameSignUp.getText() + "\tSurname " + textFieldSurnameSignUp.getText()
//                + "\tPass " + textFieldPassSignUp.getText() + "\t ConfPass " + textFieldConfPassSignUp.getText());
//    }

    public void signUpRest(MouseEvent mouseEvent) {
        System.out.println("Email " + textFieldEmailSignUp.getText() + "\tName " + textFieldNameSignUp.getText() + "\tSurname " + textFieldSurnameSignUp.getText()
                + "\tPass " + textFieldPassSignUp.getText() + "\t ConfPass " + textFieldConfPassSignUp.getText() + "\t VAT " + textFieldVATNumber.getText());
    }
}
