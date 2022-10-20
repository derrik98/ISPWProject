package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.utils.SessionUser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

public class TouristGuideMenuBarController {


    @FXML
    public Text notificationNumber;
    public ImageView imageMessage;
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

    @FXML
    public void switchToHome() throws IOException {
        sel = "home";
        this.guideGraphicChange.switchToHomePage(this.LabelHome.getScene());
        stackScene.push("home");
        //this.LabelHome.setUnderline(true);
        System.out.println(stackScene);
    }
    @FXML
    public void switchToResult() {
        sel = "result";
        this.guideGraphicChange.switchToResult(this.LabelResult.getScene());
        stackScene.push("result");
        System.out.println(stackScene);

        //this.tggc.switchToResult(this.LabelResult.getScene());
    }
    @FXML
    public void switchToProfile() {
        sel = "profile";
        this.guideGraphicChange.switchToProfile(this.LabelProfile.getScene());
        stackScene.push("profile");
        System.out.println(stackScene);
    }

    @FXML
    public void switchToAddItinerary() throws IOException {
        sel = "addItinerary";
        this.guideGraphicChange.switchToAddItinerary(this.LabelAddItinerary.getScene());
        stackScene.push("addItinerary");
    }

    @FXML
    public void logout(){
        stackScene.empty();
        SessionUser.getInstance().closeSession();
        this.guideGraphicChange.switchToLogin(this.LabelHome.getScene());
    }

    public void undoScene() throws IOException {

        if (stackScene.size() > 1) {
            String from = stackScene.get(stackScene.size() - 2);
            sel = stackScene.get(stackScene.size() - 2);
            switch (from) {
                case "home" -> this.guideGraphicChange.switchToHomePage(this.LabelHome.getScene());
                case "result" -> this.guideGraphicChange.switchToResult(this.LabelResult.getScene());
                case "profile" -> this.guideGraphicChange.switchToProfile(this.LabelProfile.getScene());
                case "addItinerary" -> this.guideGraphicChange.switchToProfile(this.LabelAddItinerary.getScene());
            }

            stackScene.remove(stackScene.size() - 1);

        } else {
            this.guideGraphicChange.switchToHomePage(this.imageUndo.getScene());
        }

    }

    public void viewNotification() {
        this.guideGraphicChange.switchToNotifications(this.imageMessage.getScene());
    }

    public void init() {
        notificationNumber.setText("10");

        this.guideGraphicChange = TouristGuideGraphicChange.getInstance();

        if(stackScene.isEmpty()){
            stackScene.push("home");
        }

        String style = "-fx-underline: true;";

        switch (sel){
            case "home" -> this.LabelHome.setStyle(style);
            case "profile" -> this.LabelProfile.setStyle(style);
            case "result" -> this.LabelResult.setStyle(style);
            case "addItinerary" -> this.LabelAddItinerary.setStyle(style);
            default -> {
            }
        }
    }
}
