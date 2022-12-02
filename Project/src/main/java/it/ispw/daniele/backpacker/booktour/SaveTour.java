package it.ispw.daniele.backpacker.booktour;

import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.bean.ItineraryBean;
import it.ispw.daniele.backpacker.dao.ItineraryDao;
import it.ispw.daniele.backpacker.entity.Itinerary;
import it.ispw.daniele.backpacker.utils.Controller;

import java.text.ParseException;
import java.util.List;

public class SaveTour extends Controller {

    public void saveTour(GeneralUserBean user, ItineraryBean itinerary) throws ParseException {
        ItineraryDao id = new ItineraryDao();
        id.saveTour(user.getUsername(), itinerary.getSteps());
    }

    public void removeTour(GeneralUserBean user, ItineraryBean itinerary){
        ItineraryDao id = new ItineraryDao();
        id.removeTour(user.getUsername(), itinerary.getSteps());
    }

    public List<ItineraryBean> getItinerary(String input){//, GeneralUserBean gu) {
        ItineraryDao id = new ItineraryDao();
        List<Itinerary> itinerary = null;

        itinerary = id.getSavedItinerary(input);

        if(itinerary != null){
            return this.convert(itinerary);
        }
        else{
            return null;
        }
    }

}
