package run;

import java.util.List;
import models.entities.Client;
import models.entities.Order;
import models.entities.Restaurant;
import models.entities.Table;

/**
 * 
 * @author Gabriel Huertas <gabriel970826@gmail.com>
 */
public class Runner {

    public static void main(String[] args) {

        Restaurant restaurant = Restaurant.getInstance();
        List<Client> group1 = restaurant.clientsArrival();

        Table table = restaurant.getAvailableTable();
        table.occupy();

        Order order = new Order(table);
        order.createPersonalOrders(group1);

        System.out.println(order);

        List<Client> group2 = restaurant.clientsArrival();

        Table table2 = restaurant.getAvailableTable();
        table2.occupy();

        Order order2 = new Order(table2);
        order2.createPersonalOrders(group2);
        System.out.println(order2);
        
        restaurant.getKitchen().getChefs().forEach(System.out::println);

    }
}
