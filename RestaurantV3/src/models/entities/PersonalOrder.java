package models.entities;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

/**
 *
 * @author Lenovo
 */
public class PersonalOrder {

    private Client client;
    private ArrayList<OrderedDish> orderedDishes;
    private int consumptionTime;
    
    /**
     * 
     * @param client Client who has placed its order
     * @param selectedDishes Selected dishes
     * @param consumptionTime 
     */
    public PersonalOrder(Client client, ArrayList<Dish> selectedDishes, int consumptionTime) {
        this.client = client;
        this.orderedDishes = new ArrayList<>();
        this.consumptionTime = consumptionTime;
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
     * Check if this personal order is ready to be served
     * 
     * @return true if this personal order is ready, otherwise, false
     */
    public boolean isReady(){
       return orderedDishes.stream()
               .allMatch(o->o.getOrderedDishState().equals(OrderedDishState.PREPARED));
    }

    /**
     * Set all ordered dishes' state to eating
     */
    public void eat() {
        orderedDishes.forEach(od -> od.setOrderedDishState(OrderedDishState.EATING));
    }

    /**
     * Set all ordered dishes' state to consumed
     */
    public void finish() {
        orderedDishes.forEach(od -> od.setOrderedDishState(OrderedDishState.CONSUMED));
    }
    
    public void consumeOrder(){
        eat();
        //AQUÍ VA EL TIEMPO QUE SE DEMORA EN CONSUMIR LA ORDEN
        finish();
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
        orderedDishes.addAll(selectedDishes.stream().map(sd -> new OrderedDish(sd)).collect(Collectors.toList()));
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
