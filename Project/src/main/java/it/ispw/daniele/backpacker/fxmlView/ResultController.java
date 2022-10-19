package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.bean.HomeBean;
import it.ispw.daniele.backpacker.bean.ItineraryBean;
import it.ispw.daniele.backpacker.bean.ResultBean;
import it.ispw.daniele.backpacker.booktour.BookTourController;
import it.ispw.daniele.backpacker.entity.Itinerary;
import it.ispw.daniele.backpacker.utils.Roles;
import it.ispw.daniele.backpacker.utils.SessionUser;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;

import java.awt.event.MouseAdapter;

import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ResultController implements Initializable {

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

    //private final Accordion accordionResult = new Accordion();
    private ResultBean resultBean = ResultBean.getInstance();

    private GeneralUserBean sessionUser;
    private BookTourController controller;

    protected ItineraryBean itinerary;

    public ResultController() throws IOException {
    }


   @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.sessionUser = SessionUser.getInstance().getSession();

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

       BookTourController btc = new BookTourController();
       List<ItineraryBean> itineraries = new ArrayList<>();
       ItineraryBean it;// = new ItineraryBean();
       HomeBean hb = HomeBean.getInstance();

       if (hb != null) {
           this.countrySearch.setText(hb.getCountry());
           this.citySearch.setText(hb.getCity());
           this.addressSearch.setText(hb.getAddress());
           this.isRestaurant.setText("bho");
           this.radiusSearch.setText(hb.getRange());
       }
       else {
           this.countrySearch.setText("");
           this.citySearch.setText("");
           this.addressSearch.setText("");
           this.isRestaurant.setText("");
           this.radiusSearch.setText("");
       }


       //try {

           it = btc.getItinerary(citySearch.getText());

           if(it == null){
               System.out.println("DATABASE " + it);
           }
           else{
               System.out.println(it.getSteps());
               suggestedItinerary.setText("Suggested Itinerary");
               guideImage.setImage(new Image("guideOn.png"));
               guideImage.setFitHeight(50);
               guideImage.setFitHeight(50);

               String[] steps = it.getSteps().split("/");
               ArrayList<String> als = new ArrayList<>();
               for(int i = 0; i < steps.length; i++){
                   als.add(i, steps[i]);
               }
               System.out.println("als " + als);
               Accordion accordion = new Accordion();
               //accordion.setPrefWidth(1280);
               //for(int i = 0; i < 4;i++) {
               /////for(int indexItinerary = 0; indexItinerary < als.size(); indexItinerary++){

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

                   HBox region2 = new HBox();
                   region2.setMinWidth(15);
                   region2.setMaxWidth(Double.MAX_VALUE);

                   WebView webView = new WebView();
                   webView.setMinHeight(300);
                   StringBuilder Url = new StringBuilder("https://google.it/maps/dir");

                   //System.out.println(ResultBean.getInstance().getItinerary());

                   //Itinerary itinerary = ResultBean.getInstance().getItinerary().get(indexItinerary);
                   System.out.println(itinerary);
                   for(int indexMonument = 0; indexMonument < als.size(); indexMonument++){
                       //System.out.println(itinerary.getItinerary());
                       Label label = new Label(" " + als.get(indexMonument) + " ");
                       label.setFont(new Font("Arial", 14));
                       label.setPrefWidth(Control.USE_COMPUTED_SIZE);

                       Label space = new Label(" - ");
                       space.setFont(new Font("Arial", 14));
                       space.setPrefWidth(Control.USE_COMPUTED_SIZE);

                       System.out.println(label.getLayoutX() + " " +label.getTranslateX());
                       contentPane.getChildren().add(label);
                       contentPane.getChildren().add(space);
                       Url.append("/").append(als.get(indexMonument));
                       label.setCursor(Cursor.HAND);
                       label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                           @Override
                           public void handle(MouseEvent mouseEvent) {
                               System.out.println("label cliccata");
                           }
                       });

                   }
                   webView.getEngine().load(Url.toString());
                   VBox v = new VBox(webView);
                   titledPane.setContent(v);


                   ImageView ivBuy = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/buy.png")).toExternalForm()));
                   ivBuy.setFitWidth(55);
                   ivBuy.setFitHeight(55);
                   ivBuy.setCursor(Cursor.HAND);
                   ImageView ivMap = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/googleMaps.png")).toExternalForm()));
                   ivMap.setFitWidth(35);
                   ivMap.setFitHeight(35);
                   ivMap.setCursor(Cursor.HAND);
                   ImageView ivSave = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/save.png")).toExternalForm()));
                   ivSave.setCursor(Cursor.HAND);
                   ivSave.setFitWidth(35);
                   ivSave.setFitHeight(35);

                   this.controller = new BookTourController();

                   System.out.println("ITTTT " + it + it.getItineraryId());

                   ivBuy.setOnMouseClicked(mouseEvent -> {

                       FXMLLoader loader = new FXMLLoader();
                       FileInputStream fileInputStream = null;
                       try {

                           fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/ItineraryDetails-Page.fxml");
                           Parent fxmlLoader = loader.load(fileInputStream);
                           ItineraryDetailsController idc =  loader.getController();;
                          idc.convertItinerary(it);
                           System.out.println("RRRRRRRRRRRRRRRRRRR " + it);
                          // ItineraryDetailsController.getInstance().convertItinerary(it);
                           this.stackPaneResult.getChildren().add(fxmlLoader);
                           stackPaneResult.getChildren().get(0).setDisable(true);
                           //loader.setController(idc);
                       } catch (IOException e) {
                           throw new RuntimeException(e);
                       }

                       //loader.setController(suc);




                       /*//boolean isPart = controller.isParticipating(this.sessionUser, this.itinerary);
                       boolean isPart = controller.isParticipating(this.sessionUser, it);
                       if(isPart){
                           controller.removeParticipation(this.sessionUser, this.itinerary);
                           //this.part.setText("Add Participation");
                       } else {
                           controller.addParticipation(this.sessionUser, it);
                           //this.part.setText("Remove Participation");
                       }*/
                   });

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

                   contentPane.getChildren().addAll(region, ivBuy, region1, ivMap, region2
                           , ivSave);

                   //restringere accordion
                   //contentPane.setPrefWidth(Control.USE_PREF_SIZE);

                   titledPane.setGraphic(contentPane);
                   accordion.getPanes().add(titledPane);
               //}
               vBoxResultGuide.getChildren().add(accordion);
           }

       /*} catch (NoMusicEventFoundException e) {
           this.evLabel.setText(e.getMessage());
       }*/


        //UserGraphicChange ugc = UserGraphicChange.getInstance();
        //vBoxResult.getChildren().add(accordionResult);
        //ugc.menuBar(this.menuBar, "result");

        selfItinerary.setText("Self Itinerary");
        Accordion accordion = new Accordion();
        //accordion.setPrefWidth(1280);
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

            HBox region2 = new HBox();
            region2.setMinWidth(15);
            region2.setMaxWidth(Double.MAX_VALUE);

            WebView webView = new WebView();
            webView.setMinHeight(300);
            StringBuilder Url = new StringBuilder("https://google.it/maps/dir");

            System.out.println(ResultBean.getInstance().getItinerary());

            Itinerary itinerary = ResultBean.getInstance().getItinerary().get(indexItinerary);
            System.out.println(itinerary);
            for(int indexMonument = 0; indexMonument < itinerary.getItinerary().size(); indexMonument++){
                //System.out.println(itinerary.getItinerary());
                Label label = new Label(" " + itinerary.getItinerary().get(indexMonument).getName() + " ");
                label.setFont(new Font("Arial", 14));
                label.setPrefWidth(Control.USE_COMPUTED_SIZE);

                Label space = new Label(" - ");
                space.setFont(new Font("Arial", 14));
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


            //ImageView ivBuy = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/buy.png")).toExternalForm()));
            //ivBuy.setFitWidth(55);
            //ivBuy.setFitHeight(55);
            //ivBuy.setCursor(Cursor.HAND);
            ImageView ivMap = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/googleMaps.png")).toExternalForm()));
            ivMap.setFitWidth(35);
            ivMap.setFitHeight(35);
            ivMap.setCursor(Cursor.HAND);
            ImageView ivSave = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/save.png")).toExternalForm()));
            ivSave.setCursor(Cursor.HAND);
            ivSave.setFitWidth(35);
            ivSave.setFitHeight(35);

            this.controller = new BookTourController();

            /*ivBuy.setOnMouseClicked(mouseEvent -> {
                boolean isPart = controller.isParticipating(this.sessionUser, this.itinerary);
                if(isPart){
                    controller.removeParticipation(this.sessionUser, this.itinerary);
                    //this.part.setText("Add Participation");
                } else {
                    controller.addParticipation(this.sessionUser, this.itinerary);
                    //this.part.setText("Remove Participation");
                }
            });*/

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

            //contentPane.getChildren().addAll(region, ivBuy, region1, ivMap, region2, ivSave);
            contentPane.getChildren().addAll(region, ivMap, region2, ivSave);

            //restringere accordion
            //contentPane.setPrefWidth(Control.USE_PREF_SIZE);

            titledPane.setGraphic(contentPane);
            accordion.getPanes().add(titledPane);
        }
        vBoxResult.getChildren().add(accordion);


    }


}
