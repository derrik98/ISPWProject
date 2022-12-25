package it.ispw.daniele.backpacker.utils;

import it.ispw.daniele.backpacker.bean.ItineraryBean;
import it.ispw.daniele.backpacker.bean.TouristGuideBean;
import it.ispw.daniele.backpacker.bean.UserBean;
import it.ispw.daniele.backpacker.booktour.SaveTour;
import it.ispw.daniele.backpacker.entity.Itinerary;
import it.ispw.daniele.backpacker.entity.TouristGuide;
import it.ispw.daniele.backpacker.entity.User;
import it.ispw.daniele.backpacker.view.fxmlView.ItineraryDetailsController;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Controller {

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

    protected Accordion createTable(List<ItineraryBean> itineraryBeanList, String type, String from){

        Accordion accordion = new Accordion();
        int jind;
        for(jind = 0; jind < itineraryBeanList.size(); jind++) {
            System.out.println(itineraryBeanList.get(jind).getSteps());
            String[] steps = itineraryBeanList.get(jind).getSteps().split("/");
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
            //HBox.setHgrow(region1, Priority.ALWAYS);

            HBox region2 = new HBox();
            region2.setMinWidth(15);
            region2.setMaxWidth(Double.MAX_VALUE);
            //HBox.setHgrow(region2, Priority.ALWAYS);

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

                    contentPane.getChildren().add(space);

                    label.setCursor(Cursor.HAND);
                    label.setOnMouseClicked(mouseEvent -> System.out.println("label cliccata"));
                }

                Url.append("/").append(als.get(indexMonument));

            }
            webView.getEngine().load(Url.toString());
            VBox v = new VBox(webView);
            titledPane.setContent(v);

            if(type.equals("suggested")){

                //Setting of buy image
                ImageView ivBuy = new ImageView();
                ivBuy = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/buy.png")).toExternalForm()));
                ivBuy.setFitWidth(50);
                ivBuy.setFitHeight(50);
                ivBuy.setCursor(Cursor.HAND);

                int finalJ = jind;
                ivBuy.setOnMouseClicked(mouseEvent -> {
                    FXMLLoader loader = new FXMLLoader();
                    FileInputStream fileInputStream;
                    try {

                        fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/ItineraryDetails-Page.fxml");
                        Parent fxmlLoader = loader.load(fileInputStream);
                        ItineraryDetailsController idc = loader.getController();
                        idc.init(itineraryBeanList.get(finalJ));
                        ////this.stackPaneResult.getChildren().add(fxmlLoader);  C'ERANOOOOOO
                        /////stackPaneResult.getChildren().get(0).setDisable(true); OOOOOOOOO
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                contentPane.getChildren().addAll(region, ivBuy);
            }

            else {
                HBox.setHgrow(region1, Priority.ALWAYS);
            }

            //Setting of save image
            ImageView ivSave;
            int finalJ1 = jind;
            if(from.equals("profile")){
                ivSave = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/cestino.png")).toExternalForm()));
                ivSave.setOnMouseClicked(mouseEvent -> {
                    SaveTour st = new SaveTour();
                    System.out.println("ITINERARIO ." + itineraryBeanList.get(finalJ1).getSteps());
                    st.removeTour(SessionUser.getInstance().getSession(), itineraryBeanList.get(finalJ1));
                });
            }
            else{
                ivSave = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/save.png")).toExternalForm()));
                ivSave.setOnMouseClicked(mouseEvent -> {

                    SaveTour st = new SaveTour();
                    try {
                        System.out.println("ITINERARIO ." + itineraryBeanList.get(finalJ1).getSteps());
                        st.saveTour(SessionUser.getInstance().getSession(), itineraryBeanList.get(finalJ1));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

            ivSave.setCursor(Cursor.HAND);
            ivSave.setFitWidth(35);
            ivSave.setFitHeight(35);


            contentPane.getChildren().addAll(region1, ivSave);

            //Setting of map image
            ImageView ivMap = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/googleMaps.png")).toExternalForm()));
            ivMap.setFitWidth(35);
            ivMap.setFitHeight(35);
            ivMap.setCursor(Cursor.HAND);
            ivMap.setId("MAP");


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

            contentPane.getChildren().addAll(region2, ivMap);

            titledPane.setGraphic(contentPane);
            accordion.getPanes().add(titledPane);
        }
        return accordion;
    }
}
