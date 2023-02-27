package it.ispw.daniele.backpacker.utils;

import it.ispw.daniele.backpacker.bean.ItineraryBean;
import it.ispw.daniele.backpacker.bean.TouristGuideBean;
import it.ispw.daniele.backpacker.bean.UserBean;
import it.ispw.daniele.backpacker.booktour.SaveTour;
import it.ispw.daniele.backpacker.entity.Itinerary;
import it.ispw.daniele.backpacker.entity.TouristGuide;
import it.ispw.daniele.backpacker.entity.User;
import it.ispw.daniele.backpacker.view.fxml_view.ItineraryDetailsController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
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

public class Controller {

    protected UserBean convert(User l) {
        UserBean ub = new UserBean();
        ub.setUsername(l.getUsername());
        ub.setName(l.getName());
        ub.setSurname(l.getSurname());
        ub.setProfilePicture(l.getProfilePicture());
        ub.setEmail(l.getEmail());

        return ub;
    }

    protected TouristGuideBean convert(TouristGuide l) {
        TouristGuideBean tgb = new TouristGuideBean();
        tgb.setUsername(l.getUsername());
        tgb.setName(l.getName());
        tgb.setSurname(l.getSurname());
        tgb.setProfilePicture(l.getProfilePicture());
        tgb.setEmail(l.getEmail());
        tgb.setIdentificationCode(l.getVatNum());

        return tgb;
    }

    protected List<ItineraryBean> convert(List<Itinerary> itinerary) {
        List<ItineraryBean> lb = new ArrayList<>();
        for (Itinerary it : itinerary) {
            ItineraryBean ib = this.convert(it);
            lb.add(ib);
        }

        return lb;
    }

    protected ItineraryBean convert(Itinerary itinerary) {
        ItineraryBean ib = new ItineraryBean();
        ib.setItineraryId(itinerary.getId());
        ib.setGuideId(itinerary.getGuideId());
        ib.setLocation(itinerary.getLocation());
        ib.setDate(itinerary.getDate());
        ib.setTime(itinerary.getTime());
        ib.setParticipants(itinerary.getParticipants());
        ib.setPrice(itinerary.getPrice());
        ib.setSteps(itinerary.getSteps());

        return ib;
    }

    protected Accordion createTable(List<ItineraryBean> itineraryBeanList, String type, String from, StackPane stackPane) {

        Accordion accordion = new Accordion();

        for (int jind = 0; jind < itineraryBeanList.size(); jind++) {

            String[] steps = itineraryBeanList.get(jind).getSteps().split("/");
            ArrayList<String> als = new ArrayList<>();
            for (int i = 0; i < steps.length; i++) {
                als.add(i, steps[i]);
            }
            TitledPane titledPane = new TitledPane();

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

            HBox region2 = new HBox();
            region2.setMinWidth(15);
            region2.setMaxWidth(Double.MAX_VALUE);


            for (int indexMonument = 0; indexMonument < als.size(); indexMonument++) {

                Label label = new Label(" " + als.get(indexMonument) + " ");

                if (indexMonument != 0) {

                    Label space = new Label(" - ");
                    space.setFont(new Font("Arial", 14));
                    space.setPrefWidth(Control.USE_COMPUTED_SIZE);

                    contentPane.getChildren().add(space);

                    label.setCursor(Cursor.HAND);

                }

                label.setFont(new Font("Arial", 14));
                label.setPrefWidth(Control.USE_COMPUTED_SIZE);
                contentPane.getChildren().add(label);

            }

            HBox region3 = new HBox();
            region3.setMinWidth(15);
            region3.setMaxWidth(Double.MAX_VALUE);

            Label output = new Label();
            contentPane.getChildren().addAll(region3, output);

            if (type.equals("suggested")) {

                if (!from.equals("profile")) {

                    //Setting of buy image
                    ImageView ivBuy;
                    ivBuy = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/buy.png")).toExternalForm()));
                    ivBuy.setFitWidth(40);
                    ivBuy.setFitHeight(40);
                    ivBuy.setCursor(Cursor.HAND);

                    int finalJ = jind;
                    ivBuy.setOnMouseClicked(mouseEvent -> {

                        titledPane.setExpanded(false);
                        this.BuyItinerary(itineraryBeanList.get(finalJ), stackPane);

                    });

                    contentPane.getChildren().addAll(region, ivBuy);
                } else {
                    HBox.setHgrow(region1, Priority.ALWAYS);
                }
            } else {
                HBox.setHgrow(region1, Priority.ALWAYS);
            }

            ImageView ivSave;
            int finalJ1 = jind;

            //Setting of delete image
            if (from.equals("profile")) {

                ivSave = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/cestino.png")).toExternalForm()));
                ivSave.setOnMouseClicked(mouseEvent -> {
                    titledPane.setCollapsible(false);
                    titledPane.setExpanded(false);
                    this.SaveItinerary(itineraryBeanList.get(finalJ1), "remove");

                    accordion.getPanes().remove(titledPane);
                });
            }

            //Setting of save image
            else {
                ivSave = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/save.png")).toExternalForm()));
                ivSave.setOnMouseClicked(mouseEvent -> {

                    try {
                        this.SaveItinerary(itineraryBeanList.get(finalJ1), "save");
                        output.setText("Saved");
                    } catch (Exception e) {
                        output.setText("Error");
                    }

                });

                ivSave.setCursor(Cursor.HAND);
                ivSave.setFitWidth(35);
                ivSave.setFitHeight(35);

                contentPane.getChildren().addAll(region1, ivSave);
            }

                //Setting of map image
            /*ImageView ivMap = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/googleMaps.png")).toExternalForm()));
            ivMap.setFitWidth(35);
            ivMap.setFitHeight(35);
            ivMap.setCursor(Cursor.HAND);
            ivMap.setId("MAP");


            ivMap.setOnMouseClicked(mouseEvent -> {
                System.out.println("COLL " + titledPane.isCollapsible());
                System.out.println("COLL " + titledPane.isExpanded());

                //if (!titledPane.isCollapsible()) {
                    if (!titledPane.isExpanded()) {

                    goToMap(als, titledPane);

                    //titledPane.setCollapsible(true);
                    titledPane.setExpanded(true);

                } else {
                    //titledPane.setCollapsible(true);
                    titledPane.setExpanded(false);
                }
            });
            contentPane.getChildren().addAll(region2, ivMap);*/
                titledPane.setGraphic(contentPane);
                this.goToMap(als, titledPane);


                titledPane.setExpanded(true);
                titledPane.setCollapsible(true);
                accordion.getPanes().add(titledPane);
            }
            return accordion;
        }


    private void BuyItinerary(ItineraryBean itineraryBean, StackPane stackPane) {
        FXMLLoader loader = new FXMLLoader();
        FileInputStream fileInputStream;

        try {
            fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/view/fxml_view/ItineraryDetails-Page.fxml");
            Parent fxmlLoader = loader.load(fileInputStream);
            ItineraryDetailsController idc = loader.getController();
            idc.init(itineraryBean);

            stackPane.getChildren().add(fxmlLoader);
            stackPane.getChildren().get(0).setDisable(true);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void SaveItinerary(ItineraryBean itineraryBean, String type) {
        SaveTour st = new SaveTour();
        switch (type) {
            case "save" -> st.saveTour(SessionUser.getInstance().getSession(), itineraryBean);
            case "remove" -> st.removeTour(SessionUser.getInstance().getSession(), itineraryBean);
        }
    }

    private void goToMap(ArrayList<String> als, TitledPane titledPane) {

        WebView webView = new WebView();
        webView.setMinHeight(550);
        StringBuilder Url = new StringBuilder("https://google.it/maps/dir/" + SessionUser.getInstance().getSearchSession().getAddress());

        for (String element : als) {

            Url.append("/").append(element);

        }

        webView.getEngine().load(Url.toString());

        if(!Url.toString().equals("")){
           // webView.setMaxSize(1200, 600);
            //webView.maxWidthProperty().bind(webView.getScene().widthProperty());
            //webView.maxHeightProperty().bind(webView.getScene().heightProperty());
        } else {
            webView.maxWidthProperty().unbind();
            webView.maxHeightProperty().unbind();
        }

        VBox v = new VBox(webView);
        titledPane.setContent(v);

        //webView.getEngine().load(null);

    }
}
