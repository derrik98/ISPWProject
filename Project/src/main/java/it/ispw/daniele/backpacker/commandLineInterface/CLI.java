package it.ispw.daniele.backpacker.commandLineInterface;


public class CLI {

    public static final String RESET = "\033[0m";  // TEXT RESET
    public static final String RED = "\033[0;31m"; // RED
    public static final String GREEN = "\u001B[32m";
    public static final String BOLD = "\033[0;1m"; // BOLD

    public static void main(String[] args) {

        CliLoginController cliLoginController = new CliLoginController();
        cliLoginController.init();

    }
}
