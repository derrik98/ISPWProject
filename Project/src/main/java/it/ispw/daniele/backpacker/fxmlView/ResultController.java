package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.bean.ResultBean;
import it.ispw.daniele.backpacker.entity.Itinerary;
import it.ispw.daniele.backpacker.utils.Roles;
import it.ispw.daniele.backpacker.utils.SessionUser;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ResultController implements Initializable {

    @FXML
    private VBox vBoxResult;
    @FXML
    private HBox menuBar = new HBox();

    //private final Accordion accordionResult = new Accordion();
    private ResultBean resultBean = ResultBean.getInstance();

    public ResultController() throws IOException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(SessionUser.getInstance().getSession().getRole().equals(Roles.TOURIST_GUIDE.name().toLowerCase())) {
            TouristGuideGraphicChange i = TouristGuideGraphicChange.getInstance();
            i.menuBar(this.menuBar, "result");
            System.out.println(SessionUser.getInstance().getSession().getRole() + Roles.TOURIST_GUIDE.name());
        }
        else {
            UserGraphicChange ugc = UserGraphicChange.getInstance();
            ugc.menuBar(this.menuBar, "result");
            System.out.println(SessionUser.getInstance().getSession().getRole() + Roles.USER.name().toLowerCase());

        }


        //UserGraphicChange ugc = UserGraphicChange.getInstance();
        //vBoxResult.getChildren().add(accordionResult);
        //ugc.menuBar(this.menuBar, "result");
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
                    Label label = new Label("  " + itinerary.getItinerary().get(indexMonument).getName() + "  ");
                    label.setFont(new Font("Arial", 16));
                    label.setPrefWidth(Control.USE_COMPUTED_SIZE);

                    Label space = new Label(" - ");
                    space.setFont(new Font("Arial", 16));
                    space.setPrefWidth(Control.USE_COMPUTED_SIZE);

                    System.out.println(label.getLayoutX() + " " +label.getTranslateX());
                    contentPane.getChildren().add(label);
                    contentPane.getChildren().add(space);
                    Url.append("/").append(itinerary.getItinerary().get(indexMonument).getName());
                    label.setCursor(Cursor.HAND);
                    label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            System.out.println("label cliccata");
                        }
                    });
//                    Popup popup = new Popup();
//                    label.setOnMouseEntered(mouseEvent -> {
//                        StackPane stickyNotesPane = new StackPane();
//                        stickyNotesPane.setPrefSize(50, 50);
//                        stickyNotesPane.setStyle("-fx-background-color: white;");
//                        stickyNotesPane.setStyle("-fx-background-radius: 20px;");
//
//                        popup.getContent().add(stickyNotesPane);
//                            Bounds bnds = label.localToScreen(label.getLayoutBounds());
//                            double x = bnds.getMinX() - (stickyNotesPane.getWidth() / 2) + (label.getWidth() / 2);
//                            double y = bnds.getMinY() - stickyNotesPane.getHeight();
//                            popup.getContent().add(new Label("Click for Detals"));
//                            popup.show(label, x, y);
//                    });
//
//                    label.setOnMouseExited(mouseEvent -> popup.hide());
////
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

            //restringere accordion
            //contentPane.setPrefWidth(Control.USE_PREF_SIZE);

            titledPane.setGraphic(contentPane);
            accordion.getPanes().add(titledPane);
        }
        vBoxResult.getChildren().add(accordion);
    }
}
