package it.ispw.daniele.backpacker.controller.search;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class MonumentFromAddress extends JSONFactory{

    public static Vector<String> monuments = new Vector<String>();
    private static MonumentFromAddress instance = null;

    public static synchronized MonumentFromAddress getInstance() {
        if(instance==null) {
            instance = new MonumentFromAddress();
        }
        return instance;
    }

    protected MonumentFromAddress(){
    }

    public static void setMonuments(Vector<String> monuments) {
        MonumentFromAddress.monuments = monuments;
    }

    public Vector<String> getMonuments() {
        return monuments;
    }


    @Override
    public boolean getJSON(String address, String type) throws JSONNotFound {
        JSONObject json;
        try {
            json = readJsonFromUrl("https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + type + "+in+" + convertString(address) + "&radius=7000&type=tourist_attraction&language=it&key=AIzaSyDKAl31fAwxbDImIXXOxSre5uma5WdOgHg");
            JSONArray a = (JSONArray) json.get("results");
            System.out.println(a);
            System.out.println("lunghezza record " + a.length());
            int i = 0;
            //while(!a.getJSONObject(i).get("name").equals(null)) {
            while (i < 20){ //OKKKKKKK
//				System.out.println(a);

                JSONObject o = a.getJSONObject(i);
                System.out.println(o.get("name"));
                monuments.add(o.get("name").toString());
                i++;
            }
//			for(int i = 0; i < a.length(); i++) {
//				JSONObject o = a.getJSONObject(i);
//				System.out.println(o.get("name"));
//				monuments.add(o.get("name").toString());
//				//monuments.add(i, o.get("name").toString());
//			}

            //System.out.println(json);

            if(!json.getString("status").equals("OK")) {
                throw new JSONNotFound("Non sono presenti monumenti in questa zona");
            }
        }
        catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
