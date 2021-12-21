package it.ispw.daniele.backpacker.entity;

import org.json.JSONArray;

import java.math.BigDecimal;

public class Monument {

    private String name;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String rating;
    private JSONArray types;


    public Monument() {
        this.name = "";
        this.latitude = BigDecimal.valueOf(0.0);
        this.longitude = BigDecimal.valueOf(0.0);
        this.rating = "";
        this.types = null;
    }

    public Monument(String name, BigDecimal latitude, BigDecimal longitude, String rating, JSONArray types) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rating = rating;
        this.types = types;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public JSONArray getTypes() {
        return types;
    }

    public void setTypes(JSONArray types) {
        this.types = types;
    }




    @Override
    public String toString(){
        return String.valueOf(this.name);
//		return this.id + " \"" + this.route + "\" (" + this.id + this.route +")";
    }
}
