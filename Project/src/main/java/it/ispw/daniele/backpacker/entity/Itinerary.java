package it.ispw.daniele.backpacker.entity;

import java.util.Vector;

public class Itinerary {

    private String id;
    private String guideId;
    private String location;
    private String date;
    private String time;
    private String participants;
    private String price;
    private String steps;

    public Itinerary(String id, String location, String guideId, String date, String steps) {
        this.steps = steps;
        this.guideId = guideId;
        this.id = id;
        this.location = location;
        this.date = date;
    }

    public Itinerary(String steps) {
        this.steps = steps;
    }

    public String getSteps() {
        return this.steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
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

    public String getParticipants() {
        return this.participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
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
