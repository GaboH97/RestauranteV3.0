package models.entities;

import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class Waiter {

    public static final int REST_DURATION = 10; //In minutes
    
    private int id;
    private String name;
    private double rate;
    private ArrayList<Order> orders;
    private boolean resting;
    private boolean busy;

    public Waiter(int id, String name, double rate, boolean resting, boolean busy) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.orders = new ArrayList<>();
        this.resting = resting;
        this.busy = busy;
    }

    public Waiter(int id, String name) {
        this.id = id;
        this.name = name;
        this.rate = 0d;
        this.resting = false;
        this.busy = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public boolean isResting() {
        return resting;
    }

    public void setResting(boolean resting) {
        this.resting = resting;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

}
