package it.ispw.daniele.backpacker.controller.search;

import com.mysql.fabric.Response;
import it.ispw.daniele.backpacker.bean.HomeBean;
import it.ispw.daniele.backpacker.exceptions.AddressNotFoundException;
import it.ispw.daniele.backpacker.exceptions.CityNotFoundException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.io.IOException;

public class AddressFromCity extends JSONFactory{

    private static AddressFromCity instance = null;

    public static synchronized AddressFromCity getInstance() {
        if(instance==null) {
            instance = new AddressFromCity();
        }
        return instance;
    }

    @Override
    public boolean getJSON(HomeBean homeBean) throws AddressNotFoundException, IOException {

        JSONObject json;

        json = readJsonFromUrl("https://maps.googleapis.com/maps/api/place/autocomplete/json?input=" + convertString(homeBean.getAddress()) + "&types=geocode&key=" + System.getProperty("google_api") + "&language=it");


        if(!json.getString("status").equals("OK")) {
            throw new AddressNotFoundException("Questa via non è presente in questo città");
        }

        JSONArray a = (JSONArray) json.get("predictions");
        JSONObject o = a.getJSONObject(0);
        String s = (String) o.get("description");

        String upperCase;
        upperCase = String.valueOf(upperCase(homeBean.getCity()));

        if (s.contains(homeBean.getCity()) || s.contains(upperCase)) {
            return true;
        }
        else{
            throw new AddressNotFoundException("Address not present in this city");
        }

    }

}
