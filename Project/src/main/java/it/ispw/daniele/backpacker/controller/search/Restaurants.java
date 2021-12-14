package it.ispw.daniele.backpacker.controller.search;

import it.ispw.daniele.backpacker.entity.Monument;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Restaurants extends JSONFactory {

    public Restaurants(String address) {
        super();
    }

    @Override
    public boolean getJSON(String address, String country) throws JSONNotFound, IOException {
        JSONObject json = readJsonFromUrl("https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurants+in+"
                + convertString(address) +
                "&radius=8000&type=restaurants&language=it&key=AIzaSyDKAl31fAwxbDImIXXOxSre5uma5WdOgHg");;
        try {
            //json = readJsonFromUrl("https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + type + "+in+" + convertString(address) + "&radius=8000&type=tourist_attraction&language=it&key=AIzaSyDKAl31fAwxbDImIXXOxSre5uma5WdOgHg");

//            json = readJsonFromUrl("https://maps.googleapis.com/maps/api/place/textsearch/json?query=monuments+in+"
//                    + convertString(address) +
//                    "&radius=8000&type=tourist_attraction&language=it&key=AIzaSyDKAl31fAwxbDImIXXOxSre5uma5WdOgHg");
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
            if(!json.get("status").equals("OK")) {
                System.out.println(json);
                System.out.println(json.get("status"));
                throw new JSONNotFound("Non sono presenti monumenti in questa zona");
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }
}
