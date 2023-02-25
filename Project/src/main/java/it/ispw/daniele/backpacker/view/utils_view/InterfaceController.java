package it.ispw.daniele.backpacker.view.utils_view;

import it.ispw.daniele.backpacker.bean.ItineraryBean;
import it.ispw.daniele.backpacker.bean.TouristGuideBean;
import it.ispw.daniele.backpacker.bean.UserBean;

public abstract class InterfaceController {

    protected UserBean setUserBean(String username, String name, String surname, String email, String password, String newFileName){
        UserBean ub = new UserBean();
        ub.setUsername(username);
        ub.setName(name);
        ub.setSurname(surname);
        ub.setEmail(email);
        ub.setPassword(password);
        ub.setProfilePicture(newFileName);
        return ub;
    }
    protected TouristGuideBean setTouristGuideBean(String username, String name, String surname, String email, String password, String newFileName, String VATNumber){
        TouristGuideBean tgb = new TouristGuideBean();
        tgb.setUsername(username);
        tgb.setName(name);
        tgb.setSurname(surname);
        tgb.setEmail(email);
        tgb.setPassword(password);
        tgb.setProfilePicture(newFileName);
        tgb.setIdentificationCode(VATNumber);
        return tgb;
    }

    protected ItineraryBean setItineraryBean(String guideId, String location, String date, String time, int participants, int price, String steps){
        ItineraryBean ib = new ItineraryBean();
        ib.setGuideId(guideId);
        ib.setDate(date);
        ib.setLocation(location);
        ib.setTime(time);
        ib.setParticipants(participants);
        ib.setPrice(price);
        ib.setSteps(steps);
        return ib;
    }

}
