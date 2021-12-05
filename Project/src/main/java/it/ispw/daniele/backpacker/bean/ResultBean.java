package it.ispw.daniele.backpacker.bean;

import it.ispw.daniele.backpacker.entity.Itinerary;
import it.ispw.daniele.backpacker.entity.Monument;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ResultBean {

    private static ResultBean INSTANCE = null;

    private Vector<Monument> monuments = new Vector<>();
    private Vector<Itinerary> itinerary = new Vector<>();

    protected ResultBean() {
    }

    public static synchronized ResultBean getInstance() {
        if(INSTANCE==null) {
            INSTANCE = new ResultBean();
        }
        return INSTANCE;
    }

    public Vector<Itinerary> getItinerary() {
        return itinerary;
    }

    public void setItinerary(Vector<Itinerary> itinerary) {
		this.itinerary = itinerary;
        //this.itinerary.add(itinerary);
    }

    public List<Monument> getMonuments() {
        return monuments;
    }

    public void setMonuments(Vector<Monument> monuments) {
        this.monuments = monuments;
    }

}
