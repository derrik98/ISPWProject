package it.ispw.daniele.backpacker.controller.search;

import it.ispw.daniele.backpacker.entity.Monument;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Vector;

public class MonumentFromAddress extends JSONFactory{

    private Vector<Monument> monuments = new Vector<>();
    private static MonumentFromAddress instance = null;

    public static synchronized MonumentFromAddress getInstance() {
        if(instance==null) {
            instance = new MonumentFromAddress();
        }
        return instance;
    }

    protected MonumentFromAddress(){
    }

    public void setMonuments(Vector<Monument> monuments) {
        this.monuments = monuments;
    }

    public Vector<Monument> getMonuments() {
        return this.monuments;
    }


    @Override
    public boolean getJSON(String address, String type) throws JSONNotFound {
        JSONObject json;
        try {
            //json = readJsonFromUrl("https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + type + "+in+" + convertString(address) + "&radius=8000&type=tourist_attraction&language=it&key=AIzaSyDKAl31fAwxbDImIXXOxSre5uma5WdOgHg");

            json = readJsonFromUrl("https://maps.googleapis.com/maps/api/place/textsearch/json?query=monuments+in+"
                    + convertString(address) +
                    "&radius=8000&type=tourist_attraction&language=it&key=AIzaSyDKAl31fAwxbDImIXXOxSre5uma5WdOgHg");
            JSONArray a = (JSONArray) json.get("results");
            System.out.println("QUESTO è A " + a);
            System.out.println("lunghezza record " + a.length());
            int i = 0;
            JSONObject o = null;
            while (i < a.length()){ //OKKKKKKK
                o = a.getJSONObject(i);
                System.out.println(o);
                this.getJSONName(o);
                this.getLat(o);
                this.getLon(o);
                this.getRating(o);
                this.getTypes(o);
                Monument monument = new Monument(this.getJSONName(o), this.getLat(o), this.getLon(o), this.getRating(o), this.getTypes(o));
                System.out.println(o.get("name"));
                //getMonuments().add(o.get("name").toString());
                getMonuments().add(monument);
                i++;
            }
            if(!json.getString("status").equals("OK")) {
                throw new JSONNotFound("Non sono presenti monumenti in questa zona");
            }
        }
        catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private JSONArray getTypes(JSONObject jsonObject) {
        return (JSONArray) jsonObject.get("types");
    }

    private String getRating(JSONObject jsonObject) {
            return jsonObject.get("rating").toString();
    }

    private BigDecimal getLat(JSONObject jsonObject) {
        JSONObject geometry = (JSONObject) jsonObject.get("geometry");
        JSONObject location = (JSONObject) geometry.get("location");
        return (BigDecimal) location.get("lat");
    }

    private BigDecimal getLon(JSONObject jsonObject) {
        JSONObject geometry = (JSONObject) jsonObject.get("geometry");
        JSONObject location = (JSONObject) geometry.get("location");
        return (BigDecimal) location.get("lng");
    }

    public String getJSONName(JSONObject jsonObject){
        return (String) jsonObject.get("name");
    }
}
