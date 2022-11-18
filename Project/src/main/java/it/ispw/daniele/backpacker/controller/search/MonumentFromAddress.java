package it.ispw.daniele.backpacker.controller.search;

import it.ispw.daniele.backpacker.entity.Monument;
import it.ispw.daniele.backpacker.exceptions.MonumentNotFoundException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Vector;

public class MonumentFromAddress extends JSONFactory{
    private ArrayList<String> monuments = new ArrayList<>();
    private static MonumentFromAddress instance = null;

    public static synchronized MonumentFromAddress getInstance() {
        if(instance==null) {
            instance = new MonumentFromAddress();
        }
        return instance;
    }

    public ArrayList<String> getMonuments(String address) throws MonumentNotFoundException {
        this.getJSON(address, "monuments");
        //System.out.println("MONUMENTI" + monuments);
        //resultBean.setMonuments(monumentFromAddress.getMonuments());
        return this.monuments;
    }

    @Override
    public boolean getJSON(String address, String type) throws MonumentNotFoundException {

        try {
            JSONObject json;
            String url = ("https://maps.googleapis.com/maps/api/place/textsearch/json?query=monuments+in+"+convertString(address)+
                    "&radius=8000&type=tourist_attraction&language=it&key=AIzaSyDKAl31fAwxbDImIXXOxSre5uma5WdOgHg");
            json = readJsonFromUrl(url);
            JSONArray a = (JSONArray) json.get("results");
            int i = 0;
            JSONObject o;
            while (i < a.length()){ //finche ci sono record
                o = a.getJSONObject(i);
                if(!monuments.contains((String) o.get("name"))){
                    monuments.add((String) o.get("name"));
                }
                i++;
            }
            if(!json.get("status").equals("OK")) {
                throw new MonumentNotFoundException("No result");
            }
        } catch (IOException e) {
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
