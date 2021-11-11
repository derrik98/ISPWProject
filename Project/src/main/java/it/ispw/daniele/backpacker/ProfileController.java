package it.ispw.daniele.backpacker;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ProfileController extends GUIController implements Initializable {

    @FXML
    ImageView imageSettings;
    @FXML
    Text textSettings;
    @FXML
    VBox vBoxProfile;

    private final Accordion accordionResult = new Accordion();

    public void switchToSettings() throws IOException {
        fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Edit-Profile-Page.fxml")));
        stage = (Stage) LabelHome.getScene().getWindow();
        scene = new Scene(fxmlLoader, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
        stackScene.push(scene);
        System.out.println(stackScene);
    }

    public void showInfoSettings(MouseEvent mouseEvent) {
        System.out.println("entrato");
        textSettings.setVisible(true);
        //return textSettings.isVisible();

    }

    public void notShowInfoSettings(MouseEvent mouseEvent) {
        textSettings.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        vBoxProfile.getChildren().add(accordionResult);


        Accordion accordion = new Accordion();
        for(int i = 0; i <4;i++) {

            TitledPane titledPane = new TitledPane();
            titledPane.setCollapsible(false);
            titledPane.setAlignment(Pos.CENTER);
            HBox contentPane = new HBox();
            contentPane.setAlignment(Pos.CENTER);

            contentPane.setPadding(new Insets(0, 10, 0, 35));

            contentPane.minWidthProperty().bind(titledPane.widthProperty());

            HBox region = new HBox();
            region.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(region, Priority.ALWAYS);

            HBox region1 = new HBox();
            region1.setMinWidth(15);
            region1.setMaxWidth(Double.MAX_VALUE);

            Label l = new Label("prova" + i);
            l.setFont(new Font("Arial", 20));
            ImageView ivMap = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/googleMaps.png")).toExternalForm()));
            ivMap.setFitWidth(40);
            ivMap.setFitHeight(40);
            ivMap.setCursor(Cursor.HAND);
            ImageView ivSave = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/delete.png")).toExternalForm()));
            ivSave.setCursor(Cursor.HAND);
            ivSave.setFitWidth(40);
            ivSave.setFitHeight(40);

            ivMap.setOnMouseClicked(mouseEvent -> {
                if (!titledPane.isCollapsible()) {
                    titledPane.setCollapsible(true);
                    titledPane.setExpanded(true);
                } else {
                    titledPane.setCollapsible(false);
                    titledPane.setExpanded(false);

                }
            });

            ivSave.setOnMouseClicked(mouseEvent -> {
                Node s = contentPane.getChildren().get(0);
            });

            contentPane.getChildren().addAll(l, region, ivMap, region1, ivSave);

            titledPane.setGraphic(contentPane);
            WebView webView = new WebView();
            webView.getEngine().load("https://google.com");
            VBox v = new VBox(webView);
            titledPane.setContent(v);
            accordion.getPanes().add(titledPane);
        }
        vBoxProfile.getChildren().add(accordion);
    }
}
