package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.controller.login.LoginController;
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

public class GUIChangeTemplate{

    protected final Logger logger = Logger.getLogger("GraphicChange");

    protected Roles whoAmI;

    public GUIChangeTemplate(){
    }

    public void catcher(GUIAction guiAction){
        try {
            guiAction.action();
        }catch (IOException ioException){
            logger.log(Level.WARNING, ioException.toString(), ioException.getCause());
        }
    }

    @FXML
    public void switchToLogin(Scene scene){
        this.catcher(new GUIAction() {
            @Override
            public void action() throws IOException {
                LoginController loginController = new LoginController();
                FXMLLoader loader = new FXMLLoader();
                FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/LoginViewPage.fxml");
                Parent fxmlLoader = loader.load(fileInputStream);
                //loader.setController(loginController);
                scene.setRoot(fxmlLoader);
                //stackScene.push(fxmlLoader);
            }
        });
    }

//    public void switchToHomepage(Scene scene) throws IOException {
//        this.catcher(new GUIAction() {
//            @Override
//            public void action() throws IOException {
//                //HomeUserController homeController = new HomeUserController();
//              //  FXMLLoader loader = new FXMLLoader(getClass().getResource("src/main/java/it/ispw/daniele/backpacker/fxmlView/Home-Page.fxml"));
//               // loader.setController(homeController);
//
//                //huc.init();
//
//                FXMLLoader loader = new FXMLLoader();
//                FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/Home-Page.fxml");
//                //loader.setController(loginViewController);
//                Parent fxmlLoader = loader.load(fileInputStream);
//                scene.setRoot(fxmlLoader);
//            }
//        });
//    }


//    public void switchToProfile(Scene scene) throws IOException {
//        this.catcher(new GUIAction() {
//            @Override
//            public void action() throws IOException {
//                HomeController homeController = new HomeController();
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("src/main/java/it/ispw/daniele/backpacker/fxmlView/Profile-Page.fxml"));
//                loader.setController(homeController);
//                scene.setRoot(loader.load());
//                //huc.init();
//            }
//        });
//    }

    public void menuBar (HBox pos, String sel){
        this.catcher(new GUIAction() {
            @Override
            public void action() throws IOException {
                FXMLLoader loader = new FXMLLoader();
                switch (whoAmI){
                    case USER -> {
//                        loader = new FXMLLoader(getClass().getResource("/src/main/java/it/ispw/daniele/backpacker/fxmlView/MenuBar.fxml"));
//                        MenuBarController mbc = new MenuBarController();
//                        Parent parent = loader.load();
//                        //loader.setController(mbc);
//                        pos.getChildren().add(parent);
//                        mbc.init(sel);
//                        System.out.println(pos.getChildren());
//                        System.out.println(pos.getChildren().get(0));
//                        AnchorPane a = (AnchorPane) pos.getChildren().get(0);
//                        System.out.println(a.getChildren().get(0));
//                        System.out.println(pos);
//                        break;
                        FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/MenuBar.fxml");
                        Parent fxmlLoader = loader.load(fileInputStream);
                        MenuBarController menuBarController = new MenuBarController();
//                        //FXMLLoader loader = new FXMLLoader();

//                        //scene.setRoot(fxmlLoader);
                        //loader.setController(menuBarController);
                           pos.getChildren().add(fxmlLoader);

//                        //stackScene.push(fxmlLoader);
                        menuBarController.init(sel);
                        System.out.println(pos.getChildren());
                        System.out.println(pos.getChildren().get(0));
                        Parent a = (Parent) pos.getChildren().get(0);
                        System.out.println(a.getChildrenUnmodifiable());
                        System.out.println(pos);
                       break;
                    }default -> {break;}
                }
            }
        });
    }
}

