package it.ispw.daniele.backpacker.bean;

import java.util.List;
import java.util.Vector;

public class ResultBean {

    private static ResultBean INSTANCE = null;

    private Vector<String> monuments = new Vector<>();
    private final Vector<List<String>> itinerary = new Vector<>();

    protected ResultBean() {
    }

    public static synchronized ResultBean getInstance() {
        if(INSTANCE==null) {
            INSTANCE = new ResultBean();
        }
        return INSTANCE;
    }

    public Vector<List<String>> getItinerary() {
        return itinerary;
    }

    public void setItinerary(List<String> itinerary) {
//		this.itinerary = itinerary;
        this.itinerary.add(itinerary);
    }

    public List<String> getMonuments() {
        return monuments;
    }

    public void setMonuments(Vector<String> monuments) {
        this.monuments = monuments;
    }

}
