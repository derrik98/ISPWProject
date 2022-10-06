package it.ispw.daniele.backpacker.bean;

import java.io.Serializable;

public class ItineraryBean implements Serializable {

    static final long serialVersionUID = 42L;
    private String steps;
    private String id;
    private String guideId;
    private String name;
    private String location;
    private String ticketone;
    private String date;
    private String time;
    private String partecipants;
    private String price;

    public String getPartecipants() {
        return partecipants;
    }

    public void setPartecipants(String partecipants) {
        this.partecipants = partecipants;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGuideId() {
        return guideId;
    }

    public void setGuideId(String guideId) {
        this.guideId = guideId;
    }

    private double distance;

    public void setId(int id) {

        this.id = "" + id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDistance() {
        return this.distance;
    }

    public String getSteps() {
        return this.steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

}
