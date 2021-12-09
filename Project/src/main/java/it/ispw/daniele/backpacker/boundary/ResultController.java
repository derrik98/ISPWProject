package it.ispw.daniele.backpacker.boundary;

import it.ispw.daniele.backpacker.bean.ResultBean;
import it.ispw.daniele.backpacker.entity.Itinerary;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ResultController extends GUIController implements Initializable {

    @FXML
    VBox vBoxResult;

    //private final Accordion accordionResult = new Accordion();
    private ResultBean resultBean = ResultBean.getInstance();

    public ResultController() throws IOException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //vBoxResult.getChildren().add(accordionResult);

        Accordion accordion = new Accordion();
        //for(int i = 0; i < 4;i++) {
        for(int indexItinerary = 0; indexItinerary < ResultBean.getInstance().getItinerary().size(); indexItinerary++){

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

            WebView webView = new WebView();
            webView.setMinHeight(300);
            StringBuilder Url = new StringBuilder("https://google.it/maps/dir");

            System.out.println(ResultBean.getInstance().getItinerary());

                Itinerary itinerary = ResultBean.getInstance().getItinerary().get(indexItinerary);
                System.out.println(itinerary);
                for(int indexMonument = 0; indexMonument < itinerary.getItinerary().size(); indexMonument++){
                    //System.out.println(itinerary.getItinerary());
                    Label label = new Label(" " + itinerary.getItinerary().get(indexMonument).getName() + " ");
                    label.setFont(new Font("Arial", 16));
                    label.setPrefWidth(Control.USE_COMPUTED_SIZE);
                    System.out.println(label.getLayoutX() + " " +label.getTranslateX());
                    contentPane.getChildren().add(label);
                    Url.append("/").append(itinerary.getItinerary().get(indexMonument).getName());

                }

            webView.getEngine().load(Url.toString());
            VBox v = new VBox(webView);
            titledPane.setContent(v);

            ImageView ivMap = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/googleMaps.png")).toExternalForm()));
            ivMap.setFitWidth(40);
            ivMap.setFitHeight(40);
            ivMap.setCursor(Cursor.HAND);
            ImageView ivSave = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/save.png")).toExternalForm()));
            ivSave.setCursor(Cursor.HAND);
            ivSave.setFitWidth(40);
            ivSave.setFitHeight(40);

            ivMap.setOnMouseClicked(mouseEvent -> {
                if (!titledPane.isCollapsible()) {
                    titledPane.setCollapsible(true);
                    titledPane.setExpanded(true);
                    System.out.println("tasto mappa cliccato");
                } else {
                    titledPane.setCollapsible(false);
                    titledPane.setExpanded(false);

                }
            });

            ivSave.setOnMouseClicked(mouseEvent -> {
                Node s = contentPane.getChildren().get(0);
            });

            contentPane.getChildren().addAll(region, ivMap, region1, ivSave);
            contentPane.setPrefWidth(Control.USE_PREF_SIZE);
            ScrollString s = new ScrollString();

            titledPane.setGraphic(contentPane);
            accordion.getPanes().add(titledPane);
        }
        vBoxResult.getChildren().add(accordion);
    }
}
