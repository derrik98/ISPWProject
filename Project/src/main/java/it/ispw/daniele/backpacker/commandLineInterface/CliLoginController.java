package it.ispw.daniele.backpacker.commandLineInterface;

import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.controller.login.LoginController;
import it.ispw.daniele.backpacker.exceptions.EmptyFieldException;
import it.ispw.daniele.backpacker.exceptions.LoginFailException;
import it.ispw.daniele.backpacker.utils.SessionUser;

import java.util.Scanner;

import static it.ispw.daniele.backpacker.commandLineInterface.CLI.RED;
import static it.ispw.daniele.backpacker.commandLineInterface.CLI.RESET;

public class CliLoginController {

    public void init() {

        Scanner scan = new Scanner(System.in);

        do {
            System.out.print("\033[H\033[2J");
            System.out.println("----------------------------------EXIT [quit]");
            System.out.println("---------------------------------------------");
            System.out.println("----------------LOGIN [0]--------------------");
            System.out.println("----------------SIGNUP [1]-------------------");
            System.out.println("---------------------------------------------");
            System.out.println("---------------------------------------------");

            System.out.println("Command: ");

            switch (scan.nextLine()) {
                case "0" -> {
                    this.login(scan);
                    //CliLoginController loginController = new CliLoginController();
                    //loginController.init(scan);
                }
                case "1" -> {
                    CliSignUpController signUpController = new CliSignUpController();
                    signUpController.init();
                }
                case "quit" -> {
                    System.out.println(RED + "CLOSE APPLICATION" + RESET);
                    scan.close();
                    return;
                }
                default -> System.out.println(RED + "Command not found\n" + RESET);
            }
            System.out.flush();

        } while(scan.hasNext());
    }

    private void login(Scanner scan) {
        System.out.print("\033[H\033[2J");

        System.out.println("Username:");
        String username = scan.nextLine();
        System.out.flush();

        System.out.println("Password:");
        String password = scan.nextLine();
        System.out.flush();

        GeneralUserBean gub = new GeneralUserBean();
        gub.setUsername(username);
        gub.setPassword(password);

        LoginController controller = new LoginController();
        GeneralUserBean gu;
        try {
            gu = controller.login(gub);
            if (gu == null) {
                System.out.println(RED + "LOGIN FAILED!" + RESET);
            } else {
                String role = gu.getRole();

                //SET SESSION GENERAL USER
                SessionUser su = SessionUser.getInstance();
                su.setSession(gu);

                switch (role) {
                    case "user" -> CliUserGraphicChange.getInstance().menuBar();
                    case "tourist_guide" -> CliTouristGuideGraphicChange.getInstance().menuBar();
                    default -> {
                    }
                }
            }

        } catch (EmptyFieldException e) {
            System.out.println("\n" + RED + e.getMessage() + RESET + "\n");
        }
    }
}
