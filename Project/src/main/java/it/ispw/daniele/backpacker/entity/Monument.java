package it.ispw.daniele.backpacker.entity;

public class Monument {

    private int id;
    private String route;

    public Monument() {
    }

    public Monument(int id, String route) {
        this.id = id;
        this.route = route;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    @Override
    public String toString(){
        return String.valueOf(this.id);
//		return this.id + " \"" + this.route + "\" (" + this.id + this.route +")";
    }
}
