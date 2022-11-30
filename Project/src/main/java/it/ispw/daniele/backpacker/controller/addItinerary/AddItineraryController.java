package it.ispw.daniele.backpacker.controller.addItinerary;

import it.ispw.daniele.backpacker.bean.ItineraryBean;
import it.ispw.daniele.backpacker.dao.ItineraryDao;
import it.ispw.daniele.backpacker.exceptions.IdJustUsed;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddItineraryController {

    public int getItineraryId(ItineraryBean itineraryBean) throws SQLException, ClassNotFoundException {
        ItineraryDao itineraryDao = new ItineraryDao();
        ///MODIFICARE CODICE SQL
        return itineraryDao.getItineraryId(itineraryBean.getGuideId(), itineraryBean.getLocation(), itineraryBean.getDate(), itineraryBean.getTime(), itineraryBean.getParticipants(), itineraryBean.getPrice(), itineraryBean.getSteps());
    }
    public boolean addItinerary(ItineraryBean itineraryBean) throws IdJustUsed {
        ItineraryDao itineraryDao = new ItineraryDao();
        Date date = null;
        Date currentDate = new Date();

        if(itineraryBean.getGuideId().equals("") || itineraryBean.getLocation().equals("")) {
            return false;
        }

        if (itineraryBean.getDate() != null) {
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(itineraryBean.getDate());
            } catch (ParseException e) {
                return false;
            }
        } else {
            return false;
        }

        if(date.before(currentDate)){
            DateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy");
            //throw new DateException(outputFormatter.format(date) + " is before current date");
        }

       /*if(!itineraryDao.getItineraryId(itineraryBean.getItineraryId())){
           throw new IdJustUsed("id gia esistente");
            //System.out.println("id gia esistente");
            //return false;
       }*/



        //return itineraryDao.addItinerary(itineraryBean.getItineraryId(), itineraryBean.getGuideId(), itineraryBean.getLocation(), date, itineraryBean.getTime(), itineraryBean.getParticipants(), itineraryBean.getPrice(), itineraryBean.getSteps());
        return itineraryDao.addItinerary(itineraryBean.getGuideId(), itineraryBean.getLocation(), date, itineraryBean.getTime(), itineraryBean.getParticipants(), itineraryBean.getPrice(), itineraryBean.getSteps());

    }
}
