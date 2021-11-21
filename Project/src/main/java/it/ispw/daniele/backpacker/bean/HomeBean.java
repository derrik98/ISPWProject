package it.ispw.daniele.backpacker.bean;

import it.ispw.daniele.backpacker.controller.search.JSONNotFound;
import it.ispw.daniele.backpacker.controller.search.SearchController;

import java.io.IOException;

public class HomeBean {

    private String country;
    private String city;
    private String address;
    private String range;
    private boolean restaurant;

    private static HomeBean INSTANCE = null;

    public HomeBean getInstance() {
        if(INSTANCE==null) {
            INSTANCE = new HomeBean();
        }
        return INSTANCE;
    }

    public HomeBean() {
    }

    public HomeBean(String country, String city, String address, String range, boolean restaurant) {
        this.country = country;
        this.city = city;
        this.address = address;
        this.range = range;
        this.restaurant = restaurant;

        System.out.println("Country " + this.country + "\tCity " + this.city + "\tAddress " + this.address + "\tRadius " + this.range
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

    public boolean isRestaurant() {
        return restaurant;
    }

    public void setRestaurant(boolean restaurant) {
        this.restaurant = restaurant;
    }


    public boolean validate() throws IOException, JSONNotFound, JSONNotFound {
        // controllo sintattico
        if (country == "" || city == "" || address == null) {
            System.out.println(country + city + address);
            return false;
        }

        HomeBean datiCorretti = SearchController.getInstances().getInput(this.getCountry(), this.getCity(), this.getAddress());
        System.out.println("provafatta" + city);//FARE COME LA VALIDATE
        System.out.println(country);

        System.out.println(datiCorretti.country + datiCorretti.city + datiCorretti.address);
        /*if(utenteTrovato==null)
            return false;
        return true;*/
        return datiCorretti != null;

    }



}