package it.ispw.daniele.backpacker.controller.search;

import it.ispw.daniele.backpacker.bean.HomeBean;
import org.json.JSONException;
import org.json.JSONObject;

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

    @Override
    public boolean getJSON(String city, String country) throws JSONNotFound {
        JSONObject json;
        try {
            json = readJsonFromUrl("https://maps.googleapis.com/maps/api/geocode/json?address=" + city
                    + "&components=country:" + country + "&key=AIzaSyDKAl31fAwxbDImIXXOxSre5uma5WdOgHg");
            System.out.println(json);
            System.out.println(json.getString("status"));
            if(!json.getString("status").equals("OK")) {
                throw new JSONNotFound("Questa città non è presente in questo stato");
            }
        } catch (IOException | JSONException j) {
            System.out.println("err");
            return  false;
        }
        return true;

    }

}
