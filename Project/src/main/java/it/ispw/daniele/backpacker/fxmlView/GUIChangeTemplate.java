package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.exceptions.MonumentNotFoundException;
import it.ispw.daniele.backpacker.utils.Roles;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class GUIChangeTemplate{

    protected final Logger logger = Logger.getLogger("GraphicChange");

    protected Roles whoAmI;

    public GUIChangeTemplate(){
    }

    public void catcher(GUIAction guiAction){
        try {
            guiAction.action();
        }catch (IOException | MonumentNotFoundException ioException){
            logger.log(Level.WARNING, ioException.toString(), ioException.getCause());
        }
    }

    @FXML
    public void switchToLogin(Scene scene){
        this.catcher(new GUIAction() {
            @Override
            public void action() throws IOException {
                FXMLLoader loader = new FXMLLoader();
                FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/LoginView-Page.fxml");
                Parent fxmlLoader = loader.load(fileInputStream);
                LoginViewController lvc = loader.getController();
                scene.setRoot(fxmlLoader);
                lvc.init();
            }
        });
    }

    public void switchToResult(Scene scene, String country, String city, String address, String restaurant, String range) {
        this.catcher(new GUIAction() {
            @Override
            public void action() throws IOException, MonumentNotFoundException {
                FXMLLoader loader = new FXMLLoader();
                FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/Result-Page.fxml");
                Parent fxmlLoader = loader.load(fileInputStream);
                ResultController rc = loader.getController();
                scene.setRoot(fxmlLoader);
                System.out.println("VVVVVVVVVVVVV"  +country + city + address + restaurant + range);
                rc.init(country, city, address, restaurant, range);


            }
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

    public void menuBar (HBox pos, String sel){
        this.catcher(new GUIAction() {
            @Override
            public void action() throws IOException {
                FXMLLoader loader;
                switch (whoAmI){
                    case USER -> {
                        loader = new FXMLLoader();
                        FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/MenuBar.fxml");
                        Parent fxmlLoader = loader.load(fileInputStream);
                        MenuBarController mbc = loader.getController();
                        pos.getChildren().add(fxmlLoader);
                        mbc.init(sel);
                    }
                    case TOURIST_GUIDE -> {
                        loader = new FXMLLoader();
                        FileInputStream fileInputStream = new FileInputStream("Project/src/main/java/it/ispw/daniele/backpacker/fxmlView/TouristGuideMenuBar.fxml");
                        Parent fxmlLoader = loader.load(fileInputStream);
                        TouristGuideMenuBarController gbc = loader.getController();
                        pos.getChildren().add(fxmlLoader);
                        gbc.init(sel);
                    }
                    default -> {
                    }
                }
            }
        });
    }

    public abstract void switchToHomePage(Scene scene) throws IOException;

}

