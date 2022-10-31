package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.bean.ItineraryBean;
import it.ispw.daniele.backpacker.booktour.BookTourController;
import it.ispw.daniele.backpacker.controller.search.SearchController;
import it.ispw.daniele.backpacker.exceptions.MonumentNotFoundException;
import it.ispw.daniele.backpacker.utils.Roles;
import it.ispw.daniele.backpacker.utils.SessionUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResultController  {
    @FXML
    public Text suggestedItinerary = new Text();
    @FXML
    public VBox vBoxResultGuide = new VBox();
    @FXML
    public Text selfItinerary = new Text();
    @FXML
    public ImageView guideImage = new ImageView();
    public StackPane stackPaneResult = new StackPane();
    @FXML
    public HBox hBoxInput = new HBox();
    @FXML
    public ImageView guideOff;
    @FXML
    public VBox vBoxDynamic = new VBox();
    @FXML
    private Label countrySearch = new Label();
    @FXML
    private Label citySearch = new Label();
    @FXML
    private Label addressSearch = new Label();
    @FXML
    private Label isRestaurant = new Label();
    @FXML
    private Label radiusSearch = new Label();
    @FXML
    private VBox vBoxResult = new VBox();
    @FXML
    private HBox menuBar = new HBox();


    public void init(String country, String city, String address, String restaurant, String range) throws MonumentNotFoundException {

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

        if (!country.equals("") && !city.equals("") && !address.equals("")) {
            this.countrySearch.setText(country);
            this.citySearch.setText(city);
            this.addressSearch.setText(address);
            this.isRestaurant.setText(restaurant);
            this.radiusSearch.setText(range);
        }
        else {
            this.countrySearch.setText("");
            this.citySearch.setText("");
            this.addressSearch.setText("");
            this.isRestaurant.setText("");
            this.radiusSearch.setText("");
            hBoxInput.setVisible(false);
            selfItinerary.setVisible(false);
            guideOff.setVisible(false);

            hBoxInput.getChildren().removeAll();
            Hyperlink link = new Hyperlink("Start from the Home-Page");
            link.setOnMouseClicked(mouseEvent -> {
                /*UserGraphicChange.getInstance().switchToHomePage(selfItinerary.getScene());
                UserGraphicChange.getInstance().menuBar(this.menuBar, "home");*/
                if(SessionUser.getInstance().getSession().getRole().equals(Roles.TOURIST_GUIDE.name().toLowerCase())) {
                    TouristGuideGraphicChange i = TouristGuideGraphicChange.getInstance();
                    //i.switchToHomePage(this.menuBar.getScene());

                    i.menuBar(this.menuBar, "home");
                    System.out.println(SessionUser.getInstance().getSession().getRole() + Roles.TOURIST_GUIDE.name());
                }
                else {
                    UserGraphicChange ugc = UserGraphicChange.getInstance();
                    ugc.switchToHomePage(this.menuBar.getScene());
                    ugc.menuBar(this.menuBar, "home");
                    System.out.println(SessionUser.getInstance().getSession().getRole() + Roles.USER.name().toLowerCase());
                }

            });

            vBoxDynamic.getChildren().add(0, link);
        }
////TOLTO IL CLICK SULLA LABEL DEI RESULT////
        BookTourController btc = new BookTourController();
        List<ItineraryBean> it;
        it = btc.getItinerary(citySearch.getText(), "city");

        if(it == null){
            System.out.println("EMPTY_DATABASE ");
        }
        else{
            suggestedItinerary.setText("Suggested Itinerary");
            guideImage.setImage(new Image("guideOn.png"));
            guideImage.setFitHeight(50);
            guideImage.setFitHeight(50);

            Accordion accordionSuggested = createTable(it, "suggested");
            vBoxResultGuide.getChildren().add(accordionSuggested);
        }

        SearchController sc = new SearchController();
        List<ItineraryBean> iti;
        iti = sc.createItinerary(addressSearch.getText());

        if(iti == null){
            System.out.println("EMPTY_DATABASE ");
        }
        else {
            selfItinerary.setText("Self Itinerary");

            Accordion accordionSelf = createTable(iti, "self");
            vBoxResult.getChildren().add(accordionSelf);
        }
    }

    public Accordion createTable(List<ItineraryBean> itineraryBeanList, String type){

        Accordion accordion = new Accordion();
        int j;
        for(j = 0; j < itineraryBeanList.size(); j++) {
            System.out.println(itineraryBeanList.get(j).getSteps());
            String[] steps = itineraryBeanList.get(j).getSteps().split("/");
            ArrayList<String> als = new ArrayList<>();
            for (int i = 0; i < steps.length; i++) {
                als.add(i, steps[i]);
            }

            TitledPane titledPane = new TitledPane();
            titledPane.setCollapsible(false);
            titledPane.setAlignment(Pos.CENTER);
            HBox contentPane = new HBox();
            contentPane.setAlignment(Pos.CENTER);

            contentPane.setPadding(new Insets(0, 10, 0, 35));

            contentPane.minWidthProperty().bind(titledPane.widthProperty());

            HBox region = new HBox();
            region.setMinWidth(15);
            region.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(region, Priority.ALWAYS);

            HBox region1 = new HBox();
            region1.setMinWidth(15);
            region1.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(region1, Priority.ALWAYS);

            HBox region2 = new HBox();
            region2.setMinWidth(15);
            region2.setMaxWidth(Double.MAX_VALUE);

            WebView webView = new WebView();
            webView.setMinHeight(300);
            StringBuilder Url = new StringBuilder("https://google.it/maps/dir");

            for (int indexMonument = 0; indexMonument < als.size(); indexMonument++) {

                //System.out.println(itinerary.getItinerary());
                Label label = new Label(" " + als.get(indexMonument) + " ");
                label.setFont(new Font("Arial", 14));
                label.setPrefWidth(Control.USE_COMPUTED_SIZE);
                contentPane.getChildren().add(label);

                if(indexMonument != 0 && indexMonument != als.size()-1) {
                    System.out.println("INDEXMONUMENT " + indexMonument);
                    Label space = new Label(" - ");
                    space.setFont(new Font("Arial", 14));
                    space.setPrefWidth(Control.USE_COMPUTED_SIZE);

                    System.out.println(label.getLayoutX() + " " + label.getTranslateX());

                    contentPane.getChildren().add(space);
                    Url.append("/").append(als.get(indexMonument));
                    label.setCursor(Cursor.HAND);
                    label.setOnMouseClicked(mouseEvent -> System.out.println("label cliccata"));
                }

            }
            webView.getEngine().load(Url.toString());
            VBox v = new VBox(webView);
            titledPane.setContent(v);

        if(type.equals("suggested")){
            ImageView ivBuy = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/buy.png")).toExternalForm()));
            ivBuy.setFitWidth(50);
            ivBuy.setFitHeight(50);
            ivBuy.setCursor(Cursor.HAND);

            int finalJ = j;
            ivBuy.setOnMouseClicked(mouseEvent -> {
                FXMLLoader loader = new FXMLLoader();
                FileInputStream fileInputStream;
                try {

                    fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/ItineraryDetails-Page.fxml");
                    Parent fxmlLoader = loader.load(fileInputStream);
                    ItineraryDetailsController idc = loader.getController();
                    idc.convertItinerary(itineraryBeanList.get(finalJ));
                    this.stackPaneResult.getChildren().add(fxmlLoader);
                    stackPaneResult.getChildren().get(0).setDisable(true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            HBox.setHgrow(region1, Priority.NEVER);
            contentPane.getChildren().addAll(region, ivBuy);
        }

            ImageView ivMap = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/googleMaps.png")).toExternalForm()));
            ivMap.setFitWidth(35);
            ivMap.setFitHeight(35);
            ivMap.setCursor(Cursor.HAND);
            ivMap.setId("MAP");
            ImageView ivSave = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/save.png")).toExternalForm()));
            ivSave.setCursor(Cursor.HAND);
            ivSave.setFitWidth(35);
            ivSave.setFitHeight(35);

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

            contentPane.getChildren().addAll(region1, ivMap, region2, ivSave);

            titledPane.setGraphic(contentPane);
            accordion.getPanes().add(titledPane);
        }
        return accordion;
    }
}
