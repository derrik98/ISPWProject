package it.ispw.daniele.backpacker.bean;

import java.util.logging.Logger;

public class HomeBean {

    private String country;
    private String city;
    private String address;
    private String range;
    private String restaurant;

    private static HomeBean INSTANCE = null;

    public static synchronized HomeBean getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HomeBean();
        }
        return INSTANCE;
    }

    public HomeBean() {
    }

    protected HomeBean(String country, String city, String address, String range, String restaurant) {
        this.country = country;
        this.city = city;
        this.address = address;
        this.range = range;
        this.restaurant = restaurant;

        Logger.getLogger("Country " + this.country + "\tCity " + this.city + "\tAddress " + this.address + "\tRadius " + this.range
                + "\tRestaurant " + this.restaurant);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String isRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

}
