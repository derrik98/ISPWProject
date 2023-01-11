package it.ispw.daniele.backpacker.view.commandlineinterface;

import it.ispw.daniele.backpacker.exceptions.AddressNotFoundException;
import it.ispw.daniele.backpacker.exceptions.CityNotFoundException;
import it.ispw.daniele.backpacker.exceptions.MonumentNotFoundException;
import it.ispw.daniele.backpacker.utils.SessionUser;

import java.io.IOException;
import java.util.Scanner;

import static it.ispw.daniele.backpacker.view.commandlineinterface.CLI.RED;
import static it.ispw.daniele.backpacker.view.commandlineinterface.CLI.RESET;

public class CliMenuGuideController {

    public void init() throws AddressNotFoundException, IOException, CityNotFoundException, MonumentNotFoundException {

        Scanner scanner = new Scanner(System.in);

        CliTouristGuideGraphicChange touristGuideGraphicChange = CliTouristGuideGraphicChange.getInstance();

        do {
            System.out.print("\033[H\033[2J");
            System.out.println("MENU'------------------------------UNDO [u]--");
            System.out.println("---------------------------------------------");
            System.out.println("----------------Home [0]---------------------");
            System.out.println("----------------Add Itinerary [1]------------");
            System.out.println("----------------Profile [2]------------------");
            System.out.println("----------------Logout [3]-------------------");
            System.out.println("---------------------------------------------");

            System.out.println("Command: ");

            switch (scanner.nextLine()) {
                case "0" -> {
                    CliHomeController homeController = new CliHomeController();
                    homeController.init(scanner);
                }
                case "1" -> {
                    CliAddItineraryController cliAddItineraryController = new CliAddItineraryController();
                    cliAddItineraryController.init();
                }
                case "2" -> {
                    CliGuideDetailsController cliGuideDetailsController = new CliGuideDetailsController();
                    cliGuideDetailsController.init();
                }
                case "3" -> {
                    System.out.println(RED + "LOGOUT" + RESET);
                    SessionUser.getInstance().closeSession();
                    touristGuideGraphicChange.switchToLogin(scanner);
                    return;
                }
                default -> System.out.println(RED + "Command not found\n" + RESET);
            }
        } while (true);
    }
}
