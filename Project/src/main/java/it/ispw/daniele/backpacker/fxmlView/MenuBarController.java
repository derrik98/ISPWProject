package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.utils.SessionUser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

public class MenuBarController implements Initializable{

    public AnchorPane APMenuBar = new AnchorPane();
    @FXML
    private Label LabelHome = new Label();
    @FXML
    Label LabelResult = new Label();
    @FXML
    Label LabelProfile = new Label();
    @FXML
    public ImageView imageUndo;

    protected static Stack<String> stackScene = new Stack<>();


    private UserGraphicChange ugc;

    private static String sel = "home";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.ugc = UserGraphicChange.getInstance();

        if(stackScene.isEmpty()){
            stackScene.push("home");
        }

        //String style = "-fx-underline: true;";

        String style = "-fx-underline: true;";

        switch (sel){
            case "home" -> {
                this.LabelHome.setStyle(style);
            }
            case "profile" -> {
                this.LabelProfile.setStyle(style);
            }
            case "result" -> {
                this.LabelResult.setStyle(style);
            }
            default -> {
            }

        }
    }

    @FXML
    public void switchToHome() throws IOException {
        sel = "home";
        this.ugc.switchToHomePage(this.LabelHome.getScene());
        stackScene.push("home");
        //this.LabelHome.setUnderline(true);
        System.out.println(stackScene);
    }

    @FXML
    public void switchToResult() {
        sel = "result";
        this.ugc.switchToResult(this.LabelResult.getScene());
        stackScene.push("result");
        System.out.println(stackScene);
    }

    @FXML
    public void switchToProfile(){
        sel = "profile";
        this.ugc.switchToProfile(this.LabelProfile.getScene());
        stackScene.push("profile");
        System.out.println(stackScene);
    }

    @FXML
    public void logout(){
        stackScene.empty();
        SessionUser.getInstance().closeSession();
        this.ugc.switchToLogin(this.LabelHome.getScene());
    }


    @FXML
    public void undoScene() throws IOException {

        if (stackScene.size() > 1) {
            String from = stackScene.get(stackScene.size() - 2);
            sel = stackScene.get(stackScene.size() - 2);
            switch (from) {
                case "home" -> {
                    this.ugc.switchToHomePage(this.LabelHome.getScene());
                }
                case "result" -> {
                    this.ugc.switchToResult(this.LabelResult.getScene());
                }
                case "profile" -> {
                    this.ugc.switchToProfile(this.LabelProfile.getScene());
                }
            }

            //System.out.println(sel + "      aaaaaaaa");
            stackScene.remove(stackScene.size() - 1);
            //System.out.println(stackScene);
        } else {
            this.ugc.switchToHomePage(this.imageUndo.getScene());
        }

    }
}
