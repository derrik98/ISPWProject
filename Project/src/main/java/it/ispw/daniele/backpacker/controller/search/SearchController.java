package it.ispw.daniele.backpacker.controller.search;

import it.ispw.daniele.backpacker.bean.HomeBean;
import it.ispw.daniele.backpacker.bean.ItineraryBean;
import it.ispw.daniele.backpacker.bean.ResultBean;
import it.ispw.daniele.backpacker.entity.Itinerary;
import it.ispw.daniele.backpacker.entity.Monument;
import it.ispw.daniele.backpacker.exceptions.AddressNotFoundException;
import it.ispw.daniele.backpacker.exceptions.CityNotFoundException;
import it.ispw.daniele.backpacker.exceptions.MonumentNotFoundException;
import it.ispw.daniele.backpacker.utils.Controller;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class SearchController extends Controller {


    //private static SearchController INSTANCE = null;
    public ResultBean resultBean = ResultBean.getInstance();
    private static final MonumentFromAddress monumentFromAddress = MonumentFromAddress.getInstance();

    /*public static SearchController getInstances() {
        if (INSTANCE == null) {
            INSTANCE = new SearchController();
        }
        return INSTANCE;
    }*/


//    public HomeBean getInput(String country, String city, String address) throws IOException, JSONException, JSONNotFound {
//        //HomeBean interfacebean;
//        //System.out.println(country + city + address);
//        JSONFactory firstCheck = CityFromCountry.getInstance();
//        JSONFactory.convertString(address);
//        if (firstCheck.getJSON(city, country)) {
////    		interfacebean = new InterfaceBean(country, city, address);
////    		interfacebean.setCountry(country);
////    		interfacebean.setCity(city);
//
//            JSONFactory secondCheck = new AddressFromCity();
//            if (secondCheck.getJSON(address, city)) {
////				interfacebean.setAddress(address);
//                searchMonuments();     //RIMOSSO DOPO
//                createItinerary(mfa.getMonuments());
////				createItinerary(mfa.getMonuments());
//                //JSONFactory thirdCheck = new MonumentFromAddress();
//                //if(thirdCheck.getJSON(address, "monument")) {
//                //interfacebean.setMonuments(MonumentFromAddress.monuments); } else {
//                //interfacebean = null;
//                // }
//                return HomeBean.getInstance();
//            }
////    		else {
////    			interfacebean = null;
////    		}
////        } else {
////           // interfaceBean = null; RIMOSSO DOPO
////        }
////      //  return interfaceBean; RIMOSSO DOPO
//
//        }
//        return HomeBean.getInstance();
//    }

    public HomeBean checkInput(HomeBean bean) throws CityNotFoundException, AddressNotFoundException, MonumentNotFoundException, IOException {
        JSONFactory checkCityCountry = new CityFromCountry();//.getInstance();

        if (checkCityCountry.getJSON(bean.getCity(), bean.getCountry())) {
            JSONFactory checkAddressCity = new AddressFromCity();
            if(checkAddressCity.getJSON(bean.getAddress(), bean.getCity())) {
                MonumentFromAddress monuments = new MonumentFromAddress();
                ArrayList<String> result = monuments.getMonuments(bean.getAddress());
                monuments.getJSON(bean.getAddress(), "monuments");
                ArrayList<Itinerary> allItineraries = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    //this.createItinerary(result);
                    //////allItineraries.add(this.createItinerary(result)); ////c'era
                    //////System.out.println(allItineraries.size());
                }
                return HomeBean.getInstance();
            }
            else{
                return null;
            }
        }
        return null;
    }

    /*public Vector<Monument> searchMonuments() throws MonumentNotFoundException {
        monumentFromAddress.getJSON(HomeBean.getInstance().getAddress(), "monuments");
        System.out.println("MONUMENTI" + monumentFromAddress.getMonuments());
        resultBean.setMonuments(monumentFromAddress.getMonuments());
        return monumentFromAddress.getMonuments();

    }*/

    public List<ItineraryBean> createItinerary(String address) throws MonumentNotFoundException {

        MonumentFromAddress monuments = new MonumentFromAddress();
        ArrayList<String> result = monuments.getMonuments(address);
        monuments.getJSON(address, "monuments");
        ArrayList<Itinerary> allItineraries = new ArrayList<>();

        String steps = "";
        for(int i = 0; i < result.size(); i++){
            steps = steps + "/" + result.get(i);
        }

        System.out.println("STEPS " + steps);
        ArrayList<Itinerary> it = new ArrayList<>();

        for(int i = 0; i < 5; i++) {
            Random num = new Random();
            //Vector<String> copyList = pointOfInterest;
            //copyList = attraction;
            // System.out.println(copyList.size());
            //Itinerary itinerary = new Itinerary(steps);
            StringBuilder vector = new StringBuilder();
            //Vector<Itinerary> allItinerary = new Vector<>();
            //System.out.println("COPYLIST   " + copyList);
            for (int j = 0; j <= 5; j++) {
                //int index = num.nextInt(copyList.size());
                int index = num.nextInt((result.size() - 1) + 1);
                //System.out.println("INDEX " + index + " BOUND " + pointOfInterest.size());
//    		int index = num.nextInt(10);
//    		System.out.println(copyList.get(index));
                //    itinerary.add(copyList.get(index));
                //itinerary.setItinerary(pointOfInterest.get(index));
                if (!vector.toString().contains(result.get(index))) {
                    //vector.add(pointOfInterest.get(index));
                    vector.append("/").append(result.get(index));
                }
                //vector.add(pointOfInterest.get(index));
//    		itinerary.add(copyList.get(i));
                //Itinerary iti = new Itinerary();
                //iti.setItinerary(pointOfInterest);
                //System.out.println("ENTITY " + iti.getItinerary());
                // resultBean.getInstance().setItinerary(itinerary); //RIMOSSO DOPO
//    		System.out.println(resultBean.getInstance().getItinerary());
//    		copyList.remove(index);
                // System.out.println("elemento scelto   :" + copyList.get(index));
                //copyList.remove(index);
                //
                // System.out.println("COPYLIST   " + copyList);
//    		System.out.println("INFO" + num.nextInt(10) + copyList + itinerary);

//    		new ResultPage().getInstance().addRoute(itinerary.toString());
            }
//        allItinerary.add(new Itinerary(itinerary));
//        ResultBean.getInstance().setItinerary(allItinerary);
            //System.out.println("ITINERARYYYYYY " + vector);

            //itinerary.setItinerary(vector.toString());
            //  new ResultPage().getInstance().addRoute(itinerary);   RIMOSSO DOPO
//    	new ResultPage().getInstance().addRoute(itinerary);

            //    	System.out.println(itinerary);
//    	ResultBean.getInstance().setItinerary(itinerary);
//    	System.out.println(resultBean.getInstance().getItinerary());
            Itinerary itinerary = new Itinerary(vector.toString());
            it.add(itinerary);
        }
        return this.convert(it);
    }


    public void searchAttraction() {

    }

    public void searchRestaurants(HomeBean bean) {
        //if (bean.isRestaurant()){
        // JSONFactory checkRestaurant = new Restaurants(bean.getAddress());
        //}

    }

    public void getResult() {

    }

    public void setResult() {

    }

    public void showResultOnMap() {

    }

    public void getCountry() {

    }

    public void getCity() {

    }

    public void getAddress() {

    }
}