package it.ispw.daniele.backpacker.controller.search;

import it.ispw.daniele.backpacker.bean.HomeBean;
import it.ispw.daniele.backpacker.exceptions.CityNotFoundException;
import org.json.JSONArray;
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

    @Override
    public boolean getJSON(HomeBean homeBean) throws CityNotFoundException, IOException {
        JSONObject json;

        json = readJsonFromUrl("https://maps.googleapis.com/maps/api/place/autocomplete/json?input="
                + homeBean.getCity() + "&types=(cities)&language=it&key=" + System.getProperty("google_api"));

        JSONArray ja = (JSONArray) json.get("predictions");
        JSONObject o = (JSONObject) ja.getJSONObject(0).get("structured_formatting");
        String s = (String) o.get("secondary_text");

        String upperCase;
        upperCase = String.valueOf(upperCase(homeBean.getCountry()));

        if (s.contains(homeBean.getCountry()) || s.contains(upperCase)) {
            return true;
        }
        else{
            throw new CityNotFoundException("City not found in this country");
        }

    }

}
