package models.entities;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Lenovo
 */
public class Waiter {

    public static final int REST_DURATION = 10; //In minutes

    private int id;
    private String name;
    private double rate;
    private WaiterState state;
    private Queue<Order> takenOrders;
    

    public Waiter(int id, String name, double rate) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.takenOrders = new LinkedList<>();
    }

    public Waiter(int id, String name) {
        this.id = id;
        this.name = name;
        this.rate = 0d;
    }
 
    /**
     * 
     * @return true if the waiter has only taken 3 orders, otherwise false
     */
    public boolean canTakeOrder() {
        return takenOrders.size() < 3;
    }
    
    /**
     * 
     * @param order 
     */
    public void takeOrder(Order order){
        takenOrders.add(order);
    }
    
    /**
     * 
     * @return The first order (if present) taken by the waiter in FIFO style
     */
    public Order leaveOrder(){
        return takenOrders.poll();
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

    public Queue<Order> getTakenOrders() {
        return takenOrders;
    }

    public void setTakenOrders(Queue<Order> takenOrders) {
        this.takenOrders = takenOrders;
    }

    public WaiterState getState() {
        return state;
    }

    public void setState(WaiterState state) {
        this.state = state;
    }
}
