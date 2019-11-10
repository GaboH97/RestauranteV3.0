package run;

import java.util.Map;
import models.entities.Dish;
import models.entities.OrderedDish;
import models.entities.Restaurant;

/**
 *
 * @author Gabriel Huertas <gabriel970826@gmail.com>
 */
public class Runner {
    
    public static void main(String[] args) {
        
        Restaurant restaurant = Restaurant.getInstance();
//        List<Client> group1 = restaurant.clientsArrival();
//
//        Table table = restaurant.getAvailableTable();
//        table.occupy();
//
//        Order order = new Order(table);
//        order.createPersonalOrders(group1);
//
//        System.out.println(order);
//
//        List<Client> group2 = restaurant.clientsArrival();
//
//        Table table2 = restaurant.getAvailableTable();
//        table2.occupy();
//
//        Order order2 = new Order(table2);
//        order2.createPersonalOrders(group2);
//        System.out.println(order2);
//        
//        order.pay(restaurant.getCash());
//        order2.pay(restaurant.getCash());
//        
//        System.out.println("TOTAL INCOME "+restaurant.getCash().getTotalIncome());
//        
        restaurant.start();
        
        Map<Dish, Double> dishesWithAverageRate = restaurant.getDishesWithAverageRate();
        
        dishesWithAverageRate.entrySet().forEach(e -> System.out.println(e.getKey().getName() + " " + e.getValue()));
        
        Map<Dish, Double> bestRatedDishes = restaurant.getBestRatedDishesPerDishType();
        
        System.out.println("=============== BEST RATED DISHES ==============");
        bestRatedDishes.entrySet().forEach(e -> System.out.println(e.getKey().getName() + " " + e.getKey().getDishType().name() + " " + e.getValue()));
        
        System.out.println("=============== ORDERS GROUPED BY PAYMENT STRATEGY ====================");
        
        restaurant.getCountOfOrdersByPaymentStrategy().entrySet().forEach(e -> System.out.println(e.getKey().getName() + " " + e.getValue()));
    }
}
