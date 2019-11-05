package models.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Lenovo
 */
public class PersonalOrder {

    private Client client;
    private ArrayList<OrderedDish> orderedDishes;
    private int consumptionTime;

    public PersonalOrder(Client client, ArrayList<Dish> selectedDishes) {
        this.client = client;
        this.orderedDishes = new ArrayList<>();
        setSelectedDishes(selectedDishes);
    }

    public PersonalOrder(Client client) {
        this.client = client;
        this.orderedDishes = new ArrayList<>();
    }

    public void rankDishes() {
        Random r = new Random();
        orderedDishes.forEach(o -> o.setRating(r.nextInt(6)));
    }

    /**
     *
     * @return Total for ordered dishes
     */
    public double getTotal() {
        return orderedDishes
                .stream()
                .mapToDouble(d -> d.getDish().getPrice())
                .sum();
    }
    
     public void setSelectedDishes(ArrayList<Dish> selectedDishes) {
         orderedDishes.addAll(selectedDishes.stream().map(sd-> new OrderedDish(sd)).collect(Collectors.toList()));
     }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ArrayList<OrderedDish> getOrderedDishes() {
        return orderedDishes;
    }

    public void setOrderedDishes(ArrayList<OrderedDish> orderedDishes) {
        this.orderedDishes = orderedDishes;
    }

    public int getConsumptionTime() {
        return consumptionTime;
    }

    public void setConsumptionTime(int consumptionTime) {
        this.consumptionTime = consumptionTime;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Client: ").append(client).append(System.getProperty("line.separator"))
                .append("\t").append("Dishes: ").append(System.getProperty("line.separator"));
        orderedDishes
                .forEach(od -> builder.append("\tDish: ").append(od.getDish().getName())
                .append(" Dish Type: ").append(od.getDish().getDishType())
                .append(System.getProperty("line.separator")));

        return builder.toString();
    }

   

}
