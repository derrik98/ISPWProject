package it.ispw.daniele.backpacker.commandLineInterface;

import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.bean.ItineraryBean;
import it.ispw.daniele.backpacker.controller.addItinerary.AddItineraryController;
import it.ispw.daniele.backpacker.fxmlView.TouristGuideGraphicChange;
import it.ispw.daniele.backpacker.utils.SessionUser;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.Scanner;

public class CliAddItineraryController {

    private String steps = "";
    private GeneralUserBean guideBean;
    public ListView<Object> listView = new ListView<>();
    private AddItineraryController controller;
    Scanner scanner = new Scanner(System.in);

    public void init() {

        TouristGuideGraphicChange guideGraphicChange = TouristGuideGraphicChange.getInstance();
        //guideGraphicChange.menuBar(this.menuBar, "addItinerary");

        //fieldDate.setStyle("-fx-font-size: 20");

        this.controller = new AddItineraryController();

        for(int i = 1; i < 10; i++){
            System.out.println("step -> + i");

            if(scanner.nextLine().equals("share")){
                this.share();
            }
            else{
                listView.getItems().add(scanner.nextLine());
            }

            TextField textField = new TextField();
            textField.setStyle("-fx-font-size: 20");
            textField.setId(String.valueOf(i));
            textField.setPromptText("step -> " + i);


        }
        this.guideBean= SessionUser.getInstance().getSession();
    }

    private void share() {
        for(int i = 0; i < this.listView.getItems().size(); i++){
            TextField t = (TextField) listView.getItems().get(i);
            if(!t.getText().equals("") && t.getText() != null) {
                this.steps = this.steps.concat(t.getText() + "/");
            }

        }
        System.out.println(this.steps);

        System.out.println("condiviso");
        System.out.println("Itinerary id:");
        String id = scanner.nextLine();

        System.out.println("Location:");
        String location = scanner.nextLine();

        System.out.println("Date:");
        String date = scanner.nextLine();

        System.out.println("Time:");
        String time = scanner.nextLine();

        System.out.println("Participants:");
        String participants = scanner.nextLine();

        System.out.println("Price:");
        String price = scanner.nextLine();

        boolean result = false;

       /* if(this.fieldDate.getValue() != null) {
            date = this.fieldDate.getValue().toString();
        }*/

        ItineraryBean itineraryBean = new ItineraryBean();
        itineraryBean.setItineraryId(id);
        itineraryBean.setGuideId(this.guideBean.getUsername());
        itineraryBean.setDate(date);
        itineraryBean.setLocation(location);
        itineraryBean.setTime(time);
        itineraryBean.setParticipants(participants);
        itineraryBean.setPrice(price);
        itineraryBean.setSteps(this.steps);
        System.out.println(this.guideBean.getUsername());
        System.out.println("Dati" + itineraryBean.getGuideId() + "\n" + itineraryBean.getDate() + "\n" + itineraryBean.getLocation() + "\n" + itineraryBean.getTime() + "\n" + itineraryBean.getParticipants() + "\n" + itineraryBean.getPrice());

        try {
            result = controller.addItinerary(itineraryBean);
            if(result){
                //this.headerLabel.setText("Music Event Added");
                System.out.println("itinerario aggiunto");
            }
            else{
                System.out.println("errore aggiunta itinerario");
                //this.headerLabel.setText("Failed to add music event");
            }
        } catch (Exception e) {
            e.printStackTrace();
            //this.headerLabel.setText(de.getMessage());
        }
    }

}
