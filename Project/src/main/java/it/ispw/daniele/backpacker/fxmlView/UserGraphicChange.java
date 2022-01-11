package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.utils.Roles;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.FileInputStream;
import java.io.IOException;

public class UserGraphicChange extends GUIChangeTemplate{

    private static UserGraphicChange instance = null;

    private UserGraphicChange(){
        whoAmI = Roles.USER;
    }

    public static UserGraphicChange getInstance(){
        if(instance == null){
            instance = new UserGraphicChange();
        }
        return instance;
    }

    public void switchToHomepage(Scene scene) throws IOException {
        this.catcher(new GUIAction() {
            @Override
            public void action() throws IOException {
                HomeController homeController = new HomeController();
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("Home-Page.fxml"));
//                loader.setController(homeController);
//                scene.setRoot(loader.load());

                FXMLLoader loader = new FXMLLoader();
                FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/fxmlView/Home-Page.fxml");
                Parent fxmlLoader = loader.load(fileInputStream);
                //Scene scene = this.LabelHome.getScene();
                scene.setRoot(fxmlLoader);
                loader.setController(homeController);
                stackScene.push(fxmlLoader);
                //huc.init();
            }
        });
    }
}
