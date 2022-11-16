package it.ispw.daniele.backpacker.commandLineInterface;

import it.ispw.daniele.backpacker.bean.TouristGuideBean;
import it.ispw.daniele.backpacker.bean.UserBean;
import it.ispw.daniele.backpacker.controller.login.LoginController;
import it.ispw.daniele.backpacker.utils.FileManager;
import it.ispw.daniele.backpacker.utils.Roles;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Scanner;

public class CliSignUpController {

    private File imageFile = null;

    public void init(Scanner scan) {

        System.out.print("\033[H\033[2J");
        System.out.flush();

        LoginController lc = new LoginController();

        boolean regResult = false;

        String email;
        String username;
        String name;
        String surname;
        String password;
        String userType;
        String VATNumb;
        String ProfilePicture = "";

        System.out.println("Email:");
        email = scan.nextLine();

        System.out.println("Username:");
        username = scan.nextLine();

        System.out.println("Name:");
        name = scan.nextLine();

        System.out.println("Surname:");
        surname = scan.nextLine();

        System.out.println("Password:");
        password = scan.nextLine();

        /*email = textFieldEmailSignUp.getText();
        username = textFieldNameSignUp.getText();
        password = textFieldPassSignUp.getText();*/

        System.out.println("Type of user: Generic[0] - Tourist_Guide[1]");
        userType = scan.nextLine();
        switch (userType) {
            case "0" -> userType = "USER";
            case "1" -> {
                userType = "TOURIST_GUIDE";
                System.out.println("VAT Number:");
                VATNumb = scan.nextLine();
            }
        }

        /*if(this.USER.isUnderline()){
            userType = USER.getId();
        }
        else {
            userType = TOURIST_GUIDE.getId();
        }
*/
        System.out.println("Would you like profile image? Yes[y] - No[n]");
//        password = scan.nextLine();

        String fileName = "";
        String newFileName = "";

        switch (scan.nextLine()){
            case "n":
                fileName = "";
                newFileName = "";
                System.out.println("sonoqui");
            case "y":
                final FileChooser fc = new FileChooser();
                fc.setTitle("Select image");
                fc.setInitialDirectory(new File(System.getProperty("user.home")));
                fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG","*.jpg"),
                        new FileChooser.ExtensionFilter("PNG","*.png"));
                this.imageFile = fc.showOpenDialog(new Stage());
                if(this.imageFile!=null){
                    ProfilePicture = this.imageFile.getName();
                }
                assert this.imageFile != null;
                fileName = this.imageFile.getName();;
                newFileName = username+fileName;
        }



        /*if(ProfilePicture.equals("")) {
            fileName="";
            newFileName="";
        }else {
            final FileChooser fc = new FileChooser();
            fc.setTitle("Select image");
            fc.setInitialDirectory(new File(System.getProperty("user.home")));
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG","*.jpg"),
                    new FileChooser.ExtensionFilter("PNG","*.png"));
            this.imageFile=fc.showOpenDialog(new Stage());
            if(this.imageFile!=null){
                this.textFieldImage.setText(this.imageFile.getName());
            }
        }
//            fileName=ProfilePicture;
            newFileName=username+fileName;
        }*/

        if (userType.equals(Roles.USER.name())){
            UserBean ub = new UserBean();
            ub.setUsername(username);
            ub.setName(name);
            ub.setSurname(surname);
            ub.setEmail(email);
            ub.setPassword(password);
            ub.setProfilePicture(newFileName);
            regResult = lc.createUser(ub);
            System.out.println(ub.getUsername()+ub.getName()+ub.getSurname()+ub.getEmail()+ub.getPassword()+ub.getProfilePicture());
        }
        else if(userType.equals(Roles.TOURIST_GUIDE.name())){
            System.out.println("VAT Number:");
            VATNumb = scan.nextLine();
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

        email = "";
        username = "";
        name = "";
        surname = "";
        password = "";
        userType = "";
        VATNumb = "";
        this.imageFile = null;
        ProfilePicture = "";
        /*this.textFieldEmailSignUp.setText("");
        this.textFieldNameSignUp.setText("");
        this.textFieldPassSignUp.setText("");
        this.textFieldConfPassSignUp.setText("");
        this.textFieldSurnameSignUp.setText("");
        this.textFieldVATNumber.setText("");
        this.imageFile = null;
        this.textFieldImage.setText("No image selected");*/

    }
}
