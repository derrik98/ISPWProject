package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.bean.TouristGuideBean;
import it.ispw.daniele.backpacker.bean.UserBean;
import it.ispw.daniele.backpacker.controller.login.LoginController;
import it.ispw.daniele.backpacker.utils.FileManager;
import it.ispw.daniele.backpacker.utils.Roles;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;

public class SignUpController{

    @FXML
    private Label TOURIST_GUIDE;
    public TextField textFieldImage;
    @FXML
    private Label USER;
    @FXML
    private TextField textFieldEmailSignUp,textFieldNameSignUp, textFieldSurnameSignUp, textFieldPassSignUp, textFieldConfPassSignUp;
    @FXML
    private TextField textFieldVATNumber;

    private File imageFile = null;

    @FXML
    public void signUp(){
        LoginController lc = new LoginController();

        boolean regResult = false;

        String email = "";
        String username = "";
        String password = "";
        String userType = "";
        String VATNumb = "";

        email = textFieldEmailSignUp.getText();
        username = textFieldNameSignUp.getText();
        password = textFieldPassSignUp.getText();
        if(this.USER.isUnderline()){
            userType = USER.getId();
        }
        else {
            userType = TOURIST_GUIDE.getId();
        }

        String fileName;
        String newFileName;

        if(this.imageFile == null) {
            fileName="";
            newFileName="";
        }else {
            fileName=this.imageFile.getName();
            newFileName=username+fileName;
        }

        if (userType.equals(Roles.USER.name())){
            String name = this.textFieldNameSignUp.getText();
            String surname = this.textFieldSurnameSignUp.getText();
            UserBean ub = new UserBean();
            ub.setUsername(username);
            ub.setName(name);
            ub.setSurname(surname);
            ub.setEmail(email);
            ub.setPassword(password);
            ub.setProfilePicture(newFileName);
            regResult = lc.createUser(ub);
        }
        else if(userType.equals(Roles.TOURIST_GUIDE.name())){
            String name = this.textFieldNameSignUp.getText();
            String surname = this.textFieldSurnameSignUp.getText();
            VATNumb = textFieldVATNumber.getText();
            TouristGuideBean tgb = new TouristGuideBean();
            tgb.setUsername(username);
            tgb.setName(name);
            tgb.setSurname(surname);
            tgb.setEmail(email);
            tgb.setPassword(password);
            tgb.setProfilePicture(newFileName);
            tgb.setIdentificationCode(VATNumb);
            regResult = lc.createTouristGuide(tgb);
        }

        if(Boolean.TRUE.equals(regResult)){
            System.out.println("REGISTRATION SUCCESSFULL");
            if(this.imageFile != null){
                String path = FileManager.PROFILE;
                System.out.println(path);
                File file = new File(path, fileName);
                File newFile = new File(path, newFileName);
                try(InputStream inputStream = new FileInputStream(this.imageFile)){
                    Files.copy(inputStream, file.toPath());
                }catch (Exception e){
                    System.out.println("Warning image");
                }
                if(!file.renameTo(newFile)){
                    System.out.println("unable to rename");
                }
                       }
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
        this.imageFile = null;
        this.textFieldImage.setText("No image selected");
    }

    @FXML
    public void selectImage() {
        final FileChooser fc=new FileChooser();
        fc.setTitle("Select image");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG","*.jpg"),
                new FileChooser.ExtensionFilter("PNG","*.png"));
        this.imageFile=fc.showOpenDialog(new Stage());
        if(this.imageFile!=null)this.textFieldImage.setText(this.imageFile.getName());
    }

    @FXML
    public void switchToUserSignUpPage() {
        this.textFieldVATNumber.setDisable(true);
        this.USER.setUnderline(true);
        this.TOURIST_GUIDE.setUnderline(false);
    }

    @FXML
    public void switchToTGuideSignUpPage() {
        this.textFieldVATNumber.setDisable(false);
        this.USER.setUnderline(false);
        this.TOURIST_GUIDE.setUnderline(true);
    }
}
