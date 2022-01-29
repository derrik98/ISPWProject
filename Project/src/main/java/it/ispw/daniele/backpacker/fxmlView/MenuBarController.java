package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.utils.SessionUser;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Stack;

public class MenuBarController {
    @FXML
    private Label LabelHome = new Label();
    @FXML
    public Label LabelResult = new Label();
    @FXML
    private Label LabelProfile = new Label();
    @FXML
    public Label LabelLogin = new Label();
//    @FXML
//    public Label LabelSignUp;
    @FXML
    public ImageView imageUndo;

    private String from = "home";
    private String searchString = "";
    private UserGraphicChange grCtrl;

    protected static Stack<String> stackScene = new Stack<>();

    //COMPLEATARE STACK DI UNDO


    private UserGraphicChange ugc = UserGraphicChange.getInstance();

    String style = " -fx-font-size:16; -fx-font-style: bold;";

    @FXML
    public void switchToHome(MouseEvent mouseEvent) throws IOException {
        this.ugc.switchToHomePage(this.LabelHome.getScene());
        stackScene.push("home");
        System.out.println(stackScene);
    }
    @FXML
    public void switchToResult(MouseEvent mouseEvent) {
        this.ugc.switchToResultPage(this.LabelResult.getScene());
        stackScene.push("result");
        System.out.println(stackScene);
    }
    @FXML
    public void switchToProfile(MouseEvent mouseEvent) throws IOException {
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

    @FXML
    public void logout(){
        SessionUser.getInstance().closeSession();
        this.ugc.switchToLogin(this.LabelHome.getScene());}


    public void switchToSignUp(MouseEvent mouseEvent) {
    }

    public void init(String selected) {

        this.ugc = UserGraphicChange.getInstance();
        switch (selected) {
            case "home" -> {
                this.LabelHome.setStyle(style);
            }
            case "profile" -> {
                this.LabelProfile.setStyle(style);
                this.LabelProfile.setUnderline(true);
            }
            case "result" -> this.LabelResult.setStyle(style);
            default -> {
            }
        }
    }

    @FXML
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
