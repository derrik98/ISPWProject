package it.ispw.daniele.backpacker.commandLineInterface;

import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.bean.ItineraryBean;
import it.ispw.daniele.backpacker.controller.addItinerary.AddItineraryController;
import it.ispw.daniele.backpacker.fxmlView.TouristGuideGraphicChange;
import it.ispw.daniele.backpacker.utils.SessionUser;

import java.util.ArrayList;
import java.util.Scanner;

import static it.ispw.daniele.backpacker.commandLineInterface.CLI.RED;
import static it.ispw.daniele.backpacker.commandLineInterface.CLI.RESET;

public class CliAddItineraryController {

    private String steps = "";
    private GeneralUserBean guideBean = SessionUser.getInstance().getSession();
    //private ListView<String> listView;
    private ArrayList<String> listView = new ArrayList<>();

    private AddItineraryController controller;
    Scanner scanner = new Scanner(System.in);

    public void init() {

        TouristGuideGraphicChange guideGraphicChange = TouristGuideGraphicChange.getInstance();
        //guideGraphicChange.menuBar(this.menuBar, "addItinerary");

        //fieldDate.setStyle("-fx-font-size: 20");

        this.controller = new AddItineraryController();

        System.out.println("Digit 'share' to share itinerary");
        for(int i = 1; i < 10; i++){
            System.out.println("step -> " + i);

            String input = scanner.nextLine();

            if(input.equals("share")){
                this.share();
                return;
            }
            else{
                listView.add(input);
                //listView.getItems().add(scanner.nextLine());
            }

            /*TextField textField = new TextField();
            textField.setStyle("-fx-font-size: 20");
            textField.setId(String.valueOf(i));
            textField.setPromptText("step -> " + i);
*/

        }
        this.guideBean= SessionUser.getInstance().getSession();
    }

    private void share() {
        /*for(int i = 0; i < this.listView.getItems().size(); i++){
            String t = listView.getItems().get(i);
            if(!t.equals("") && t != null) {
                this.steps = this.steps.concat(t + "/");
            }

        }*/

        for(int i = 0; i < this.listView.size(); i++){
            String t = listView.get(i);
            if(!t.equals("") && t != null) {
                this.steps = this.steps.concat(t + "/");
            }

        }

        System.out.println(this.steps);

        System.out.println("Itinerary details:\n");
        System.out.println("Itinerary id:");
        String id = scanner.nextLine();

        System.out.println("Location:");
        String location = scanner.nextLine();

        System.out.println("Date: format[YYYY/MM/DD]");
        String date = scanner.nextLine().replace("/", "-");

        System.out.println("Time:");
        String time = scanner.nextLine();

        System.out.println("Participants:");
        String participants = scanner.nextLine();

        System.out.println("Price:");
        String price = scanner.nextLine();

        boolean result = false;

        ItineraryBean itineraryBean = new ItineraryBean();
        itineraryBean.setItineraryId(id);
        itineraryBean.setGuideId(this.guideBean.getUsername());
        itineraryBean.setDate(date);
        itineraryBean.setLocation(location);
        itineraryBean.setTime(time);
        itineraryBean.setParticipants(Integer.parseInt(participants));
        itineraryBean.setPrice(Integer.parseInt(price));
        itineraryBean.setSteps(this.steps);
        System.out.println(this.guideBean.getUsername());
        System.out.println("Dati" + itineraryBean.getGuideId() + "\n" + itineraryBean.getDate() + "\n" + itineraryBean.getLocation() + "\n" + itineraryBean.getTime() + "\n" + itineraryBean.getParticipants() + "\n" + itineraryBean.getPrice());

        try {
            result = controller.addItinerary(itineraryBean);
            if(result){
                //this.headerLabel.setText("Music Event Added");
                System.out.println("Correct share!\n");
            }
            else{
                System.out.print(RED + "Error share\n" + RESET);
                //this.headerLabel.setText("Failed to add music event");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print(RED + "Error share\n" + RESET);
            //this.headerLabel.setText(de.getMessage());
        }
    }

}
