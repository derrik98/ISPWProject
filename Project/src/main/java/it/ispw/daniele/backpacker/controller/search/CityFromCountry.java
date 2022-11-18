package it.ispw.daniele.backpacker.controller.search;

import it.ispw.daniele.backpacker.exceptions.CityNotFoundException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.*;
import java.io.IOException;

public class CityFromCountry extends JSONFactory{

    private static CityFromCountry instance = null;

    public static synchronized CityFromCountry getInstance() {
        if(instance==null) {
            instance = new CityFromCountry();
        }
        return instance;
    }

    @Override
    public boolean getJSON(String city, String country) throws CityNotFoundException, IOException {
        JSONObject json;

        json = readJsonFromUrl("https://maps.googleapis.com/maps/api/place/autocomplete/json?input="
                + city + "&types=(cities)&language=it&key=AIzaSyDKAl31fAwxbDImIXXOxSre5uma5WdOgHg");

        JSONArray ja = (JSONArray) json.get("predictions");
        JSONObject o = (JSONObject) ja.getJSONObject(0).get("structured_formatting");
        String s = (String) o.get("secondary_text");

        String upperCase;
        upperCase = String.valueOf(upperCase(country));

        if (s.contains(country) || s.contains(upperCase)) {
           // System.out.println(upperCase);
            return true;
        }
        else{
            throw new CityNotFoundException("City not found in this country");
        }

    }

}
