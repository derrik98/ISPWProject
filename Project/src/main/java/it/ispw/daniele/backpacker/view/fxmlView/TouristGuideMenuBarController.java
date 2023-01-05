package it.ispw.daniele.backpacker.view.fxmlView;

import it.ispw.daniele.backpacker.bean.TouristGuideBean;
import it.ispw.daniele.backpacker.utils.SessionUser;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Stack;

public class TouristGuideMenuBarController {

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
    private TouristGuideBean myUser;

    @FXML
    public void switchToHome() {
        this.guideGraphicChange.switchToHomePage(this.LabelHome.getScene());
        stackScene.push("home");
    }
    @FXML
    public void switchToResult() {
        //this.guideGraphicChange.switchToResult(this.LabelResult.getScene());
        stackScene.push("result");
    }
    @FXML
    public void switchToTGuideDetails() {
        this.guideGraphicChange.switchToTGuideDet(this.LabelProfile.getScene(), this.myUser);
        stackScene.push("profile");
    }

    @FXML
    public void switchToAddItinerary() throws IOException {
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
            switch (from) {
                case "home" -> this.guideGraphicChange.switchToHomePage(this.LabelHome.getScene());
                //case "result" -> this.guideGraphicChange.switchToResult(this.LabelResult.getScene());
                case "profile" -> this.guideGraphicChange.switchToTGuideDet(this.LabelProfile.getScene(), this.myUser);
                case "addItinerary" -> this.guideGraphicChange.switchToAddItinerary(this.LabelAddItinerary.getScene());
            }

            stackScene.remove(stackScene.size() - 1);

        } else {
            this.guideGraphicChange.switchToHomePage(this.imageUndo.getScene());
        }

    }

    public void init(String selected) {

        this.guideGraphicChange = TouristGuideGraphicChange.getInstance();

        if(stackScene.isEmpty()){
            stackScene.push("home");
        }

        String style = "-fx-underline: true;";

        switch (selected){
            case "home" -> this.LabelHome.setStyle(style);
            case "profile" -> this.LabelProfile.setStyle(style);
            case "result" -> this.LabelResult.setStyle(style);
            case "addItinerary" -> this.LabelAddItinerary.setStyle(style);
            default -> {
            }
        }
    }
}
