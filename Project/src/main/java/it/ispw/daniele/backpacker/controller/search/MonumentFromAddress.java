package it.ispw.daniele.backpacker.controller.search;

import it.ispw.daniele.backpacker.entity.Monument;
import it.ispw.daniele.backpacker.exceptions.MonumentNotFoundException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Vector;

public class MonumentFromAddress extends JSONFactory{

    private Vector<Monument> monuments = new Vector<>();
    private static MonumentFromAddress instance = null;

    public static synchronized MonumentFromAddress getInstance() {
        if(instance==null) {
            instance = new MonumentFromAddress();
        }
        return instance;
    }

    protected MonumentFromAddress(){
    }

    public void setMonuments(Vector<Monument> monuments) {
        this.monuments = monuments;
    }

    public Vector<Monument> getMonuments() {
        return this.monuments;
    }


    @Override
    public boolean getJSON(String address, String type) throws MonumentNotFoundException {
        JSONObject json = null;
        try {
            json = readJsonFromUrl("https://maps.googleapis.com/maps/api/place/textsearch/json?query=monuments+in+"
                    + convertString(address) +
                    "&radius=8000&type=tourist_attraction&language=it&key=AIzaSyDKAl31fAwxbDImIXXOxSre5uma5WdOgHg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //////https://maps.googleapis.com/maps/api/place/textsearch/json?query=monuments+in+viadelcorso&radius=8000&type=tourist_attraction&language=it&pagetoken=AcYSjRg09uzz6xPSqXiBlHQMvTuSusuBHqvv4toPcF5W1Zb3jtAXS1aGe-104lK4774v1HkarzuNBur5npDjMZsDOUhkkYFYnaKZxIz6udZ655kFhxaFNyVfp-MiXI1irBq1QvI8ukQBGkNBQ-MMca-htmAdWbSYAQovJqnwygPu2gk83_xnO8VrZrP05vYosN9shAocrUQEQyP6C3gHmlKaa6f3J_7EWh6-mDk43b0id9AbyFkBavrtMdtxHoRp_oUCbAGW7VTjDpdmOE5cYRz6uzeC5AKlnulx7gJpl1BbVOiEVno0a39iS-PTlv07DKsdBQr8AMbLe5A_WULds46Ju2KBRnFurhPLmSzUqen3t0Xmd4p8GTiVP-dmLPbRrMJSmZBo-elw048KgsdywTiNjlxfxCCvHZ_83MVgM0n5JAEbPOOd2v8jta3t5M2_rb5tdwgnfiOHfBjXZ2S1C0VUVT_Fz0uNv4KphEGDbwqMPDmy6U2b8Z1MA1VMrl7wLDYe31N8-7rYhDr9rzqzqodYEjoaQyba1yd1Mqik1WLUNRlOr3TZUrBl-uqnwQSHoVeYHqxXtemLFqsp2HL1ALeKhwIjgcuoXBx45q2oJ8aKi9PJw5fmR2So7g-WrY0D3l0tA1qXILl1IQ&key=AIzaSyDKAl31fAwxbDImIXXOxSre5uma5WdOgHg
        //nextpage
        try {
            //json = readJsonFromUrl("https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + type + "+in+" + convertString(address) + "&radius=8000&type=tourist_attraction&language=it&key=AIzaSyDKAl31fAwxbDImIXXOxSre5uma5WdOgHg");

//            json = readJsonFromUrl("https://maps.googleapis.com/maps/api/place/textsearch/json?query=monuments+in+"
//                    + convertString(address) +
//                    "&radius=8000&type=tourist_attraction&language=it&key=AIzaSyDKAl31fAwxbDImIXXOxSre5uma5WdOgHg");
            assert json != null;
            JSONArray a = (JSONArray) json.get("results");
            int i = 0;
            JSONObject o = null;
            while (i < a.length()){ //OKKKKKKK
                o = a.getJSONObject(i);
                //System.out.println(o);
                /*this.getJSONName(o);
                this.getLat(o);
                this.getLon(o);
                this.getRating(o);
                this.getTypes(o);
                Monument monument = new Monument(this.getJSONName(o), this.getLat(o), this.getLon(o), this.getRating(o), this.getTypes(o));
                */System.out.println(o.get("name"));
                //getMonuments().add(o.get("name").toString());
                //getMonuments().add(monument);
                i++;
            }
            if(!json.get("status").equals("OK")) {
                throw new MonumentNotFoundException("Non sono presenti monumenti in questa zona");
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    private JSONArray getTypes(JSONObject jsonObject) {
        return (JSONArray) jsonObject.get("types");
    }

    private String getRating(JSONObject jsonObject) {
            return jsonObject.get("rating").toString();
    }

    private BigDecimal getLat(JSONObject jsonObject) {
        JSONObject geometry = (JSONObject) jsonObject.get("geometry");
        JSONObject location = (JSONObject) geometry.get("location");
        return (BigDecimal) location.get("lat");
    }

    private BigDecimal getLon(JSONObject jsonObject) {
        JSONObject geometry = (JSONObject) jsonObject.get("geometry");
        JSONObject location = (JSONObject) geometry.get("location");
        return (BigDecimal) location.get("lng");
    }

    public String getJSONName(JSONObject jsonObject){
        return (String) jsonObject.get("name");
    }
}
