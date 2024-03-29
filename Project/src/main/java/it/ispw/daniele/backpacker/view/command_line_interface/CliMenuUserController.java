package it.ispw.daniele.backpacker.view.command_line_interface;

import it.ispw.daniele.backpacker.exceptions.AddressNotFoundException;
import it.ispw.daniele.backpacker.exceptions.CityNotFoundException;
import it.ispw.daniele.backpacker.exceptions.MonumentNotFoundException;
import it.ispw.daniele.backpacker.utils.SessionUser;

import java.io.IOException;
import java.util.Scanner;

import static it.ispw.daniele.backpacker.view.command_line_interface.CLI.RED;
import static it.ispw.daniele.backpacker.view.command_line_interface.CLI.RESET;

public class CliMenuUserController {

    private final Scanner scanner = new Scanner(System.in);

    public void init() throws AddressNotFoundException, IOException, CityNotFoundException, MonumentNotFoundException {

        CliUserGraphicChange ugc = CliUserGraphicChange.getInstance();

        do {
            System.out.print("\033[H\033[2J");
            System.out.println("MENU'------------------------------UNDO [u]--");
            System.out.println("---------------------------------------------");
            System.out.println("----------------Home [0]---------------------");
            System.out.println("----------------Profile [1]------------------");
            System.out.println("----------------Logout [2]-------------------");
            System.out.println("---------------------------------------------");

            System.out.println("Command: ");

            switch (scanner.nextLine()) {
                case "0" -> {
                    CliHomeController homeController = new CliHomeController();
                    homeController.init(scanner);
                    System.out.flush();
                }
                case "1" -> {
                    CliUserDetailsController cliUserDetailsController = new CliUserDetailsController();
                    cliUserDetailsController.init();
                }
                case "2" -> {
                    System.out.println(RED + "LOGOUT" + RESET);
                    SessionUser.getInstance().closeSession();
                    ugc.switchToLogin(scanner);
                    return;
                }
                case "u" ->{

                }
                default -> System.out.println(RED + "Command not found\n" + RESET);
            }

            System.out.flush();

        } while (true);
    }
}
