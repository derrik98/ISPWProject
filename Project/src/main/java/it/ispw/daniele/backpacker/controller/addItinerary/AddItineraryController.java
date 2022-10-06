package it.ispw.daniele.backpacker.controller.addItinerary;

import it.ispw.daniele.backpacker.bean.ItineraryBean;
import it.ispw.daniele.backpacker.dao.ItineraryDao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddItineraryController {
    public boolean addItinerary(ItineraryBean itineraryBean) {
        ItineraryDao itineraryDao = new ItineraryDao();
        Date date = null;
        Date currentDate = new Date();
        List<Double> coordinates = null;

        if(itineraryBean.getGuideId().equals("") || itineraryBean.getLocation().equals("")) {
            return false;
        }

        /*try {
            coordinates = GoogleMapBoundary.locateAddress(meb.getLocation());
        } catch (IOException e) {
            logger.log(Level.WARNING, e.toString());
            return false;
        } catch (org.json.simple.parser.ParseException pe) {
            logger.log(Level.WARNING, pe.toString());
            return false;
        } catch (LocationNotFoundException e) {
            //Maybe log to the view
            logger.log(Level.WARNING, e.getMessage());
            return false;*/
        //}

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

        System.out.println(itineraryBean.getName());
        System.out.println(itineraryBean.getLocation());
        System.out.println(itineraryBean.getGuideId());
        System.out.println(date);
        System.out.println(itineraryBean.getSteps());
        return itineraryDao.addItinerary(itineraryBean.getGuideId(), itineraryBean.getLocation(), itineraryBean.getGuideId(), date, itineraryBean.getSteps());
    }
}
