package it.ispw.daniele.backpacker.view.commandlineinterface;

import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.bean.ItineraryBean;
import it.ispw.daniele.backpacker.controller.additinerary.AddItineraryController;
import it.ispw.daniele.backpacker.utils.SessionUser;

import java.util.ArrayList;
import java.util.Scanner;

import static it.ispw.daniele.backpacker.view.commandlineinterface.CLI.RED;
import static it.ispw.daniele.backpacker.view.commandlineinterface.CLI.RESET;

public class CliAddItineraryController {

    private String steps = "";
    private GeneralUserBean guideBean = SessionUser.getInstance().getSession();
    private final ArrayList<String> listView = new ArrayList<>();
    private AddItineraryController controller;
    Scanner scanner = new Scanner(System.in);

    public void init() {

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
            }
        }
        this.guideBean= SessionUser.getInstance().getSession();
    }

    private void share() {

        for (String step : this.listView) {
            if (!step.equals("")) {
                this.steps = this.steps.concat(step + "/");
            }

        }

        System.out.println(this.steps);

        System.out.println("Itinerary details:\n");

        System.out.println("Location:");
        String location = scanner.nextLine();
        System.out.flush();

        System.out.println("Date: format[YYYY/MM/DD]");
        String date = scanner.nextLine().replace("/", "-");
        System.out.flush();

        System.out.println("Time:");
        String time = scanner.nextLine();
        System.out.flush();

        System.out.println("Participants:");
        String participants = scanner.nextLine();
        System.out.flush();

        System.out.println("Price:");
        String price = scanner.nextLine();
        System.out.flush();

        boolean result;

        ItineraryBean itineraryBean = new ItineraryBean();
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
                System.out.println("Correct share!\n");
            }
            else{
                System.out.print(RED + "Error share\n" + RESET);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print(RED + "Error share\n" + RESET);
        }
    }
}
