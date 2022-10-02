package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.utils.SessionUser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

public class TouristGuideMenuBarController implements Initializable {

    @FXML
    private Label LabelHome = new Label();
    @FXML
    public Label LabelResult = new Label();
    @FXML
    private Label LabelProfile = new Label();
    @FXML
    public Label LabelAddItinerary = new Label();
    @FXML
    public ImageView imageUndo;

    protected static Stack<String> stackScene = new Stack<>();

    private TouristGuideGraphicChange guideGraphicChange;

    private static String sel = "home";
    //String style = "-fx-background-color: trasparent; -fx-border:none; -fx-font-size:16;";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.guideGraphicChange = TouristGuideGraphicChange.getInstance();

        if(stackScene.isEmpty()){
            stackScene.push("home");
        }

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
            case "addItinerary" -> {
                this.LabelAddItinerary.setStyle(style);
            }
            default -> {
            }

        }
    }

    @FXML
    public void switchToHome(MouseEvent mouseEvent) throws IOException {
        sel = "home";
        this.guideGraphicChange.switchToHomePage(this.LabelHome.getScene());
        stackScene.push("home");
        //this.LabelHome.setUnderline(true);
        System.out.println(stackScene);
    }
    @FXML
    public void switchToResult(MouseEvent mouseEvent) {
        sel = "result";
        this.guideGraphicChange.switchToResult(this.LabelResult.getScene());
        stackScene.push("result");
        System.out.println(stackScene);

        //this.tggc.switchToResult(this.LabelResult.getScene());
    }
    @FXML
    public void switchToProfile(MouseEvent mouseEvent) throws IOException {
        sel = "profile";
        this.guideGraphicChange.switchToProfile(this.LabelProfile.getScene());
        stackScene.push("profile");
        System.out.println(stackScene);
    }

    @FXML
    public void switchToAddItinerary(MouseEvent mouseEvent) throws IOException {
        sel = "addItinerary";
        this.guideGraphicChange.switchToAddItinerary(this.LabelAddItinerary.getScene());
        stackScene.push("addItinerary");
    }

    @FXML
    public void logout(){
        SessionUser.getInstance().closeSession();
        this.guideGraphicChange.switchToLogin(this.LabelHome.getScene());
    }

    public void switchToSignUp(MouseEvent mouseEvent) {
    }

    /*public void init(String selected) {

        this.tggc = TouristGuideGraphicChange.getInstance();
        switch(selected){
            case "home":
                this.LabelHome.setStyle(style);
                break;
            case "profile":
                this.LabelProfile.setStyle(style);
                break;
            case "result":
                this.LabelProfile.setStyle(style);
                break;
            default:
                break;
        }
    }*/

    public void undoScene(MouseEvent mouseEvent) {
    }

    /*@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.guideGraphicChange = TouristGuideGraphicChange.getInstance();

        String style = "-fx-underline: true;";

        switch (sel) {
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
            }
            default -> {
            }


        }
    }*/
}
