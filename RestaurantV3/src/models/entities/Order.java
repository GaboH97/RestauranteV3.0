package models.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 *
 * @author Gabriel Huertas <gabriel970826@gmail.com>
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
        this.paymentStrategy = choosePaymentStrategy();
    }

    public void createPersonalOrders(List<Client> clients) {
        personalOrders.addAll(clients.stream()
                .map(c -> new PersonalOrder(c))
                .collect(Collectors.toList())
        );
        //Let every client to choose the dishes he/she wants to order
        personalOrders.forEach(po -> po.setSelectedDishes(generatePersonalOrder()));
    }

    /**
     *
     * @return a list of dishes which represent a personal order following a set
     * of rules The customer always consumes a main course The client can
     * request a maximum of one entry and / or two desserts in their respective
     * order.
     */
    public ArrayList<Dish> generatePersonalOrder() {

        ArrayList<Dish> personalOrderDishes = new ArrayList<>();

        //Get a main Dish (Mandatory)
        ArrayList<Dish> mainDishes = Restaurant.getDishesByType(DishType.MAIN);
        Dish mainDish = mainDishes.get(new Random().nextInt(mainDishes.size()));
        personalOrderDishes.add(mainDish);

        boolean entreeAndDessert = Math.random() < 0.5;

        ArrayList<Dish> entreeDishes = Restaurant.getDishesByType(DishType.ENTREE);
        ArrayList<Dish> dessertDishes = Restaurant.getDishesByType(DishType.DESSERT);

        /**
         * Complete menu with one of these two options
         *
         * 1. One entreé and up to two desserts 2. One entreé or up to two
         * desserts
         */
        if (entreeAndDessert) {

            Dish entreeDish = entreeDishes.get(new Random().nextInt(entreeDishes.size()));
            personalOrderDishes.add(entreeDish);

            for (int i = 0; i < new Random().nextInt(2) + 1; i++) {
                Dish desserDish = dessertDishes.get(new Random().nextInt(dessertDishes.size()));
                personalOrderDishes.add(desserDish);
            }

        } else {
            boolean ordersEntree = Math.random() < 0.5;
            if (ordersEntree) {
                Dish entreeDish = entreeDishes.get(new Random().nextInt(entreeDishes.size()));
                personalOrderDishes.add(entreeDish);
            } else {
                for (int i = 0; i < new Random().nextInt(2) + 1; i++) {
                    Dish desserDish = dessertDishes.get(new Random().nextInt(dessertDishes.size()));
                    personalOrderDishes.add(desserDish);
                }
            }
        }
        return personalOrderDishes;
    }

    /**
     *
     * @return Total cost of the order which is equal to the sum of each
     * personal order
     */
    public double getTotal() {
        return personalOrders.stream().mapToDouble(po -> po.getTotal()).sum();
    }
    
    /**
     * 
     * @return Value of the tip equal to 10% of the order's total
     */
    public double calculateWaiterTip() {
        return getTotal() * 0.1;
    }

    /**
     *
     * @return A randomly chosen Payment Strategy
     */
    public PaymentStrategy choosePaymentStrategy() {
        int num = new Random().nextInt(3);
        return num == 0 ? PaymentStrategy.AW : num == 1 ? PaymentStrategy.OFE : PaymentStrategy.AFE;
    }

    /**
     * 
     * @return Amount to be paid by every client when using a All-for-everything payment
     * strategy
     */
    public double getOrderTotalDivided() {
        return getTotal() / personalOrders.size();
    }

    /**
     * Represents total time to
     * @return The maximum consumption time among clients through personal order
     */
    public int maxTimeToConsume() {
        return personalOrders.stream()
                .mapToInt(po -> po.getConsumptionTime())
                .max().getAsInt();
    }

    /**
     *
     * @return
     */
    public Client getClientToPay() {
        return personalOrders.get(new Random().nextInt(personalOrders.size())).getClient();
    }

    /**
     * Method that simulates an order payment according to an specific strategy
     *
     * AW -> American Way AFE - All-for-Everything OFE - One-for-everything
     *
     * @param cash
     */
    public void pay(Cash cash) {
        switch (paymentStrategy) {
            case AW:
                personalOrders.stream()
                        .map(po -> cash.generatePayment(po.getClient(), po.getTotal(), paymentStrategy))
                        .forEach(cash::addPayment);
                break;
            case AFE:
                double paymentPerClient = getOrderTotalDivided();
                personalOrders.stream()
                        .map(po -> cash.generatePayment(po.getClient(), paymentPerClient, paymentStrategy))
                        .forEach(cash::addPayment);
                break;
            case OFE:
                Client clientToPay = getClientToPay();
                Payment payment = cash.generatePayment(clientToPay, getTotal(), paymentStrategy);
                break;
        }
    }
    
    /**
     * Ends consumption, rank dishes per personal order and rate waiter service
     * @param waiter Waiter who took the order
     */
    public void finish(Waiter waiter){
        personalOrders.forEach(PersonalOrder::rankDishes);
    }
    
    //============ GETTERS & SETTERS ================
    
    
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

    public PaymentStrategy getPaymentStrategy() {
        return paymentStrategy;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Table: ").append(table).append(System.getProperty("line.separator"))
                .append("Personal Orders: ").append(System.getProperty("line.separator"));

        personalOrders.forEach(po -> builder.append("\t").append(po.toString()).append(System.getProperty("line.separator")));
        builder.append("Payment Strategy: ").append(paymentStrategy).append(System.getProperty("line.separator"));
        return builder.toString();
    }

}
