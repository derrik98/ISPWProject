package it.ispw.daniele.backpacker.controller.search;

import it.ispw.daniele.backpacker.exceptions.AddressNotFoundException;
import it.ispw.daniele.backpacker.exceptions.CityNotFoundException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class AddressFromCity extends JSONFactory{

    private static AddressFromCity instance = null;

    public static synchronized AddressFromCity getInstance() {
        if(instance==null) {
            instance = new AddressFromCity();
        }
        return instance;
    }

    protected AddressFromCity(){
    }

    @Override
    public boolean getJSON(String address, String city) throws AddressNotFoundException, IOException {
        JSONObject json;

        json = readJsonFromUrl("https://maps.googleapis.com/maps/api/place/autocomplete/json?input=" + convertString(address) + "&types=geocode&key=AIzaSyDKAl31fAwxbDImIXXOxSre5uma5WdOgHg&language=it");


        if(!json.getString("status").equals("OK")) {
            throw new AddressNotFoundException("Questa via non è presente in questo città");
        }

        JSONArray a = (JSONArray) json.get("predictions");
        JSONObject o = a.getJSONObject(0);
        String s = (String) o.get("description");

        String upperCase;
        upperCase = String.valueOf(upperCase(city));

        if (s.contains(city) || s.contains(upperCase)) {
            //System.out.println(upperCase);
            return true;
        }
        else{
            throw new AddressNotFoundException("Address not present in this city");
        }

    }

}
