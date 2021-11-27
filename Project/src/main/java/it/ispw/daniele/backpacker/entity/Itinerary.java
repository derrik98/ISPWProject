package it.ispw.daniele.backpacker.entity;

import java.util.Vector;

public class Itinerary {

        private int id;
        private Vector<String> itinerary;

        public Itinerary() {
        }

        public Itinerary(int id, Vector<String> itinerary) {
            this.id = id;
            this.itinerary = itinerary;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Vector<String> getItinerary() {
            return itinerary;
        }

        public void setItinerary(Vector<String> route) {
            this.itinerary = route;
        }

        @Override
        public String toString(){
            return String.valueOf(this.id);
//		return this.id + " \"" + this.route + "\" (" + this.id + this.route +")";
        }

}
