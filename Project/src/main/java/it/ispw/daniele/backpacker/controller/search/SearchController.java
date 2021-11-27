package it.ispw.daniele.backpacker.controller.search;

import it.ispw.daniele.backpacker.bean.HomeBean;
import it.ispw.daniele.backpacker.bean.ResultBean;
import it.ispw.daniele.backpacker.entity.Itinerary;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class SearchController{// extends HomeBean {


    private static SearchController INSTANCE = null;
    public ResultBean resultBean = ResultBean.getInstance();
    private static MonumentFromAddress mfa = MonumentFromAddress.getInstance();

    public static SearchController getInstances() {
        if (INSTANCE == null) {
            INSTANCE = new SearchController();
        }
        return INSTANCE;
    }


    public HomeBean getInput(String country, String city, String address) throws IOException, JSONException, JSONNotFound {
        //HomeBean interfacebean;
        //System.out.println(country + city + address);
        JSONFactory firstCheck = CityFromCountry.getInstance();
        JSONFactory.convertString(address);
        if (firstCheck.getJSON(city, country)) {
//    		interfacebean = new InterfaceBean(country, city, address);
//    		interfacebean.setCountry(country);
//    		interfacebean.setCity(city);

            JSONFactory secondCheck = new AddressFromCity();
            if (secondCheck.getJSON(address, city)) {
//				interfacebean.setAddress(address);
                searchMonuments();     //RIMOSSO DOPO
                createItinerary(mfa.getMonuments());
//				createItinerary(mfa.getMonuments());
                //JSONFactory thirdCheck = new MonumentFromAddress();
                //if(thirdCheck.getJSON(address, "monument")) {
                //interfacebean.setMonuments(MonumentFromAddress.monuments); } else {
                //interfacebean = null;
                // }
                return HomeBean.getInstance();
            }
//    		else {
//    			interfacebean = null;
//    		}
//        } else {
//           // interfaceBean = null; RIMOSSO DOPO
//        }
//      //  return interfaceBean; RIMOSSO DOPO

        }
        return HomeBean.getInstance();
    }

    public List<String> searchMonuments() throws JSONNotFound {
        mfa.getJSON(HomeBean.getInstance().getAddress(), "monuments");
        System.out.println("MONUMENTI" + mfa.getMonuments());
        resultBean.setMonuments(mfa.getMonuments());
        return mfa.getMonuments();

    }

    public List<String> createItinerary(Vector<String> attraction) {
        Random num = new Random();
        Vector<String> copyList = new Vector<>();
        copyList = attraction;
        System.out.println(copyList.size());
        Vector<String> itinerary = new Vector<>();
        System.out.println("COPYLIST   " + copyList);
        for (int i = 0; i <= 5; i++) {
            int index = num.nextInt(copyList.size());
//    		int index = num.nextInt(10);
//    		System.out.println(copyList.get(index));
            itinerary.add(copyList.get(index));
//    		itinerary.add(copyList.get(i));
            Itinerary iti = new Itinerary();
            iti.setItinerary(attraction);
            System.out.println("ENTITY " + iti.getItinerary());
            resultBean.getInstance().setItinerary(itinerary); //RIMOSSO DOPO
//    		System.out.println(resultBean.getInstance().getItinerary());
//    		copyList.remove(index);
            System.out.println("elemento scelto   :" + copyList.get(index));
            copyList.remove(index);
            System.out.println("COPYLIST   " + copyList);
//    		System.out.println("INFO" + num.nextInt(10) + copyList + itinerary);

//    		new ResultPage().getInstance().addRoute(itinerary.toString());
        }

      //  new ResultPage().getInstance().addRoute(itinerary);   RIMOSSO DOPO
//    	new ResultPage().getInstance().addRoute(itinerary);


//    	System.out.println(itinerary);
//    	resultBean.getInstance().setItinerary(itinerary);
//    	System.out.println(resultBean.getInstance().getItinerary());
        return itinerary;
    }


    public void searchAttraction() {

    }

    public void searchRestaurants() {

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
