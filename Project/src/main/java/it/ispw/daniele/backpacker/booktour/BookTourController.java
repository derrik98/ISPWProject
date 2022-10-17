package it.ispw.daniele.backpacker.booktour;

import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.bean.ItineraryBean;
import it.ispw.daniele.backpacker.dao.ItineraryDao;
import it.ispw.daniele.backpacker.entity.Itinerary;

public class BookTourController {

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

    public ItineraryBean getItinerary(String city){//, GeneralUserBean gu) {
        ItineraryDao id = new ItineraryDao();
        Itinerary itinerary = id.getItinerary(city);//, gu.getRole());
        System.out.println(itinerary);
        if(itinerary != null){
            ItineraryBean ib = this.convert(itinerary);
            return ib;
        }
        else{
            return null;
        }

        //ib.setLatitude(me.getCoordinates().get(0));
        //ib.setLongitude(me.getCoordinates().get(1));

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
