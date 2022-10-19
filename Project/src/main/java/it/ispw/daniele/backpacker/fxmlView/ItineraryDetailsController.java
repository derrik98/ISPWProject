package it.ispw.daniele.backpacker.fxmlView;

import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.bean.ItineraryBean;
import it.ispw.daniele.backpacker.booktour.BookTourController;
import it.ispw.daniele.backpacker.utils.SessionUser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ItineraryDetailsController implements Initializable {
    @FXML
    public Button subscribeButton;
    @FXML
    public ImageView closePageImage;
    @FXML
    public AnchorPane APDetails;

    private BookTourController controller = new BookTourController();
    private GeneralUserBean sessionUser = SessionUser.getInstance().getSession();

   public static ItineraryDetailsController instance = null;

    public static ItineraryBean itineraryBean = new ItineraryBean();

    public static ItineraryDetailsController getInstance(){
        if(instance == null){
            instance = new ItineraryDetailsController();
        }
        return instance;
    }

    @FXML
    public void closePage() {
        StackPane sp = (StackPane) APDetails.getParent();
        sp.getChildren().remove(1);
        sp.getChildren().get(0).setDisable(false);
    }

    @FXML
    public void subscribe() {
System.out.println(itineraryBean);
        //boolean isPart = controller.isParticipating(this.sessionUser, this.itineraryBean);
        boolean isPart = controller.isParticipating(this.sessionUser, this.itineraryBean);
        if(isPart){
            controller.removeParticipation(this.sessionUser, this.itineraryBean);
                           //this.part.setText("Add Participation");
        } else {
            controller.addParticipation(this.sessionUser, this.itineraryBean);
                           //this.part.setText("Remove Participation");
        }
    }

    public void convertItinerary(ItineraryBean it) {
        itineraryBean = it;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
