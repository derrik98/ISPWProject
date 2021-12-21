package it.ispw.daniele.backpacker.controller.search;

import it.ispw.daniele.backpacker.exceptions.AddressNotFoundException;
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
        JSONArray arr = o.getJSONArray("terms");
        JSONObject ob = arr.getJSONObject(1);


        System.out.println(upperCaseFirst(city) + " VALUE " + ob.get("value"));
            if(!ob.get("value").equals(upperCaseFirst(city))) {
                System.out.println(city + " VALUE " + ob.get("value"));
                throw new AddressNotFoundException("Questa via non è presente in questo città");
            }



            System.out.println("Address from city" + json);
            //System.out.println(a);
            //System.out.println(o);
            //System.out.println(arr);
            //System.out.println(ob);
            //System.out.println(ob.get("value"));

        return true;
    }

    public static String upperCaseFirst(String val) {
        char[] arr = val.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        return new String(arr);
    }

}
