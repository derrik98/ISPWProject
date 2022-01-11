package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.utils.Roles;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GUIChangeTemplate extends App{

    protected final Logger logger = Logger.getLogger("GraphicChange");

    protected Roles whoAmI;

    public GUIChangeTemplate(){
    }

    public void catcher(GUIAction guiAction){
        try {
            guiAction.action();
        }catch (IOException ioException){
            logger.log(Level.WARNING, ioException.toString());
        }
    }

    public void swithToLogin(Scene scene){
        this.catcher(new GUIAction() {
            @Override
            public void action() throws IOException {
                LoginViewController loginController = new LoginViewController();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login-Page.fxml"));
                fxmlLoader.setController(loginController);
                scene.setRoot(fxmlLoader.load());

            }
        });
    }

//    public void switchToHomepage(Scene scene) throws IOException {
//        this.catcher(new GUIAction() {
//            @Override
//            public void action() throws IOException {
//                HomeController homeController = new HomeController();
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("src/main/java/it/ispw/daniele/backpacker/fxmlView/Home-Page.fxml"));
//                loader.setController(homeController);
//                scene.setRoot(loader.load());
//                //huc.init();
//            }
//        });
//    }

    public void switchToProfile(Scene scene) throws IOException {
        this.catcher(new GUIAction() {
            @Override
            public void action() throws IOException {
                HomeController homeController = new HomeController();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("src/main/java/it/ispw/daniele/backpacker/fxmlView/Profile-Page.fxml"));
                loader.setController(homeController);
                scene.setRoot(loader.load());
                //huc.init();
            }
        });
    }
}

