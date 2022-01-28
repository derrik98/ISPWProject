package it.ispw.daniele.backpacker.fxmlView;

import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbarLayout;
import it.ispw.daniele.backpacker.bean.HomeBean;
import it.ispw.daniele.backpacker.exceptions.AddressNotFoundException;
import it.ispw.daniele.backpacker.exceptions.CityNotFoundException;
import it.ispw.daniele.backpacker.exceptions.MonumentNotFoundException;
import it.ispw.daniele.backpacker.utils.Roles;
import it.ispw.daniele.backpacker.utils.SessionUser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeUserController implements Initializable {

    @FXML
    private HBox menuBar = new HBox();
    @FXML
    private AnchorPane APHome = new AnchorPane();
    @FXML
    private Slider sliderRange;
    @FXML
    private Label labelRange;
    @FXML
    private TextField textFieldCountry;
    @FXML
    private TextField textFieldCity;
    @FXML
    private TextField textFieldAddress;
    @FXML
    private Button buttonSearch;
    @FXML
    private RadioButton radioButtonRestaurant;

    public HomeUserController() throws IOException {
    }

    public void onSliderChanged() {
        BigDecimal sliderValue = BigDecimal.valueOf(sliderRange.getValue()).setScale(1, RoundingMode.HALF_UP);
        labelRange.setText(sliderValue.setScale(1, RoundingMode.HALF_UP) + " km");
    }

    public void SearchRoutes() throws IOException{

        //HomeBean homeBean = new HomeBean(textFieldCountry.getText(), textFieldCity.getText(), textFieldAddress.getText(), labelRange.getText(), radioButtonRestaurant.isSelected());
         HomeBean homeBean = HomeBean.getInstance();
         homeBean.setCountry(textFieldCountry.getText());
         homeBean.setCity(textFieldCity.getText());
         homeBean.setAddress(textFieldAddress.getText());

        try {
            homeBean.validate();
            UserGraphicChange.getInstance().switchToResultPage(this.textFieldCountry.getScene());
        } catch (CityNotFoundException cnfe) {
            //e.printStackTrace();
            this.showFeedback(cnfe.getMessage());
        } catch (AddressNotFoundException anfe){
            //anfe.printStackTrace();
            this.showFeedback(anfe.getMessage());
        }catch (MonumentNotFoundException mnfe){
            this.showFeedback(mnfe.getMessage());
        }
    }

    @FXML
    public void handleKeyPressed(KeyEvent event) throws  IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            this.SearchRoutes();
        }
    }

    private void showFeedback(String message){
        JFXSnackbar snackbar = new JFXSnackbar(APHome);
        snackbar.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
        snackbar.fireEvent(new JFXSnackbar.SnackbarEvent(new JFXSnackbarLayout(message), Duration.seconds(2.5), null));
        //snackbar.getStylesheets().add(String.valueOf(HomeController.class.getResource("styleLoginDenied.css")));

//        JFXSnackbar snackbar = new JFXSnackbar(APHome);
//        JFXSnackbarLayout layout = new JFXSnackbarLayout(message);
//
//        String css = "style/styleLoginDenied.css";
//
//        snackbar.getStylesheets().add(css);
//        layout.getStylesheets().add(css);
//
//        snackbar.enqueue(new JFXSnackbar.SnackbarEvent(layout, Duration.seconds(2.5)));
    }

//    public void init(){
//        UserGraphicChange ugc = UserGraphicChange.getInstance();
//
//        ugc.menuBar(this.menuBar, "home");
//        System.out.println(this.menuBar.getChildren() + "EEEEEEEEEEE");
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(SessionUser.getInstance().getSession().getRole().equals(Roles.TOURIST_GUIDE.name().toLowerCase())) {
            TouristGuideGraphicChange i = TouristGuideGraphicChange.getInstance();
            i.menuBar(this.menuBar, "home");
            System.out.println(SessionUser.getInstance().getSession().getRole() + Roles.TOURIST_GUIDE.name());
        }
        else {
        UserGraphicChange ugc = UserGraphicChange.getInstance();
        ugc.menuBar(this.menuBar, "home");
            System.out.println(SessionUser.getInstance().getSession().getRole() + Roles.USER.name().toLowerCase());

        }
        System.out.println(this.menuBar.getChildren() + "EEEEEEEEEEE");
    }
}
