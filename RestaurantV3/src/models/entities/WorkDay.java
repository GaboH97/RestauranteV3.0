package models.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author Gabriel Huertas <gabriel970826@gmail.com>
 */
public class WorkDay {

    private ArrayList<Order> orders;

    public WorkDay() {
        orders = new ArrayList<>();
    }

    public void addAllOrders(ArrayList<Order> orders) {
        this.orders.addAll(orders);
    }

    public Map<Dish, Long> getOrderedDishesWithQuantity() {
        List<Dish> totalOrderedDishes = getAllOrderedDishes();

        return totalOrderedDishes.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting())
                );
        //return orders.stream().collect(Collectors.groupingBy(o -> o,Collectors.counting()));
    }

    /**
     * @return All ordered dishes for this workday
     */
    public List<Dish> getAllOrderedDishes() {
        List<Dish> allOrderedDishes = new ArrayList<>();
        orders.forEach((order) -> {
            order.getPersonalOrders().forEach((personalOrder) -> {
                allOrderedDishes.addAll(personalOrder.getOrderedDishes()
                        .stream()
                        .map(od -> od.getDish())
                        .collect(Collectors.toList()));
            });
        });
        return allOrderedDishes;
    }

    public Map<Dish, Long> getBestSellingDishesPerDishType() {

        Map<Dish, Long> bestSellingDishesPerDishType = new HashMap<>();
        Map<Dish, Long> dishesSold = getOrderedDishesWithQuantity();

        //Get Dish Type values
        List<DishType> dishTypes = Arrays.asList(DishType.values());

        dishTypes.forEach((DishType dishType) -> {

            //Filter Map entries by dish type
            Map<Dish, Long> dishesPerType = dishesSold.entrySet()
                    .stream()
                    .filter((Map.Entry<Dish, Long> e) -> e.getKey().getDishType().equals(dishType))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            //Get best selling dish
            Map.Entry<Dish, Long> max = Collections.max(dishesPerType.entrySet(), Map.Entry.comparingByValue());

            bestSellingDishesPerDishType.put(max.getKey(), max.getValue());

        });

        return bestSellingDishesPerDishType;
    }

}
