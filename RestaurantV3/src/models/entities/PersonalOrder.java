package models.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Lenovo
 */
public class PersonalOrder {

    private Client client;
    private HashMap<Dish, Integer> rankedDishes;
    private PaymentType paymentType;

    public PersonalOrder(Client client, HashMap<Dish, Integer> rankedDishes) {
        this.client = client;
        this.rankedDishes = rankedDishes;
    }

    public PersonalOrder(Client client) {
        this.client = client;
        this.rankedDishes = new HashMap<>();
    }

    public void setDishes(ArrayList<Dish> dishes) {
        rankedDishes.keySet().addAll(dishes);
    }

    public void rankDishes() {
        rankedDishes.entrySet().stream().map((entry) -> entry.getKey()).forEachOrdered((dish) -> {
            rankedDishes.compute(dish, (k, v) -> new Random().nextInt(6));
        });
    }
    
    /**
     * 
     * @return Total for ordered dishes
     */
    public double getTotal() {
        return rankedDishes.entrySet()
                .stream()
                .mapToDouble((entry) -> entry.getKey().getPrice())
                .sum();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public HashMap<Dish, Integer> getRankedDishes() {
        return rankedDishes;
    }

    public void setRankedDishes(HashMap<Dish, Integer> rankedDishes) {
        this.rankedDishes = rankedDishes;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
