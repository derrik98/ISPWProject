package it.ispw.daniele.backpacker.controller.search;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import it.ispw.daniele.backpacker.bean.HomeBean;
import it.ispw.daniele.backpacker.exceptions.AddressNotFoundException;
import it.ispw.daniele.backpacker.exceptions.CityNotFoundException;
import it.ispw.daniele.backpacker.exceptions.MonumentNotFoundException;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class JSONFactory {
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }


    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        }
    }

    public static String convertString(String string) {
        String newString;
        newString = string.replaceAll("\\s+", "%20");
        return newString;
    }

    protected StringBuilder upperCase(String country) {
        StringBuilder newString = new StringBuilder();
        boolean upp = false;
        newString.append(country.substring(0, 1).toUpperCase());
        for(int i = 1; i < country.length(); i++){
            if(country.charAt(i) == ' '){
                newString.append(" ");
                upp = true;
            }
            else {
                if(upp){
                    newString.append(country.substring(i, i + 1).toUpperCase());
                }
                else {
                    newString.append(country.charAt(i));
                }
                upp = false;
            }
        }
        return newString;
    }

    protected abstract boolean getJSON(HomeBean homeBean) throws CityNotFoundException, AddressNotFoundException, MonumentNotFoundException, IOException;


}
