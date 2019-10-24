package models.entities;

import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Lenovo
 */
public class PersonalOrder {
    
    private Client client;
    private HashMap<Dish, Integer> rankedDishes;

    public PersonalOrder(Client client, HashMap<Dish, Integer> rankedDishes) {
        this.client = client;
        this.rankedDishes = rankedDishes;
    }
    
    public PersonalOrder(Client client) {
        this.client = client;
        this.rankedDishes = new HashMap<>();
    }
    
    public void rankDishes(){
        rankedDishes.entrySet().stream().map((entry) -> entry.getKey()).forEachOrdered((dish) -> {
            rankedDishes.compute(dish,(k,v)-> new Random().nextInt(6));
        });
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
}
