package models.entities;

import java.util.ArrayList;
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
    private ArrayList<Integer> serviceRates;
    private WaiterState state;
    private Queue<Order> takenOrders;

    public Waiter(int id, String name) {
        this.id = id;
        this.name = name;
        this.serviceRates = new ArrayList<>();
        this.takenOrders = new LinkedList<>();
        this.serviceRates = new ArrayList<>();
        this.state = WaiterState.AVAILABLE;
    }

    public void attendTables() {
        //Cambia el estado del mesero a atentiendo
        attend();
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
    public void takeOrder(Order order) {
        takenOrders.add(order);
    }

    /**
     *
     * @return The first order (if present) taken by the waiter in FIFO style
     */
    public Order leaveOrder() {
        return takenOrders.poll();
    }

    public void addServiceRate(Integer serviceRate) {
        serviceRates.add(serviceRate);
    }

    /**
     *
     * @return Average service rate of this waiter
     */
    public Double getAverageRate() {
        return serviceRates.stream().mapToInt(r -> r).summaryStatistics().getAverage();
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

    public ArrayList<Integer> getServiceRates() {
        return serviceRates;
    }

    public void setServiceRates(ArrayList<Integer> serviceRates) {
        this.serviceRates = serviceRates;
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

    public void attend() {
        setState(WaiterState.ATTENDING_TABLE);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Taken Orders").append(System.getProperty("line.separator"));
        takenOrders.forEach(o -> builder.append("\t").append(o.toString()).append(System.getProperty("line.separator")));
        return builder.toString();
    }

}
