package models.entities;

import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class Order {

    private static int ORDER_COUNT = 1;
    private int id;
    private Table table;
    private ArrayList<PersonalOrder> personalOrders;
    private PaymentStrategy paymentStrategy;

    public Order(Table table) {
        this.id = ORDER_COUNT++;
        this.table = table;
        this.personalOrders = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public ArrayList<PersonalOrder> getPersonalOrders() {
        return personalOrders;
    }

    public void setPersonalOrders(ArrayList<PersonalOrder> personalOrders) {
        this.personalOrders = personalOrders;
    }

    public double getTotal() {
        return personalOrders.stream().mapToDouble(po -> po.getTotal()).sum();
    }

}
