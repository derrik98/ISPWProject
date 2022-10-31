package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.bean.HomeBean;
import it.ispw.daniele.backpacker.controller.search.SearchController;
import it.ispw.daniele.backpacker.exceptions.AddressNotFoundException;
import it.ispw.daniele.backpacker.exceptions.CityNotFoundException;
import it.ispw.daniele.backpacker.exceptions.MonumentNotFoundException;
import it.ispw.daniele.backpacker.utils.Roles;
import it.ispw.daniele.backpacker.utils.SessionUser;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class HomeUserController{

    public Text errorText;
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

        buttonSearch.setStyle("");

        this.textFieldCity.setStyle("-fx-border-style: none; -fx-border-width: none; -fx-border-color: none");
        this.textFieldAddress.setStyle("-fx-border-style: none; -fx-border-width: none; -fx-border-color: none");

        HomeBean homeBean = new HomeBean();
        homeBean.setCountry(this.textFieldCountry.getText());
        homeBean.setCity(this.textFieldCity.getText());
        homeBean.setAddress(this.textFieldAddress.getText());
        homeBean.setRestaurant(this.radioButtonRestaurant.getText());
        homeBean.setRange(this.labelRange.getText());

        try {
            //////////////////

            if (textFieldCountry.getText().equals("") || textFieldCity.getText().equals("") || textFieldAddress.getText().equals("")) {
                throw new FileNotFoundException("ERROR");
                //return false;
            }
            SearchController sc = new SearchController();
            sc.checkInput(homeBean);
            //HomeBean datiCorretti = sc.checkInput(homeBean);

            //HomeBean datiCorretti = null;
            //
            /*try {
                datiCorretti = SearchController.getInstances().getInput();
            } catch (CityNotFoundException e) {
                throw new CityNotFoundException(e.getMessage());
                // e.printStackTrace();
            }*/
            //
            //System.out.println("provafatta" + city);//FARE COME LA VALIDATE
            //System.out.println(country);

            //System.out.println(datiCorretti.country + datiCorretti.city + datiCorretti.address);
        /*if(utenteTrovato==null)
            return false;
        return true;*/
            //////return datiCorretti != null;

            //////////////////
            //homeBean.validate();
            UserGraphicChange.getInstance().switchToResult(this.textFieldCountry.getScene(), this.textFieldCountry.getText(), this.textFieldCity.getText(), this.textFieldAddress.getText(), this.radioButtonRestaurant.getText(), String.valueOf(this.sliderRange.getValue()));
        } catch (CityNotFoundException cityException) {
            this.errorText.setStyle("-fx-font-size: 27px;");
            this.errorText.setText(cityException.getMessage());
            this.textFieldCity.setStyle("-fx-border-style: solid; -fx-border-width: 1; -fx-border-color: red");
        } catch (AddressNotFoundException addressException){
            this.errorText.setStyle("-fx-font-size: 27px;");
            this.errorText.setText(addressException.getMessage());
            this.textFieldAddress.setStyle("-fx-border-style: solid; -fx-border-width: 1; -fx-border-color: red");
        }catch (MonumentNotFoundException mnfe){
            //this.showFeedback(mnfe.getMessage());
        }
    }

    @FXML
    public void enterKeyPressed(KeyEvent keyEvent) throws  IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
            this.SearchRoutes();
        }
    }

    public void init() {
        if(SessionUser.getInstance().getSession().getRole().equals(Roles.TOURIST_GUIDE.name().toLowerCase())) {
            TouristGuideGraphicChange tGuideGraphicChange = TouristGuideGraphicChange.getInstance();
            tGuideGraphicChange.menuBar(this.menuBar, "home");
            System.out.println(SessionUser.getInstance().getSession().getRole() + Roles.TOURIST_GUIDE.name());
        }
        else {
            UserGraphicChange ugc = UserGraphicChange.getInstance();
            ugc.menuBar(this.menuBar, "home");
            System.out.println(SessionUser.getInstance().getSession().getRole() + Roles.USER.name().toLowerCase());
            //ugc.backButton(this.APHome, "home");
        }
    }

}
