package it.ispw.daniele.backpacker.booktour;

import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.bean.ItineraryBean;
import it.ispw.daniele.backpacker.dao.ItineraryDao;
import it.ispw.daniele.backpacker.entity.Itinerary;
import it.ispw.daniele.backpacker.utils.Controller;

import java.util.List;

public class BookTourController extends Controller {

    public List<ItineraryBean> getItinerary(String input, String type){//, GeneralUserBean gu) {
        ItineraryDao id = new ItineraryDao();
        List<Itinerary> itinerary = null;
        switch (type) {
            case "city" -> itinerary = id.getItinerary(input);
            case "user" -> itinerary = id.getBookedItineraries(input);
            default -> {
            }
        };
        if(itinerary != null){
            return this.convert(itinerary);
        }
        else{
            return null;
        }
    }

    public void addParticipation(GeneralUserBean user, ItineraryBean itinerary) {
        AddParticipationController apc = new AddParticipationController();
        apc.addParticipation(user, itinerary);
    }

    public void removeParticipation(GeneralUserBean user, ItineraryBean itinerary) {
        AddParticipationController apc = new AddParticipationController();
        apc.removeParticipation(user, itinerary);
    }

    public boolean isParticipating(GeneralUserBean user, ItineraryBean itinerary) {
        AddParticipationController apc = new AddParticipationController();
        return apc.isParticipating(user, itinerary);
    }

}
