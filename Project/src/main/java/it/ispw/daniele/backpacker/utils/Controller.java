package it.ispw.daniele.backpacker.utils;

import it.ispw.daniele.backpacker.bean.ItineraryBean;
import it.ispw.daniele.backpacker.bean.TouristGuideBean;
import it.ispw.daniele.backpacker.bean.UserBean;
import it.ispw.daniele.backpacker.booktour.BookTourController;
import it.ispw.daniele.backpacker.entity.Itinerary;
import it.ispw.daniele.backpacker.entity.TouristGuide;
import it.ispw.daniele.backpacker.entity.User;
import it.ispw.daniele.backpacker.fxmlView.ItineraryDetailsController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Controller {

    protected UserBean convert(User l) {
        UserBean ub = new UserBean();
        ub.setUsername(l.getUsername());
        ub.setName(l.getName());
        ub.setSurname(l.getSurname());
        ub.setProfilePicture(l.getProfilePicture());
        ub.setEmail(l.getEmail());

        return ub;
    }

    protected TouristGuideBean convert(TouristGuide l) {
        TouristGuideBean tgb = new TouristGuideBean();
        tgb.setUsername(l.getUsername());
        tgb.setName(l.getName());
        tgb.setSurname(l.getSurname());
        tgb.setProfilePicture(l.getProfilePicture());
        tgb.setEmail(l.getEmail());
        tgb.setIdentificationCode(l.getVatNum());

        return tgb;
    }

    protected List<ItineraryBean> convert(List<Itinerary> itinerary) {
        List<ItineraryBean> lb = new ArrayList<>();
        for (Itinerary it : itinerary) {
            ItineraryBean ib = this.convert(it);
            lb.add(ib);
        }

        return lb;
    }

    protected ItineraryBean convert(Itinerary itinerary) {
        ItineraryBean ib = new ItineraryBean();
        ib.setItineraryId(itinerary.getId());
        ib.setGuideId(itinerary.getGuideId());
        ib.setLocation(itinerary.getLocation());
        ib.setDate(itinerary.getDate());
        ib.setTime(itinerary.getTime());
        ib.setParticipants(itinerary.getParticipants());
        ib.setPrice(itinerary.getPrice());
        ib.setSteps(itinerary.getSteps());

        return ib;
    }

}
