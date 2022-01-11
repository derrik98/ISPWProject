package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.utils.Roles;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class RestaurantGraphicChange extends GUIChangeTemplate{

    private static RestaurantGraphicChange instance = null;

    private RestaurantGraphicChange(){
        whoAmI = Roles.RESTAURANT_OWNER;
    }

    public static RestaurantGraphicChange getInstance(){
        if(instance == null){
            instance = new RestaurantGraphicChange();
        }
        return instance;
    }

    public void switchToHomepage(Scene scene) throws IOException {
        this.catcher(new GUIAction() {
            @Override
            public void action() throws IOException {
                HomeController homeController = new HomeController();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Home-Page.fxml"));
                loader.setController(homeController);
                scene.setRoot(loader.load());
                //huc.init();
            }
        });
    }
}
