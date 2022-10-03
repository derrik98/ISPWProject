package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.bean.ItineraryBean;
import it.ispw.daniele.backpacker.bean.TouristGuideBean;
import it.ispw.daniele.backpacker.controller.addItinerary.AddItineraryController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AddItineraryViewController implements Initializable {
    @FXML
    public TextField textFieldLanguages;
    @FXML
    public TextField textFieldPrice;
    @FXML
    public TextField textFieldPartecipants;
    public ListView<Object> listView = new ListView<>();
    @FXML
    private HBox menuBar = new HBox();
    @FXML
    public TextField textFieldTime;
    @FXML
    public DatePicker fieldDate;
    @FXML
    public TextField textFieldCity;

    private TouristGuideBean guideBean;
    private AddItineraryController controller;

    @FXML
    public void share(MouseEvent mouseEvent) {
        System.out.println("condiviso");
        String location = this.textFieldCity.getText();
        String date = "";
        String time = this.textFieldTime.getText();
        String partecipants = this.textFieldPartecipants.getText();
        String price = this.textFieldPrice.getText();
        boolean result = false;

        if(this.fieldDate.getValue() != null) {
            date = this.fieldDate.getValue().toString();
        }

        ItineraryBean itineraryBean = new ItineraryBean();
        itineraryBean.setGuideId(this.guideBean.getIdentificationCode());
        itineraryBean.setDate(date);
        itineraryBean.setLocation(location);
        itineraryBean.setTime(time);
        itineraryBean.setPartecipants(partecipants);
        itineraryBean.setPrice(price);

        try{
            result = controller.addItinerary(itineraryBean);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TouristGuideGraphicChange tggc = TouristGuideGraphicChange.getInstance();
        tggc.menuBar(this.menuBar, "home");

        for(int i = 1; i < 10; i++){
            TextField textField = new TextField("n." + i);
            textField.setStyle("-fx-font-size: 20");
            textField.setId(String.valueOf(i));

            textField.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println(textField.getId());
                    System.out.println(listView.getItems().size());
                    if(!textField.getText().equals("") && textField.getText().substring(0,2).equals("n.")){
                        textField.setText("");
                    }
                }
            });

            listView.getItems().add(textField);
        }

    }
}
