package it.ispw.daniele.backpacker.controller.search;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class AddressFromCity extends JSONFactory{


    @Override
    public boolean getJSON(String address, String city) throws JSONNotFound {
        JSONObject json;
        try {
            json = readJsonFromUrl("https://maps.googleapis.com/maps/api/place/autocomplete/json?input=" + convertString(address) + "&types=geocode&key=AIzaSyDKAl31fAwxbDImIXXOxSre5uma5WdOgHg");
            JSONArray a = (JSONArray) json.get("predictions");
            JSONObject o = a.getJSONObject(0);
            JSONArray arr = o.getJSONArray("terms");
            JSONObject ob = arr.getJSONObject(1);

            if(ob.get("value").equals(city)) {
                return true;
            }

            if(!json.getString("status").equals("OK")) {
                throw new JSONNotFound("Questa via non è presente in questo città");
            }

            //System.out.println(json);
            //System.out.println(a);
            //System.out.println(o);
            //System.out.println(arr);
            //System.out.println(ob);
            //System.out.println(ob.get("value"));
        } catch (JSONException | IOException e) {

            e.printStackTrace();
        }
        return true;
    }

}
