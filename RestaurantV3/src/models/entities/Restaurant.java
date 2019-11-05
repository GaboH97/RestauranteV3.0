package models.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author Gabriel Huertas <gabriel970826@gmail.com>
 */
public final class Restaurant {

    //================= ATTRIBUTES ==========================
    
    public static List<Dish> MENU =  Arrays.asList(
            //--------------------- MAIN DISHES --------------------------
            new Dish("Greek Chicken Pasta", DishType.MAIN, 15.10, 35),
            new Dish("Tuscan Bean Soup", DishType.MAIN, 15.10, 20),
            new Dish("Yakisoba Noodles", DishType.MAIN, 15.10, 15),
            new Dish("6-Ingredient Vegan Chickpea Curry", DishType.MAIN, 0, 30),
            new Dish("Russian Beef Stroganoff", DishType.MAIN, 0, 30),
            //--------------------- ENTREÉS --------------------------
            new Dish("Scallops with sweet corn puree, prosciutto and lemon butter", DishType.ENTREE, 0, 10),
            new Dish("Goat's cheese pissaladiere tarts", DishType.ENTREE, 0, 5),
            new Dish("Antipasto garlic bread", DishType.ENTREE, 0, 5),
            new Dish("Crisp-fried cheese ravioli", DishType.ENTREE, 0, 8),
            new Dish("Crab cakes with dill mayonnaise", DishType.ENTREE, 0, 10),
            //--------------------- DESSERTS --------------------------
            new Dish("OREO Cookie Split", DishType.DESSERT, 0, 5),
            new Dish("Creamy Mochaccino Mousse", DishType.DESSERT, 0, 5),
            new Dish("Peach Melba Icy Delight", DishType.DESSERT, 0, 8),
            new Dish("Old Fashioned Sour Cream Doughnuts", DishType.DESSERT, 0, 10),
            new Dish("Meyer Lemon Bars", DishType.DESSERT, 0, 14),
            new Dish("Pavlova With Blueberry Jam", DishType.DESSERT, 0, 10),
            new Dish("Banana Pudding Parfaits", DishType.DESSERT, 0, 8),
            new Dish("Strawberry, Currant And Mint Tart With Mascarpone", DishType.DESSERT, 0, 10),
            new Dish("Green Tea Panna Cotta", DishType.DESSERT, 0, 15),
            new Dish("Strawberry Mousse", DishType.DESSERT, 0, 10)
    );

    private ArrayList<Table> tables;
    private Kitchen kitchen;
    private List<Waiter> waiters;
    private Cashier cashier;
    private Cash cash;

    private Map<Integer, List<Order>> ordersPerDay;

    /**
     * Clients row waiting to be attended
     */
    private Queue<List<Client>> clientsRow;

    /**
     * Singleton instance
     */
    private static Restaurant instance;

    private Restaurant() {

        this.kitchen = new Kitchen();
        this.cashier = new Cashier();
        this.cash = new Cash(cashier);

        this.tables = new ArrayList<>();
        this.waiters = new ArrayList<>();

        this.clientsRow = new LinkedList<>();
        this.ordersPerDay = new TreeMap<>();

        setUp();
    }

    /**
     *
     * @return A singleton-like instance of this class
     */
    public static Restaurant getInstance() {
        if (instance == null) {
            instance = new Restaurant();
        }
        return instance;
    }

    /**
     * Method that starts restaurant simulation
     */
    public void start() {

    }

    /**
     * Clients by themselves are not considered as single arrival units given an
     * arrival rate. Instead, they are grouped and then these are considered as
     * single arrival units
     *
     * @return A list of clients (diners) ranging between 1 to 5 members
     */
    public List<Client> clientsArrival() {
        return IntStream.rangeClosed(1, new Random().nextInt(Table.MAX_CAPACITY) + 1)
                .mapToObj(x -> new Client())
                .collect(Collectors.toList());
    }
    
    /**
     * 
     * @return Any Waiter whose state is available, otherwise null
     */
    public Waiter getAvailableWaiter(){
       return waiters.stream()
                .filter(w -> w.getState().equals(WaiterState.AVAILABLE))
                .findAny().orElse(null);
    }

    /**
     *
     * @return The group of clients (diners) who are at the top of the line
     */
    public List<Client> attendClients() {
        return clientsRow.poll();
    }

    public void setUp() {
        generateMenu();
        //Generate 14 tables
        tables.addAll(IntStream.range(0, 14).mapToObj(x -> new Table())
                .collect(Collectors.toList()));

        generateWaiters();
    }

    /**
     *
     * @return First available table, otherwise, null
     */
    public Table getAvailableTable() {
        return tables.stream()
                .filter(t -> t.isAvailable())
                .findFirst()
                .orElse(null);
    }

    public void generateMenu() {

//        //--------------------- MAIN DISHES --------------------------
//        MENU.add(new Dish("Greek Chicken Pasta", DishType.MAIN, 15.10, 35));
//        MENU.add(new Dish("Tuscan Bean Soup", DishType.MAIN, 15.10, 20));
//        MENU.add(new Dish("Yakisoba Noodles", DishType.MAIN, 15.10, 15));
//        MENU.add(new Dish("6-Ingredient Vegan Chickpea Curry", DishType.MAIN, 0, 30));
//        MENU.add(new Dish("Russian Beef Stroganoff", DishType.MAIN, 0, 30));
//
//        //--------------------- ENTREÉS --------------------------
//        MENU.add(new Dish("Scallops with sweet corn puree, prosciutto and lemon butter", DishType.ENTREE, 0, 10));
//        MENU.add(new Dish("Goat's cheese pissaladiere tarts", DishType.ENTREE, 0, 5));
//        MENU.add(new Dish("Antipasto garlic bread", DishType.ENTREE, 0, 5));
//        MENU.add(new Dish("Crisp-fried cheese ravioli", DishType.ENTREE, 0, 8));
//        MENU.add(new Dish("Crab cakes with dill mayonnaise", DishType.ENTREE, 0, 10));
//
//        //--------------------- DESSERTS --------------------------
//        MENU.add(new Dish("OREO Cookie Split", DishType.DESSERT, 0, 5));
//        MENU.add(new Dish("Creamy Mochaccino Mousse", DishType.DESSERT, 0, 5));
//        MENU.add(new Dish("Peach Melba Icy Delight", DishType.DESSERT, 0, 8));
//        MENU.add(new Dish("Old Fashioned Sour Cream Doughnuts", DishType.DESSERT, 0, 10));
//        MENU.add(new Dish("Meyer Lemon Bars", DishType.DESSERT, 0, 14));
//        MENU.add(new Dish("Pavlova With Blueberry Jam", DishType.DESSERT, 0, 10));
//        MENU.add(new Dish("Banana Pudding Parfaits", DishType.DESSERT, 0, 8));
//        MENU.add(new Dish("Strawberry, Currant And Mint Tart With Mascarpone", DishType.DESSERT, 0, 10));
//        MENU.add(new Dish("Green Tea Panna Cotta", DishType.DESSERT, 0, 15));
//        MENU.add(new Dish("Strawberry Mousse", DishType.DESSERT, 0, 10));
    }

    public void generateWaiters() {
        waiters.addAll(Arrays.asList(
                new Waiter(0, "José"),
                new Waiter(1, "Juan")
        ));
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
        ArrayList<Dish> mainDishes = getDishesByType(DishType.MAIN);
        Dish mainDish = mainDishes.get(new Random().nextInt(mainDishes.size()));
        personalOrderDishes.add(mainDish);

        boolean entreeAndDessert = Math.random() < 0.5;

        ArrayList<Dish> entreeDishes = getDishesByType(DishType.ENTREE);
        ArrayList<Dish> dessertDishes = getDishesByType(DishType.DESSERT);

        /**
         * Complete menu with one of these two options
         *
         * 1. One entreé and up to two desserts 
         * 2. One entreé or up to two desserts
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
     * @param dishType Type of dish to filter from menu
     * @return
     */
    public static ArrayList<Dish> getDishesByType(DishType dishType) {
        return (ArrayList<Dish>) MENU.stream()
                .filter(d -> d.getDishType().equals(dishType))
                .collect(Collectors.toList());
    }

    public List<Dish> getDishes() {
        return MENU;
    }

    public ArrayList<Table> getTables() {
        return tables;
    }

    public void setTables(ArrayList<Table> tables) {
        this.tables = tables;
    }

    public List<Dish> getMenu() {
        return MENU;
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

    public Kitchen getKitchen() {
        return kitchen;
    }

    public void setKitchen(Kitchen kitchen) {
        this.kitchen = kitchen;
    }

    public List<Waiter> getWaiters() {
        return waiters;
    }

    public void setWaiters(List<Waiter> waiters) {
        this.waiters = waiters;
    }

    public Map<Integer, List<Order>> getOrdersPerDay() {
        return ordersPerDay;
    }

    public void setOrdersPerDay(Map<Integer, List<Order>> ordersPerDay) {
        this.ordersPerDay = ordersPerDay;
    }

    public Queue<List<Client>> getClientsRow() {
        return clientsRow;
    }

    public void setClientsRow(Queue<List<Client>> clientsRow) {
        this.clientsRow = clientsRow;
    }
    
    
}
