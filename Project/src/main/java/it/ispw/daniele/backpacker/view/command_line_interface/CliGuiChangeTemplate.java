package it.ispw.daniele.backpacker.view.command_line_interface;

import it.ispw.daniele.backpacker.exceptions.AddressNotFoundException;
import it.ispw.daniele.backpacker.exceptions.CityNotFoundException;
import it.ispw.daniele.backpacker.exceptions.MonumentNotFoundException;
import it.ispw.daniele.backpacker.utils.Roles;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class CliGuiChangeTemplate {
    protected final Logger logger = Logger.getLogger("GraphicChange");

    protected Roles whoAmI;

    public void catcher(CliGuiAction cGuiAction){
        try {
            cGuiAction.action();
        }catch (IOException | AddressNotFoundException | CityNotFoundException | MonumentNotFoundException ioException){
            logger.log(Level.WARNING, ioException.toString(), ioException.getCause());
        }
    }

    public void switchToLogin(Scanner scanner){
        this.catcher(() -> {
            CliLoginController clc = new CliLoginController();
            clc.init();
        });
    }

    /*public void switchToProfile(Scene scene, UserBean myUser){//(Scene scene, UserBean ub, String from, String searchstring) {
        this.catcher(new GUIAction() {
            @Override
            public void action() throws IOException {
                FXMLLoader loader = new FXMLLoader();
                FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/User-Details-Page.fxml");
                Parent fxmlLoader = loader.load(fileInputStream);
                ProfileController pc = loader.getController();
                scene.setRoot(fxmlLoader);
                pc.init(myUser);
            }
        });
    }*/

    public void menuBar (){
        this.catcher(() -> {
            System.out.println("roles" + whoAmI);
            switch (whoAmI){
                case USER -> {
                    CliMenuUserController cliMenuUserController = new CliMenuUserController();
                    cliMenuUserController.init();
                }
                case TOURIST_GUIDE -> {
                    CliMenuGuideController cliMenuGuideController = new CliMenuGuideController();
                    cliMenuGuideController.init();
                }
                default -> {
                }
            }
        });
    }

    //public abstract void switchToHomePage(Scanner scanner) throws IOException;

}
