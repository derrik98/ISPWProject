package it.ispw.daniele.backpacker.fxmlView;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class MenuBarController {
    @FXML
    private Label LabelHome = new Label();
    @FXML
    public Label LabelResult = new Label();
    @FXML
    public Label LabelProfile = new Label();
    @FXML
    public Label LabelLogin = new Label();
//    @FXML
//    public Label LabelSignUp;
    @FXML
    public ImageView imageUndo;


    private UserGraphicChange ugc;

    String style = "-fx-background-color: trasparent; -fx-border:none; -fx-text-fill: rgba(245, 203, 92, 1); " +
            "-fx-font-size:16;";

    @FXML
    public void switchToHome(MouseEvent mouseEvent) throws IOException {
        this.ugc.switchToHomepage(this.LabelHome.getScene());
    }
@FXML
    public void switchToResult(MouseEvent mouseEvent) {
    }
@FXML
    public void switchToProfile(MouseEvent mouseEvent) {

    }
@FXML
    public void switchToLogin(MouseEvent mouseEvent) {
        this.ugc.switchToLogin(this.LabelLogin.getScene());
    }

    public void switchToSignUp(MouseEvent mouseEvent) {
    }

    public void init(String selected) {

        this.ugc = UserGraphicChange.getInstance();
        switch(selected){
            case "home":
                this.LabelHome.setStyle(style);
                break;
            default:
                break;
        }
    }

    public void undoScene(MouseEvent mouseEvent) {
    }
}
