package it.ispw.daniele.backpacker.view.fxml_view;

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
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class HomeUserController{

    public static final Text errorText = new Text();
    @FXML
    private HBox menuBar = new HBox();
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

    @FXML
    public void enterKeyPressed(KeyEvent keyEvent) throws  IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
            this.searchRoutes();
        }
    }

    public void onSliderChanged() {
        BigDecimal sliderValue = BigDecimal.valueOf(sliderRange.getValue()).setScale(1, RoundingMode.HALF_UP);
        labelRange.setText(sliderValue.setScale(1, RoundingMode.HALF_UP) + " km");
    }

    public void searchRoutes() throws IOException{

        buttonSearch.setStyle("");

        String styleDefault = "-fx-border-style: none; -fx-border-width: none; -fx-border-color: none";
        this.textFieldCity.setStyle(styleDefault);
        this.textFieldAddress.setStyle(styleDefault);

        HomeBean homeBean = new HomeBean();
        homeBean.setCountry(this.textFieldCountry.getText());
        homeBean.setCity(this.textFieldCity.getText());
        homeBean.setAddress(this.textFieldAddress.getText());
        homeBean.setRestaurant(this.radioButtonRestaurant.getText());
        homeBean.setRange(this.labelRange.getText());

        try {
            if (textFieldCountry.getText().equals("") || textFieldCity.getText().equals("") || textFieldAddress.getText().equals("")) {
                throw new FileNotFoundException("ERROR");
            }

            SessionUser.getInstance().setSearchSession(homeBean);

            SearchController sc = new SearchController();
            sc.checkInput(homeBean);
            UserGraphicChange.getInstance().switchToResult(this.textFieldCountry.getScene(), homeBean);

        } catch (CityNotFoundException cityException) {
            errorText.setStyle("-fx-font-size: 27px;");
            errorText.setText(cityException.getMessage());
            this.textFieldCity.setStyle("-fx-border-style: solid; -fx-border-width: 1; -fx-border-color: red");
        } catch (AddressNotFoundException addressException){
            errorText.setStyle("-fx-font-size: 27px;");
            errorText.setText(addressException.getMessage());
            this.textFieldAddress.setStyle("-fx-border-style: solid; -fx-border-width: 1; -fx-border-color: red");
        }catch (MonumentNotFoundException mnfe){
            //this.showFeedback(mnfe.getMessage());
        }
    }

    public void init() {
        if(SessionUser.getInstance().getSession().getRole().equals(Roles.TOURIST_GUIDE.name().toLowerCase())) {
            TouristGuideGraphicChange tGuideGraphicChange = TouristGuideGraphicChange.getInstance();
            tGuideGraphicChange.menuBar(this.menuBar, "home");
        }
        else {
            UserGraphicChange ugc = UserGraphicChange.getInstance();
            ugc.menuBar(this.menuBar, "home");
        }
    }

}
