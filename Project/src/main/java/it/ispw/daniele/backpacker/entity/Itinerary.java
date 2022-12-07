package it.ispw.daniele.backpacker.entity;

import java.util.Vector;

public class Itinerary {
    private int id;
    private String guideId;
    private String location;
    private String date;
    private String time;
    private int participants;
    private int price;
    private String steps;

    public Itinerary(int id, String guideId, String location, String date, String time, int participants, int price, String steps) {

        this.id = id;
        this.guideId = guideId;
        this.location = location;
        this.date = date;
        this.time = time;
        this.participants = participants;
        this.price = price;
        this.steps = steps;
    }

    public Itinerary(int id ,String steps) {
        this.steps = steps;
        this.guideId = "";
        this.id = id;
        this.location = "";
        this.date = "";
    }

    public String getSteps() {
        return this.steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGuideId() {
        return this.guideId;
    }

    public void setGuideId(String guideId) {
        this.guideId = guideId;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getParticipants() {
        return this.participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    private Vector<Monument> itinerary;




//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }

    public Vector<Monument> getItinerary() {
        return itinerary;
    }

    public void setItinerary(Vector<Monument> route) {
        this.itinerary = route;
    }

    @Override
    public String toString(){
        return String.valueOf(this.itinerary);
//		return this.id + " \"" + this.route + "\" (" + this.id + this.route +")";
    }

}
