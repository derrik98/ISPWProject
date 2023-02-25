package it.ispw.daniele.backpacker.view.fxml_view;

import it.ispw.daniele.backpacker.bean.UserBean;
import it.ispw.daniele.backpacker.utils.SessionUser;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Stack;

public class MenuBarController {

    public AnchorPane APMenuBar = new AnchorPane();
    @FXML
    private Label LabelHome = new Label();
    @FXML
    private Label LabelResult = new Label();
    @FXML
    private Label LabelProfile = new Label();
    @FXML
    public ImageView imageUndo;

    protected static Stack<String> stackScene = new Stack<>();

    private UserGraphicChange ugc;
    private UserBean myUser;

    @FXML
    public void switchToHome() {
        this.ugc.switchToHomePage(this.LabelHome.getScene());
        stackScene.push("home");
    }

    @FXML
    public void switchToResult() {
        //this.ugc.switchToResult(this.LabelResult.getScene());
        stackScene.push("result");
    }

    @FXML
    public void switchToUserDet(){
        this.ugc.switchToUserDet(this.LabelProfile.getScene(), this.myUser);
        stackScene.push("profile");
    }

    @FXML
    public void logout(){
        stackScene.empty();
        SessionUser.getInstance().closeSession();
        this.ugc.switchToLogin(this.LabelHome.getScene());
    }


    @FXML
    public void undoScene() {

        if (stackScene.size() > 1) {
            String from = stackScene.get(stackScene.size() - 2);
            switch (from) {
                case "home" -> this.ugc.switchToHomePage(this.LabelHome.getScene());
                //case "result" -> this.ugc.switchToResult(this.LabelResult.getScene());
                case "profile" -> this.ugc.switchToUserDet(this.LabelProfile.getScene(), this.myUser);
            }
            stackScene.remove(stackScene.size() - 1);

        } else {
            this.ugc.switchToHomePage(this.imageUndo.getScene());
        }

    }

    public void init(String selected) {
        this.ugc = UserGraphicChange.getInstance();

        if(stackScene.isEmpty()){
            stackScene.push("home");
        }

        String style = "-fx-underline: true;";

        switch (selected){
            case "home" -> this.LabelHome.setStyle(style);
            case "profile" -> this.LabelProfile.setStyle(style);
            case "result" -> this.LabelResult.setStyle(style);
            default -> {
            }

        }
    }
}
