package it.ispw.daniele.backpacker.view.commandlineinterface;

import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.bean.ItineraryBean;
import it.ispw.daniele.backpacker.booktour.BookTourController;
import it.ispw.daniele.backpacker.utils.SessionUser;

import java.util.Scanner;

import static it.ispw.daniele.backpacker.view.commandlineinterface.CLI.BOLD;
import static it.ispw.daniele.backpacker.view.commandlineinterface.CLI.RESET;

public class CliItineraryDetailsController {

    private String command = "SUBSCRIBE";

    private final BookTourController controller = new BookTourController();
    private ItineraryBean ib = new ItineraryBean();
    private final GeneralUserBean sessionUser = SessionUser.getInstance().getSession();
    public void subscribe() {

        System.out.println(ib);

        boolean isPart = controller.isParticipating(this.sessionUser, ib);
        if(isPart){
            controller.removeParticipation(this.sessionUser, ib);
            System.out.print("Itinerary removed!");
            this.command = "SUBSCRIBE";
        } else {
            controller.addParticipation(this.sessionUser, ib);
            System.out.println("Itinerary added!");
            this.command = "REMOVE";
        }
    }

    public void init(ItineraryBean itineraryBean) {

        System.out.print("\033[H\033[2J");
        System.out.println(BOLD + "ITINERARY DETAILS PAGE\n" + RESET);
        System.out.flush();

        System.out.println("Itinerary id: " + itineraryBean.getItineraryId());

        System.out.println("Guide id: " + itineraryBean.getGuideId());

        System.out.println("Location: " + itineraryBean.getLocation());

        System.out.println("Date: " + itineraryBean.getDate());

        System.out.println("Time: " + itineraryBean.getTime());

        System.out.println("Participants: " + itineraryBean.getParticipants());

        System.out.println("Price: " + itineraryBean.getPrice());

        System.out.println("Steps: " + itineraryBean.getSteps());

        ib = itineraryBean;

        Scanner scanner = new Scanner(System.in);

        do{

        System.out.println("\nCOMMANDS [0] " + command + " - [1] UNDO\n");

        System.out.println("Digit command: ");


            switch (scanner.nextInt()) {
                case 0 -> {
                    this.subscribe();
                }
                case 1 -> {
                    System.out.println("undo");
                    return;
                }
                default -> {
                    return;
                }
            }
        }while(true);


    }
}
