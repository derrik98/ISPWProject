package it.ispw.daniele.backpacker.view.fxml_view;

import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.bean.ItineraryBean;
import it.ispw.daniele.backpacker.controller.add_itinerary.AddItineraryController;
import it.ispw.daniele.backpacker.utils.SessionUser;
import it.ispw.daniele.backpacker.view.utils_view.InterfaceController;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import static it.ispw.daniele.backpacker.view.command_line_interface.CLI.*;

public class AddItineraryViewController extends InterfaceController {
    @FXML
    public TextField textFieldLanguages;
    @FXML
    public TextField textFieldPrice;
    @FXML
    public TextField textFieldParticipants;
    public static final ListView<Object> listView = new ListView<>();
    @FXML
    private HBox menuBar = new HBox();
    @FXML
    public TextField textFieldTime;
    @FXML
    public DatePicker fieldDate;
    @FXML
    public TextField textFieldCity;

    private GeneralUserBean guideBean;
    private AddItineraryController controller;
    private String steps = "";


    @FXML
    public void share() {

        for(int i = 0; i < listView.getItems().size(); i++){
            TextField t = (TextField) listView.getItems().get(i);
            if(!t.getText().equals("") && t.getText() != null) {
                this.steps = this.steps.concat(t.getText() + "/");
            }

        }

        String location = this.textFieldCity.getText();
        String date = "";
        String time = this.textFieldTime.getText();
        String participants = this.textFieldParticipants.getText();
        String price = this.textFieldPrice.getText();
        boolean result = false;

        if(this.fieldDate.getValue() != null) {
            date = this.fieldDate.getValue().toString();
        }

        ItineraryBean itineraryBean = this.setItineraryBean(this.guideBean.getUsername(), date, location, time, Integer.parseInt(participants), Integer.parseInt(price), this.steps);

        try {
            result = controller.addItinerary(itineraryBean);
            itineraryBean.setItineraryId(controller.getItineraryId(itineraryBean));
            if(result){
                System.out.println(GREEN + "itinerario aggiunto" + RESET);
            }
            else{
            System.out.println(RED + "errore aggiunta itinerario" + RESET);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {

        TouristGuideGraphicChange guideGraphicChange = TouristGuideGraphicChange.getInstance();
        guideGraphicChange.menuBar(this.menuBar, "addItinerary");

        fieldDate.setStyle("-fx-font-size: 20");

        this.controller = new AddItineraryController();

        for(int i = 1; i < 10; i++){
            TextField textField = new TextField();
            textField.setStyle("-fx-font-size: 20");
            textField.setId(String.valueOf(i));
            textField.setPromptText("step -> " + i);


            listView.getItems().add(textField);
        }

        this.guideBean= SessionUser.getInstance().getSession();

    }
}
