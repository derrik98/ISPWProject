package it.ispw.daniele.backpacker.commandLineInterface;


import java.util.Scanner;

public class CLI {

    public static final String RESET = "\033[0m";  // Text Reset
    public static final String RED = "\033[0;31m";     // RED

    public static void main(String[] args) {

        CliLoginController cliLoginController = new CliLoginController();
        cliLoginController.init();
    }
}
