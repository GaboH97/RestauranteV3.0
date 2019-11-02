package models.entities;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author Lenovo
 */
public final class Restaurant {
    
    
    //================= ATTRIBUTES
    private ArrayList<Dish> menu;
    private ArrayList<Table> tables;
    private ArrayList<Order> orders;
    private Waiter waiter1;
    private Waiter waiter2;
    private Cashier cashier;
    private Cash cash;

    public Restaurant() {
        this.menu = new ArrayList<>();
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
    
    /**
     * 
     * @return First available table, otherwise, null 
     */
    public Table getAvailableTable() {
        return tables.stream().filter(t -> t.isAvailable()).findFirst().orElse(null);
    }

    public void generateMenu() {

        //--------------------- MAIN DISHES --------------------------
        menu.add(new Dish("Greek Chicken Pasta", DishType.MAIN, 15.10, 0));
        menu.add(new Dish("Tuscan Bean Soup", DishType.MAIN, 15.10, 20));
        menu.add(new Dish("Yakisoba Noodles", DishType.MAIN, 15.10, 15));
        menu.add(new Dish("6-Ingredient Vegan Chickpea Curry", DishType.MAIN, 0, 30));
        menu.add(new Dish("Russian Beef Stroganoff", DishType.MAIN, 0, 30));

        //--------------------- ENTREÉS --------------------------
        menu.add(new Dish("Scallops with sweet corn puree, prosciutto and lemon butter", DishType.ENTREE, 0, 10));
        menu.add(new Dish("Goat's cheese pissaladiere tarts", DishType.ENTREE, 0, 5));
        menu.add(new Dish("Antipasto garlic bread", DishType.ENTREE, 0, 5));
        menu.add(new Dish("Crisp-fried cheese ravioli", DishType.ENTREE, 0, 8));
        menu.add(new Dish("Crab cakes with dill mayonnaise", DishType.ENTREE, 0, 10));

        //--------------------- DESSERTS --------------------------
        menu.add(new Dish("OREO Cookie Split", DishType.DESSERT, 0, 5));
        menu.add(new Dish("Creamy Mochaccino Mousse", DishType.DESSERT, 0, 5));
        menu.add(new Dish("Peach Melba Icy Delight", DishType.DESSERT, 0, 8));
        menu.add(new Dish("Old Fashioned Sour Cream Doughnuts", DishType.DESSERT, 0, 10));
        menu.add(new Dish("Meyer Lemon Bars", DishType.DESSERT, 0, 5));
        menu.add(new Dish("Pavlova With Blueberry Jam", DishType.DESSERT, 0, 5));
        menu.add(new Dish("Banana Pudding Parfaits", DishType.DESSERT, 0, 8));
        menu.add(new Dish("Strawberry, Currant And Mint Tart With Mascarpone", DishType.DESSERT, 0, 10));
        menu.add(new Dish("Green Tea Panna Cotta", DishType.DESSERT, 0, 5));
        menu.add(new Dish("Strawberry Mousse", DishType.DESSERT, 0, 5));
    }
    
    /**
     * 
     * @return a list of dishes which represent a personal order following a set of rules
     *  The customer always consumes a main course
     *  The client can request a maximum of one entry and / or two desserts in their respective order.
     */
    public ArrayList<Dish> generatePersonalOrder() {

        ArrayList<Dish> personalOrderDishes = new ArrayList<>();

        //Get a main Dish (Mandatory)
        ArrayList<Dish> mainDishes = getDishesByType(DishType.MAIN);
        Dish mainDish = mainDishes.get(new Random().nextInt(mainDishes.size()));
        personalOrderDishes.add(mainDish);

        boolean entreeAndDessert = Math.random() < 0.5;

        ArrayList<Dish> entreeDishes = getDishesByType(DishType.ENTREE);
        ArrayList<Dish> dessertDishes = getDishesByType(DishType.DESSERT);

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

    public ArrayList<Dish> getDishesByType(DishType dishType) {
        return (ArrayList<Dish>) menu.stream()
                .filter(d -> d.getDishType().equals(dishType))
                .collect(Collectors.toList());
    }

    public ArrayList<Dish> getDishes() {
        return menu;
    }

    public void setDishes(ArrayList<Dish> dishes) {
        this.menu = dishes;
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

    public ArrayList<Dish> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<Dish> menu) {
        this.menu = menu;
    }

    public Waiter getWaiter1() {
        return waiter1;
    }

    public void setWaiter1(Waiter waiter1) {
        this.waiter1 = waiter1;
    }

    public Waiter getWaiter2() {
        return waiter2;
    }

    public void setWaiter2(Waiter waiter2) {
        this.waiter2 = waiter2;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public Cash getCash() {
        return cash;
    }

    public void setCash(Cash cash) {
        this.cash = cash;
    }

    public static void main(String[] args) {
        Restaurant r = new Restaurant();
        r.getTables().forEach(System.out::println);

        Client c1 = new Client(1, "Gabo", "F");
        Client c2 = new Client(1, "Gabo", "F");

        Group g = new Group();
        g.getClients().add(c1);
        g.getClients().add(c2);

        for (int i = 0; i < 10; i++) {
            System.out.println("================= Order =========================");
            r.generatePersonalOrder().forEach(System.out::println);
            System.out.println("================= Order =========================");

        }

    }
}
