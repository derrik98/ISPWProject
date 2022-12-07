package it.ispw.daniele.backpacker.commandLineInterface;

import it.ispw.daniele.backpacker.bean.ItineraryBean;
import it.ispw.daniele.backpacker.booktour.BookTourController;
import it.ispw.daniele.backpacker.booktour.SaveTour;
import it.ispw.daniele.backpacker.controller.search.SearchController;
import it.ispw.daniele.backpacker.exceptions.MonumentNotFoundException;
import it.ispw.daniele.backpacker.fxmlView.ItineraryDetailsController;
import it.ispw.daniele.backpacker.fxmlView.TouristGuideGraphicChange;
import it.ispw.daniele.backpacker.fxmlView.UserGraphicChange;
import it.ispw.daniele.backpacker.utils.Roles;
import it.ispw.daniele.backpacker.utils.SessionUser;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;

import java.io.Console;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;

import static it.ispw.daniele.backpacker.commandLineInterface.CLI.*;

public class CliResultController {

    List<ItineraryBean> it;

   /* public void init() {

        //GeneralUserBean sessionUser = SessionUser.getInstance().getSession();

        if (SessionUser.getInstance().getSession().getRole().equals(Roles.TOURIST_GUIDE.name().toLowerCase())) {
            TouristGuideGraphicChange i = TouristGuideGraphicChange.getInstance();
            //i.menuBar(this.menuBar, "result");
            System.out.println(SessionUser.getInstance().getSession().getRole() + Roles.TOURIST_GUIDE.name());
        } else {
            UserGraphicChange ugc = UserGraphicChange.getInstance();
            //ugc.menuBar(this.menuBar, "result");
            System.out.println(SessionUser.getInstance().getSession().getRole() + Roles.USER.name().toLowerCase());
        }

    }*/

    public void init(String country, String city, String address, String restaurant, String range) throws MonumentNotFoundException {
        System.out.print("\033[H\033[2J");
        System.out.println(BOLD + "RESULT PAGE\n" + RESET);
        System.out.println("Country: " + country + ", City: " + city + ", Address: " + address + ", Restaurant: " + restaurant + ", Range: " + range + "\n");

        BookTourController btc = new BookTourController();
        //List<ItineraryBean> it;
        it = btc.getItinerary(city, "city");
        int bookedSize = 0;

        if (it == null || it.isEmpty()) {
            System.out.println("Suggested Itinerary: " + RED + "EMPTY_DATABASE " + RESET + "\n");
        } else {
            System.out.println("Suggested Itinerary: \n");
            createTable(it);
            bookedSize = it.size();
        }

        SearchController sc = new SearchController();
        List<ItineraryBean> iti;
        iti = sc.createItinerary(address);

        if (iti == null) {
            System.out.println("Self Itinerary: " + RED + "EMPTY_DATABASE " + RESET + "\n");
        } else {
            System.out.print("Self Itinerary: \n");
            createTable(iti);
        }
        assert it != null;
        List<ItineraryBean> mergeItinerary = new ArrayList<>(it);
        assert iti != null;
        mergeItinerary.addAll(iti);
        createCommand(mergeItinerary, bookedSize - 1);
    }

    private void createCommand(List<ItineraryBean> itineraryBeanList, int bSize) {
        System.out.flush();
        Scanner scanner = new Scanner(System.in);
        do {
        System.out.println("" + "Commands : VIEW ON MAP[0] - SAVE[1] - BUY[2] (Only for suggester itinerary) - QUIT[3]");
            switch (scanner.nextLine()) {
                case "0" -> {
                    System.out.println("View on map");
                }
                case "1" -> {
                    SaveTour st = new SaveTour();
                    try {
                        System.out.println("Digit Itinerary id");
                        int input = scanner.nextInt();
                        if(input > bSize){
                            st.saveTour(SessionUser.getInstance().getSession(), itineraryBeanList.get(scanner.nextInt()));
                            System.out.println(GREEN + "Itinerary added successfully" + RESET);
                        }
                        else{
                            System.out.println(RED + "Incorrect id" + RESET);
                            scanner.reset();
                        }
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "2" -> {
                    System.out.println("Digit Itinerary id");
                    int input = scanner.nextInt();
                    if(input <= bSize && input >= bSize){
                        CliItineraryDetailsController cidc = new CliItineraryDetailsController();
                        cidc.init(itineraryBeanList.get(input));
                    }
                    else{
                        System.out.println(RED + "Incorrect id" + RESET);
                    }
                }
                case "3" ->{
                    System.out.println("QUIT");
                    return;
                }
                default -> System.out.println(RED + "Command not found\n" + RESET);
            }
        } while (true);
    }

    public void createTable(List<ItineraryBean> itineraryBeanList) {

        int j;
        StringBuilder Url = new StringBuilder("https://google.it/maps/dir");
        for (j = 0; j < itineraryBeanList.size(); j++) {
            String[] steps = itineraryBeanList.get(j).getSteps().split("/");
            ArrayList<String> als = new ArrayList<>();
            for (int i = 0; i < steps.length; i++) {
                als.add(i, steps[i]);
            }

            //WebView webView = new WebView();
            //webView.setMinHeight(300);
            //StringBuilder Url = new StringBuilder("https://google.it/maps/dir");
            StringBuilder line = new StringBuilder();
            line.append("ID [").append(j).append("] ");
            for (int indexMonument = 0; indexMonument < als.size(); indexMonument++) {
                line.append(als.get(indexMonument));

                if (indexMonument != 0 && indexMonument != als.size() - 1) {
                    line.append(" - ");
                    Url.append("/").append(als.get(indexMonument));

                }
            }
            System.out.println(line);
            System.out.print("\n");

           /* webView.getEngine().load(Url.toString());
            VBox v = new VBox(webView);

            if (type.equals("suggested")) {
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

            int finalJ1 = j;
            ivSave.setOnMouseClicked(mouseEvent -> {
                System.out.println("SONO QUI");

                SaveTour st = new SaveTour();
                try {
                    System.out.println("ITINERARIO ." + itineraryBeanList.get(finalJ1).getSteps());
                    st.saveTour(SessionUser.getInstance().getSession(), itineraryBeanList.get(finalJ1));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            });*/
        }
        //createCommand(itineraryBeanList);
    }
}
