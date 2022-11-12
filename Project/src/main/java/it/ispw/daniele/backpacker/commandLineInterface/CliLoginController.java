package it.ispw.daniele.backpacker.commandLineInterface;

import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.controller.login.LoginController;
import it.ispw.daniele.backpacker.exceptions.EmptyFieldException;
import it.ispw.daniele.backpacker.utils.SessionUser;

import java.util.Scanner;

public class CliLoginController {

    public static final String RESET = "\033[0m";  // Text Reset
    public static final String RED = "\033[0;31m";     // RED
    private final Scanner scan = new Scanner(System.in);

    public void init() {

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
                    this.login();

                    //CliLoginController loginController = new CliLoginController();
                    //loginController.init(scan);
                }
                case "1" -> {
                    CliSignUpController signUpController = new CliSignUpController();
                    signUpController.init(scan);
                }
                case "quit" -> {
                    System.out.println(RED + "CLOSE APPLICATION" + RESET);
                    scan.close();
                    return;
                }
                default -> System.out.println(RED + "Command not found\n" + RESET);
            }

        } while (scan.hasNext());
    }

    private void login() {
        System.out.print("\033[H\033[2J");

        System.out.println("Username:");
        String username = scan.nextLine();

        System.out.println("Password:");
        String password = scan.nextLine();

        GeneralUserBean gub = new GeneralUserBean();
        gub.setUsername(username);
        gub.setPassword(password);

        LoginController controller = new LoginController();
        GeneralUserBean gu;
        try {
            gu = controller.login(gub);
            if (gu == null) {
                System.out.println(RED + "Login failed!" + RESET);
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
