package it.ispw.daniele.backpacker;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class HomeController extends GUIController{

    @FXML
    Slider sliderRange;
    @FXML
    Label labelRange;
    @FXML
    TextField textFieldCountry;
    @FXML
    TextField textFieldCity;
    @FXML
    TextField textFieldAddress;
    @FXML
    Button buttonSearch;
    @FXML
    RadioButton radioButtonRestaurant;

    public void onSliderChanged() {
        BigDecimal sliderValue = BigDecimal.valueOf(sliderRange.getValue()).setScale(1, RoundingMode.HALF_UP);
        labelRange.setText(sliderValue.setScale(1, RoundingMode.HALF_UP)+" km");
    }

    public void SearchRoutes() {
        System.out.println("Country " + textFieldCountry.getText() + "\tCity " + textFieldCity.getText() + "\tAddress " + textFieldAddress.getText() + "\tRadius " + labelRange.getText()
                + "\tRestaurant " + radioButtonRestaurant.isSelected());
    }
}
