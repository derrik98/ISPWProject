package it.ispw.daniele.backpacker.controller.search;

import it.ispw.daniele.backpacker.bean.HomeBean;
import it.ispw.daniele.backpacker.bean.ItineraryBean;
import it.ispw.daniele.backpacker.entity.Itinerary;
import it.ispw.daniele.backpacker.exceptions.AddressNotFoundException;
import it.ispw.daniele.backpacker.exceptions.CityNotFoundException;
import it.ispw.daniele.backpacker.exceptions.MonumentNotFoundException;
import it.ispw.daniele.backpacker.utils.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SearchController extends Controller {

    public boolean checkInput(HomeBean bean) throws CityNotFoundException, AddressNotFoundException, MonumentNotFoundException, IOException {
        JSONFactory checkCityCountry = new CityFromCountry();

        if (checkCityCountry.getJSON(bean)) {
            JSONFactory checkAddressCity = new AddressFromCity();
            if(checkAddressCity.getJSON(bean)) {
                MonumentFromAddress monuments = new MonumentFromAddress();
                return monuments.getJSON(bean);
            }
            else{
                return false;
            }
        }
        return false;
    }

    public List<ItineraryBean> createItinerary(HomeBean homeBean) throws MonumentNotFoundException {

        MonumentFromAddress monuments = new MonumentFromAddress();
        List<String> result = monuments.getMonuments(homeBean);

        monuments.getJSON(homeBean);

        ArrayList<Itinerary> it = new ArrayList<>();

        for(int i = 0; i < 5; i++) {

            Random num = new Random();

            StringBuilder vector = new StringBuilder();

            for (int j = 0; j <= 5; j++) {

                int index = num.nextInt((result.size() - 1) + 1);

                if (!vector.toString().contains(result.get(index)) && j != 0) {

                    vector.append("/").append(result.get(index));
                }
                else {

                    vector.append(result.get(index));
                }

            }

            Itinerary itinerary = new Itinerary(new Random().nextInt(1000), vector.toString());
            it.add(itinerary);
        }
        return this.convert(it);
    }

    public void searchRestaurants(HomeBean bean) {
        //TODO
    }

}