package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.utils.SessionUser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.jetbrains.annotations.NotNull;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

public class MenuBarController implements Initializable{

    @FXML
    private Label LabelHome = new Label();
    @FXML
    Label LabelResult = new Label();
    @FXML
    Label LabelProfile = new Label();
    @FXML
    public ImageView imageUndo;

    private String from = "home";

    protected static Stack<String> stackScene = new Stack<>();

    private UserGraphicChange ugc = UserGraphicChange.getInstance();

    private static String sel = "home";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String style="-fx-background-color: transparent; -fx-border: none; -fx-text-fill: rgba(245, 203, 92, 1); -fx-font-size: 40 ; -fx-font-weight: bold;";

        switch (sel){
            case "home" -> {
            //    this.LabelHome.setUnderline(true);
                this.LabelHome.setStyle(style);
//                this.LabelHome.setUnderline(true);
            }
            case "profile" -> {
                this.LabelProfile.setStyle(style);
                //this.LabelProfile.setUnderline(true);
            }
            case "result" -> {
                this.LabelResult.setStyle(style);
                break;
            }
            default -> {
                break;
            }

        }


    }

//    public void init(String selected) {
//        System.out.println(this.LabelHome + "" +  this.LabelProfile);
//        //String style="-fx-background-color: transparent; -fx-border: none; -fx-text-fill: rgba(245, 203, 92, 1); -fx-font-size: 40 ; -fx-font-weight: bold;";
//
//        this.ugc = UserGraphicChange.getInstance();
//        System.out.println(selected);
//        switch (selected) {
//
//            case "home" -> {
//                sel = selected;
//               // this.LabelHome.underlineProperty().setValue(true);
////                this.LabelHome.setStyle(style);
////                this.LabelHome.setUnderline(true);
//            }
//            case "profile" -> {
//                sel = selected;
//                //this.LabelProfile.setStyle(style);
//                //this.LabelProfile.setUnderline(true);
//            }
//            case "result" -> {
//                sel = selected;
//                //this.LabelResult.setStyle(style);
//                //this.LabelResult.setUnderline(true);
//                break;
//            }
//            default -> {
//                break;
//            }
//        }
//    }

    public void switchToHome(MouseEvent mouseEvent) throws IOException {
        sel = "home";
        this.ugc.switchToHomePage(this.LabelHome.getScene());
        stackScene.push("home");
        this.LabelHome.setUnderline(true);
        System.out.println(stackScene);
    }

    public void switchToResult(MouseEvent mouseEvent) {
        sel = "result";
        this.ugc.switchToResultPage(this.LabelResult.getScene());
        stackScene.push("result");
        System.out.println(stackScene);
    }

    public void switchToProfile(MouseEvent mouseEvent){
        sel = "profile";
        this.ugc.switchToProfilePage(this.LabelProfile.getScene());
        stackScene.push("profile");
        System.out.println(stackScene);
    }
//    @FXML
//    public void switchToLogin(MouseEvent mouseEvent) {
//        this.ugc.switchToLogin(this.LabelLogin.getScene());
//        stackScene.push(this.LabelHome.getScene().getRoot());
//        System.out.println(stackScene);
//    }

    public void logout(){
        SessionUser.getInstance().closeSession();
        this.ugc.switchToLogin(this.LabelHome.getScene());}


//    public void switchToSignUp(MouseEvent mouseEvent) {
//    }


    public void undoScene() throws IOException {

        if(stackScene.size() >= 2){
            this.from = stackScene.get(stackScene.size()-2);
            switch (this.from) {
                case "home" -> this.ugc.switchToHomePage(this.imageUndo.getScene());
                case "result" -> this.ugc.switchToResultPage(this.imageUndo.getScene());
                case "profile" -> this.ugc.switchToProfilePage(this.imageUndo.getScene());
            }
            stackScene.remove(stackScene.size()-1);
            System.out.println(stackScene);
        }
        else{
            this.ugc.switchToHomePage(this.imageUndo.getScene());
        }
    }


}
