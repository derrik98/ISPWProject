package it.ispw.daniele.backpacker.controller.search;

import it.ispw.daniele.backpacker.bean.HomeBean;
import it.ispw.daniele.backpacker.exceptions.CityNotFoundException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;

import java.io.IOException;

public class CityFromCountry extends JSONFactory{

    private static CityFromCountry instance = null;

    public static synchronized CityFromCountry getInstance() {
        if(instance==null) {
            instance = new CityFromCountry();
        }
        return instance;
    }

    protected CityFromCountry(){
    }

   //@Override
    public boolean getJSON(String city, String country) throws CityNotFoundException, IOException {
        JSONObject json;

        json = readJsonFromUrl("https://maps.googleapis.com/maps/api/geocode/json?address=" + city
                + "&components=country:" + country + "&key=AIzaSyDKAl31fAwxbDImIXXOxSre5uma5WdOgHg");
        json = readJsonFromUrl("https://maps.googleapis.com/maps/api/place/autocomplete/json?input=" + city + "&types=(cities)&language=it&key=AIzaSyDKAl31fAwxbDImIXXOxSre5uma5WdOgHg"
        );

            System.out.println(json);

        JSONArray ja = (JSONArray) json.get("predictions");
        JSONObject o = (JSONObject) ja.getJSONObject(0).get("structured_formatting");
        String s = (String) o.get("secondary_text");
            System.out.println("WW" + s);

            if(s.contains(country)){
                System.out.println("citta presente");  // caso con lettere maiuscole e minuscole
            }
            if(!json.getString("status").equals("OK")) {
                throw new CityNotFoundException("City not found in this country");
            }

        return true;

    }

}
