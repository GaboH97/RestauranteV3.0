package models.entities;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author Lenovo
 */
public final class Restaurant {

    private ArrayList<Dish> dishes;
    private ArrayList<Table> tables;
    private ArrayList<Order> orders;
    private Waiter waiter1;
    private Waiter waiter2;
    private Cashier cashier;
    private Cash cash;

    public Restaurant() {
        this.dishes = new ArrayList<>();
        this.tables = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.waiter1 = new Waiter(1, "Pedro");
        this.waiter2 = new Waiter(2, "Jorge");
        this.cashier = new Cashier();
        this.cash = new Cash(cashier);
        setUp();
    }

    public void setUp() {
        generateMenu();
        //Generate 14 tables
        tables.addAll(IntStream.range(0, 14).mapToObj(x -> new Table())
                .collect(Collectors.toList()));
    }

    public void generateMenu() {

        //--------------------- MAIN DISHES --------------------------
        dishes.add(new Dish("Greek Chicken Pasta", DishType.MAIN, 15.10, 0));
        dishes.add(new Dish("Tuscan Bean Soup", DishType.MAIN, 15.10, 20));
        dishes.add(new Dish("Yakisoba Noodles", DishType.MAIN, 15.10, 15));
        dishes.add(new Dish("6-Ingredient Vegan Chickpea Curry", DishType.MAIN, 0, 30));
        dishes.add(new Dish("Russian Beef Stroganoff", DishType.MAIN, 0, 30));

        //--------------------- ENTREÉS --------------------------
        dishes.add(new Dish("Scallops with sweet corn puree, prosciutto and lemon butter", DishType.ENTREE, 0, 10));
        dishes.add(new Dish("Goat's cheese pissaladiere tarts", DishType.ENTREE, 0, 5));
        dishes.add(new Dish("Antipasto garlic bread", DishType.ENTREE, 0, 5));
        dishes.add(new Dish("Crisp-fried cheese ravioli", DishType.ENTREE, 0, 8));
        dishes.add(new Dish("Crab cakes with dill mayonnaise", DishType.ENTREE, 0, 10));

        //--------------------- DESSERTS --------------------------
        dishes.add(new Dish("OREO Cookie Split", DishType.DESSERT, 0, 5));
        dishes.add(new Dish("Creamy Mochaccino Mousse", DishType.DESSERT, 0, 5));
        dishes.add(new Dish("Peach Melba Icy Delight", DishType.DESSERT, 0, 8));
        dishes.add(new Dish("Old Fashioned Sour Cream Doughnuts", DishType.DESSERT, 0, 10));
        dishes.add(new Dish("Meyer Lemon Bars", DishType.DESSERT, 0, 5));
        dishes.add(new Dish("Pavlova With Blueberry Jam", DishType.DESSERT, 0, 5));
        dishes.add(new Dish("Banana Pudding Parfaits", DishType.DESSERT, 0, 8));
        dishes.add(new Dish("Strawberry, Currant And Mint Tart With Mascarpone", DishType.DESSERT, 0, 10));
        dishes.add(new Dish("Green Tea Panna Cotta", DishType.DESSERT, 0, 5));
        dishes.add(new Dish("Strawberry Mousse", DishType.DESSERT, 0, 5));
    }

    public ArrayList<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(ArrayList<Dish> dishes) {
        this.dishes = dishes;
    }

    public ArrayList<Table> getTables() {
        return tables;
    }

    public void setTables(ArrayList<Table> tables) {
        this.tables = tables;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public static void main(String[] args) {
        Restaurant r = new Restaurant();
        r.getTables().forEach(System.out::println);

        Client c1 = new Client(1, "Gabo", "F");
        Client c2 = new Client(1, "Gabo", "F");
        
        Group g = new Group();
        g.getClients().add(c1);
        g.getClients().add(c2);

    }
}
