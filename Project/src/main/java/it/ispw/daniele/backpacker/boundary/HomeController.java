package it.ispw.daniele.backpacker.boundary;

import it.ispw.daniele.backpacker.bean.HomeBean;
import it.ispw.daniele.backpacker.controller.search.JSONNotFound;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
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

    public HomeController() throws IOException {
    }

    public void onSliderChanged() {
        BigDecimal sliderValue = BigDecimal.valueOf(sliderRange.getValue()).setScale(1, RoundingMode.HALF_UP);
        labelRange.setText(sliderValue.setScale(1, RoundingMode.HALF_UP)+" km");
    }

    public void SearchRoutes() throws JSONNotFound, IOException {

        //HomeBean homeBean = new HomeBean(textFieldCountry.getText(), textFieldCity.getText(), textFieldAddress.getText(), labelRange.getText(), radioButtonRestaurant.isSelected());

         HomeBean homeBean = new HomeBean().getInstance();
         homeBean.setCountry(textFieldCountry.getText());
         homeBean.setCity(textFieldCity.getText());
         homeBean.setAddress(textFieldAddress.getText());
         homeBean.validate();
    }
}
