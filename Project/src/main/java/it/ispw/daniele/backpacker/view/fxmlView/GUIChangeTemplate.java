package it.ispw.daniele.backpacker.view.fxmlView;

import it.ispw.daniele.backpacker.bean.HomeBean;
import it.ispw.daniele.backpacker.bean.ItineraryBean;
import it.ispw.daniele.backpacker.exceptions.MonumentNotFoundException;
import it.ispw.daniele.backpacker.utils.Roles;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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
                FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/view/fxmlView/LoginView-Page.fxml");
                Parent fxmlLoader = loader.load(fileInputStream);
                LoginViewController lvc = loader.getController();
                scene.setRoot(fxmlLoader);
                lvc.init();
            }
        });
    }

    //Sistemare il metodo
    public void switchToResult(Scene scene, HomeBean homeBean) {
        this.catcher(new GUIAction() {
            @Override
            public void action() throws IOException, MonumentNotFoundException {
                FXMLLoader loader = new FXMLLoader();
                FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/view/fxmlView/Result-Page.fxml");
                Parent fxmlLoader = loader.load(fileInputStream);
                ResultController rc = loader.getController();
                scene.setRoot(fxmlLoader);
                rc.init(homeBean);


            }
        });
    }

    public void switchToItineraryDetails(Scene scene, ItineraryBean itineraryBean){
        this.catcher(new GUIAction() {
            @Override
            public void action() throws IOException {
                FXMLLoader loader = new FXMLLoader();
                FileInputStream fileInputStream;

                fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/view/fxmlView/ItineraryDetails-Page.fxml");
                Parent fxmlLoader = loader.load(fileInputStream);
                ItineraryDetailsController idc = loader.getController();
                idc.init(itineraryBean);

                StackPane stackPaneResult = ResultController.getInstance().stackPaneResult;
                VBox vBox = ResultController.getInstance().vBoxDynamic;
                //StackPane stackPaneResult = new StackPane();

                System.out.println(itineraryBean);
                System.out.println(stackPaneResult.getChildren());
                stackPaneResult.getChildren().add(fxmlLoader);
                stackPaneResult.getChildren().get(0).setDisable(true);// OOOOOOOOO
            }
        });
    }

    public void menuBar (HBox pos, String sel){
        this.catcher(new GUIAction() {
            @Override
            public void action() throws IOException {
                FXMLLoader loader;
                switch (whoAmI){
                    case USER -> {
                        loader = new FXMLLoader();
                        FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/view/fxmlView/MenuBar.fxml");
                        Parent fxmlLoader = loader.load(fileInputStream);
                        MenuBarController mbc = loader.getController();
                        pos.getChildren().add(fxmlLoader);
                        mbc.init(sel);
                    }
                    case TOURIST_GUIDE -> {
                        loader = new FXMLLoader();
                        FileInputStream fileInputStream = new FileInputStream("src/main/java/it/ispw/daniele/backpacker/view/fxmlView/TouristGuideMenuBar.fxml");
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

