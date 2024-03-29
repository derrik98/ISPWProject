package it.ispw.daniele.backpacker.booktour;

import it.ispw.daniele.backpacker.bean.GeneralUserBean;
import it.ispw.daniele.backpacker.bean.ItineraryBean;
import it.ispw.daniele.backpacker.dao.ItineraryDao;

public class AddParticipationController {
        public void addParticipation(GeneralUserBean user, ItineraryBean itinerary) {
            ItineraryDao id = new ItineraryDao();
            id.addParticipation(user.getUsername(), itinerary.getItineraryId());
        }

        public void removeParticipation(GeneralUserBean user, ItineraryBean itinerary) {
            ItineraryDao id = new ItineraryDao();
            id.removeParticipation(user.getUsername(), itinerary.getItineraryId());
        }

        public boolean isParticipating(GeneralUserBean user, ItineraryBean itinerary) {
            ItineraryDao id = new ItineraryDao();
            return id.isParticipating(user.getUsername(), itinerary.getItineraryId());
        }
    }

