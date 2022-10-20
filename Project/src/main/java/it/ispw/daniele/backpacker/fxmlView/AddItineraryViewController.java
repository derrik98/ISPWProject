package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.bean.ItineraryBean;
import it.ispw.daniele.backpacker.controller.addItinerary.AddItineraryController;
import it.ispw.daniele.backpacker.utils.SessionUser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AddItineraryViewController implements Initializable {
    @FXML
    public TextField textFieldLanguages;
    @FXML
    public TextField textFieldPrice;
    @FXML
    public TextField textFieldParticipants;
    public ListView<Object> listView = new ListView<>();
    public TextField textFieldId;
    @FXML
    private HBox menuBar = new HBox();
    @FXML
    public TextField textFieldTime;
    @FXML
    public DatePicker fieldDate;
    @FXML
    public TextField textFieldCity;

    //private TouristGuideBean guideBean;

    private GeneralUserBean guideBean;
    private AddItineraryController controller;
    private String steps = "";

    public AddItineraryViewController() {
    }

    @FXML
    public void share() {

        for(int i = 0; i < this.listView.getItems().size(); i++){
            TextField t = (TextField) listView.getItems().get(i);
            //System.out.println(t.getText());
            if(!t.getText().equals("") && t.getText() != null) {
                this.steps = this.steps.concat(t.getText() + "/");
            }

        }
        System.out.println(this.steps);

        System.out.println("condiviso");
        String id = this.textFieldId.getText();
        String location = this.textFieldCity.getText();
        String date = "";
        String time = this.textFieldTime.getText();
        String participants = this.textFieldParticipants.getText();
        String price = this.textFieldPrice.getText();
        boolean result = false;

        if(this.fieldDate.getValue() != null) {
            date = this.fieldDate.getValue().toString();
        }

        ItineraryBean itineraryBean = new ItineraryBean();
        itineraryBean.setItineraryId(id);
        itineraryBean.setGuideId(this.guideBean.getUsername());
        itineraryBean.setDate(date);
        itineraryBean.setLocation(location);
        itineraryBean.setTime(time);
        itineraryBean.setParticipants(participants);
        itineraryBean.setPrice(price);
        itineraryBean.setSteps(this.steps);
        System.out.println(this.guideBean.getUsername());
        System.out.println("Dati" + itineraryBean.getGuideId() + "\n" + itineraryBean.getDate() + "\n" + itineraryBean.getLocation() + "\n" + itineraryBean.getTime() + "\n" + itineraryBean.getParticipants() + "\n" + itineraryBean.getPrice());

        try {
            result = controller.addItinerary(itineraryBean);
            if(result){
                //this.headerLabel.setText("Music Event Added");
                System.out.println("itinerario aggiunto");
            }
            else{
            System.out.println("errore aggiunta itinerario");
            //this.headerLabel.setText("Failed to add music event");
            }
        } catch (Exception e) {
            e.printStackTrace();
            //this.headerLabel.setText(de.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TouristGuideGraphicChange guideGraphicChange = TouristGuideGraphicChange.getInstance();
        guideGraphicChange.menuBar(this.menuBar, "addItinerary");

        fieldDate.setStyle("-fx-font-size: 20");

        this.controller = new AddItineraryController();

        for(int i = 1; i < 10; i++){
            TextField textField = new TextField();
            textField.setStyle("-fx-font-size: 20");
            textField.setId(String.valueOf(i));
            textField.setPromptText("step -> " + i);

            /*textField.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println(textField.getId());
                    System.out.println(listView.getItems().size());
                    if(!textField.getText().equals("") && textField.getText().substring(0,2).equals("n.")){
                        textField.setText("");
                    }
                }
            });*/

            listView.getItems().add(textField);
        }
        this.guideBean= SessionUser.getInstance().getSession();
    }
}
