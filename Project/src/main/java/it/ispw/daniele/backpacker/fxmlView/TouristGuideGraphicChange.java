package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.utils.Roles;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class TouristGuideGraphicChange extends GUIChangeTemplate{

    private static TouristGuideGraphicChange instance = null;

    private TouristGuideGraphicChange(){
        whoAmI = Roles.TOURIST_GUIDE;
    }

    public static TouristGuideGraphicChange getInstance(){
        if(instance == null){
            instance = new TouristGuideGraphicChange();
        }
        return instance;
    }

    public void switchToHomePage(Scene scene) throws IOException {
        this.catcher(new GUIAction() {
            @Override
            public void action() throws IOException {
                HomeUserController homeUserController = new HomeUserController();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Home-Page.fxml"));
                loader.setController(homeUserController);
                scene.setRoot(loader.load());
                //huc.init();
            }
        });
    }
}
