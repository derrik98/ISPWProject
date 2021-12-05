package it.ispw.daniele.backpacker.entity;

import java.util.Vector;

public class Itinerary {

        private int id;
        private Vector<Monument> itinerary;

        public Itinerary() {
        }

        public Itinerary(int id, Vector<Monument> itinerary) {
            this.id = id;
            this.itinerary = itinerary;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Vector<Monument> getItinerary() {
            return itinerary;
        }

        public void setItinerary(Vector<Monument> route) {
            this.itinerary = route;
        }

        @Override
        public String toString(){
            return String.valueOf(this.id);
//		return this.id + " \"" + this.route + "\" (" + this.id + this.route +")";
        }

}
