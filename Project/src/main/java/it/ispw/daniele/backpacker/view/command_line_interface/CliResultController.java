package it.ispw.daniele.backpacker.view.command_line_interface;

import it.ispw.daniele.backpacker.bean.HomeBean;
import it.ispw.daniele.backpacker.bean.ItineraryBean;
import it.ispw.daniele.backpacker.booktour.BookTourController;
import it.ispw.daniele.backpacker.booktour.SaveTour;
import it.ispw.daniele.backpacker.controller.search.SearchController;
import it.ispw.daniele.backpacker.exceptions.GenericException;
import it.ispw.daniele.backpacker.exceptions.MonumentNotFoundException;
import it.ispw.daniele.backpacker.utils.SessionUser;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static it.ispw.daniele.backpacker.view.command_line_interface.CLI.*;

public class CliResultController {

    List<ItineraryBean> it;

    public void init(HomeBean homeBean) throws MonumentNotFoundException, NoSuchAlgorithmException, GenericException {
        System.out.print("\033[H\033[2J");
        System.out.println(BOLD + "RESULT PAGE\n" + RESET);
        System.out.println("Country: " + homeBean.getCountry() + ", City: " + homeBean.getCity() + ", Address: " + homeBean.getAddress() + ", Restaurant: " + homeBean.isRestaurant() + ", Range: " + homeBean.getRange() + "\n");

        BookTourController btc = new BookTourController();
        //List<ItineraryBean> it;
        it = btc.getItinerary(homeBean.getCity(), "city");
        int bookedSize = 0;

        if (it == null || it.isEmpty()) {
            System.out.println("Suggested Itinerary: " + RED + "EMPTY_DATABASE " + RESET + "\n");
        } else {
            System.out.println("Suggested Itinerary: \n");
            bookedSize = it.size();
            createTable(it, 0);
        }

        SearchController sc = new SearchController();
        List<ItineraryBean> iti;
        //iti = sc.createItinerary(homeBean.getAddress());
        iti = sc.createItinerary(homeBean);

        if (iti == null) {
            System.out.println("Self Itinerary: " + RED + "EMPTY_DATABASE " + RESET + "\n");
        } else {
            System.out.print("Self Itinerary: \n");
            createTable(iti, bookedSize);
        }
        assert it != null;
        List<ItineraryBean> mergeItinerary = new ArrayList<>(it);
        assert iti != null;
        mergeItinerary.addAll(iti);
        createCommand(mergeItinerary, bookedSize - 1);
    }

    private void createCommand(List<ItineraryBean> itineraryBeanList, int bSize) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("" + "Commands : VIEW ON MAP[0] - SAVE[1] - BUY[2] (Only for suggester itinerary) - QUIT[3]");

        do {
            switch (scanner.nextLine()) {
                case "0" -> {
                    System.out.println("Digit Itinerary id");
                    int input = scanner.nextInt();
                    System.out.flush();

                    String[] steps = itineraryBeanList.get(input).getSteps().split("/");
                    System.out.println(Arrays.toString(steps));

                    ArrayList<String> als = new ArrayList<>();
                    for (int i = 0; i < steps.length; i++) {
                        als.add(i, steps[i]);
                    }

                    System.out.println("als" + als);
                    StringBuilder Url = new StringBuilder("https://google.it/maps/dir");

                    for (int indexMonument = 0; indexMonument < als.size(); indexMonument++) {
                            Url.append("/").append(als.get(indexMonument));
                    }

                    if(Desktop.isDesktopSupported()){
                        Desktop desktop = Desktop.getDesktop();
                        try {
                            desktop.browse(new URI(Url.toString().replace(" ", "+")));
                        } catch (IOException | URISyntaxException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }else{
                        Runtime runtime = Runtime.getRuntime();
                        try {
                            runtime.exec("xdg-open " + Url);
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }

                case "1" -> {
                    SaveTour st = new SaveTour();
                    System.out.println("Digit Itinerary id");
                    int input = scanner.nextInt();
                    System.out.flush();
                    if(input <= itineraryBeanList.size() && input >= 0){
                        st.saveTour(SessionUser.getInstance().getSession(), itineraryBeanList.get(input));
                        System.out.println(GREEN + "Itinerary added successfully" + RESET);
                    }//   COMMENTARE IL CODICE
                    else{
                        System.out.println(RED + "Incorrect id" + RESET);
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
            System.out.flush();
            scanner.reset();
            System.out.println("" + "Commands : VIEW ON MAP[0] - SAVE[1] - BUY[2] (Only for suggester itinerary) - QUIT[3]");
        } while (true);
    }

    public void createTable(List<ItineraryBean> itineraryBeanList, int tableSize) {

        int j;
        for (j = 0; j < itineraryBeanList.size() ; j++) {
            String[] steps = itineraryBeanList.get(j).getSteps().split("/");
            ArrayList<String> als = new ArrayList<>();
            for (int i = 0; i < steps.length; i++) {
                als.add(i, steps[i]);
            }

            StringBuilder line = new StringBuilder();
            line.append("ID [").append(tableSize).append("] ");
            tableSize++;
            for(int indexMonument = 0; indexMonument < als.size(); indexMonument++) {
                line.append(als.get(indexMonument));

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
