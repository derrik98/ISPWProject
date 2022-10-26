package it.ispw.daniele.backpacker.commandLineInterface;


import java.util.Scanner;

public class CLI {

    public static final String RESET = "\033[0m";  // Text Reset
    public static final String RED = "\033[0;31m";     // RED

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        do
        {
        System.out.println("-----------------------------------EXIT [quit]--");
        System.out.println("------------------------------------------------");
        System.out.println("----------------LOGIN [0]-----------------------");
        System.out.println("----------------SIGNUP [1]----------------------");
        System.out.println("------------------------------------------------");
        System.out.println("------------------------------------------------");

        System.out.println("Command: ");

            switch (scan.nextLine()) {
                case "0":
                    CliLoginController loginController = new CliLoginController();
                    loginController.init(scan);
                    break;
                case "1":
                    CliSignUpController signUpController = new CliSignUpController();
                    signUpController.init(scan);
                    break;
                case "quit":
                    return;
                default:
                    System.out.println(RED + "Command not found" + RESET);
                    break;
            }
            System.out.flush();
        } while (true);
    }
}
