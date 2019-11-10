package models.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author Gabriel Huertas <gabriel970826@gmail.com>
 */
public final class Restaurant {

    //================= ATTRIBUTES ==========================
    private TimeConstants time;

    public static List<Dish> MENU = Arrays.asList(
            //--------------------- MAIN DISHES --------------------------
            new Dish("Greek Chicken Pasta", DishType.MAIN, 25, 35),
            new Dish("Tuscan Bean Soup", DishType.MAIN, 20, 20),
            new Dish("Yakisoba Noodles", DishType.MAIN, 12, 15),
            new Dish("6-Ingredient Vegan Chickpea Curry", DishType.MAIN, 18, 30),
            new Dish("Russian Beef Stroganoff", DishType.MAIN, 15, 30),
            //--------------------- ENTREÉS --------------------------
            new Dish("Scallops with sweet corn puree, prosciutto and lemon butter", DishType.ENTREE, 5, 10),
            new Dish("Goat's cheese pissaladiere tarts", DishType.ENTREE, 5, 5),
            new Dish("Antipasto garlic bread", DishType.ENTREE, 3.5, 5),
            new Dish("Crisp-fried cheese ravioli", DishType.ENTREE, 10, 8),
            new Dish("Crab cakes with dill mayonnaise", DishType.ENTREE, 8, 10),
            //--------------------- DESSERTS --------------------------
            new Dish("OREO Cookie Split", DishType.DESSERT, 1.5, 5),
            new Dish("Creamy Mochaccino Mousse", DishType.DESSERT, 2.5, 5),
            new Dish("Peach Melba Icy Delight", DishType.DESSERT, 3, 8),
            new Dish("Old Fashioned Sour Cream Doughnuts", DishType.DESSERT, 3.5, 10),
            new Dish("Meyer Lemon Bars", DishType.DESSERT, 6, 14),
            new Dish("Pavlova With Blueberry Jam", DishType.DESSERT, 3, 10),
            new Dish("Banana Pudding Parfaits", DishType.DESSERT, 2, 8),
            new Dish("Strawberry, Currant And Mint Tart With Mascarpone", DishType.DESSERT, 4, 10),
            new Dish("Green Tea Panna Cotta", DishType.DESSERT, 2.5, 15),
            new Dish("Strawberry Mousse", DishType.DESSERT, 5.5, 10)
    );

    private ArrayList<Table> tables;
    private Kitchen kitchen;
    private List<Waiter> waiters;
    private List<Order> orders;
    private Cashier cashier;
    private Cash cash;

    /**
     * Clients row waiting to be attended
     */
    private Queue<List<Client>> clientsRow;

    /**
     * Singleton instance
     */
    private static Restaurant instance;

    private Restaurant() {

        time = new TimeConstants();

        this.kitchen = new Kitchen();
        this.orders = new ArrayList<>();
        this.cashier = new Cashier();
        this.cash = new Cash(cashier);

        this.tables = new ArrayList<>();
        this.waiters = new ArrayList<>();

        this.clientsRow = new LinkedList<>();

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

        //MIRA QUE EL RELOJ DE SISTEMA PARA UN DÍA SEA MENOR A LA DURACIÓN
        //LA JORNADA LABORAL
        int groups = 14;
        int tablesOccupied = 0;
        while (tablesOccupied < groups) {
            int timeBetweenArrivals = new Random().ints(15, 20).findFirst().getAsInt();
            List<Client> group = clientsArrival();

            time.advance(timeBetweenArrivals);
            System.out.println("Grupo llegó en el instante " + time.getDayCounter());

            Waiter waiter = getAvailableWaiter();

            Table table = getAvailableTable();
            table.occupy();
            Order order = new Order(table);
            order.createPersonalOrders(group);
            waiter.takeOrder(order);
            order.finish(waiter);
            tablesOccupied++;

        }
        waiters.forEach(w -> w.getTakenOrders().forEach(orders::add));
        System.out.println("orders " + orders.size());
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
    public Waiter getAvailableWaiter() {
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
         * 1. One entreé and up to two desserts 2. One entreé or up to two
         * desserts
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

    public boolean hasToWaitInQueue() {
        return getAvailableTable() == null;
    }

    public Map<Dish, Long> getOrderedDishesWithQuantity() {
        return getAllDishes().stream()
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting())
                );
        //return orders.stream().collect(Collectors.groupingBy(o -> o,Collectors.counting()));
    }

    /**
     * @return All ordered dishes for this workday
     */
    public List<Dish> getAllDishes() {
        List<Dish> allOrderedDishes = new ArrayList<>();
        this.orders.forEach((order) -> {
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

    public Map<Dish, Double> getBestRatedDishesPerDishType() {
        Map<Dish, Double> bestSellingDishesPerDishType = new HashMap<>();
        Map<Dish, Double> dishesSold = getDishesWithAverageRate();

        //Get Dish Type values
        List<DishType> dishTypes = Arrays.asList(DishType.values());

        dishTypes.forEach((DishType dishType) -> {

            //Filter Map entries by dish type
            Map<Dish, Double> dishesPerType = dishesSold.entrySet()
                    .stream()
                    .filter((Map.Entry<Dish, Double> e) -> e.getKey().getDishType().equals(dishType))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            //Get best selling dish
            Map.Entry<Dish, Double> max = Collections.max(dishesPerType.entrySet(), Map.Entry.comparingByValue());

            bestSellingDishesPerDishType.put(max.getKey(), max.getValue());

        });

        return bestSellingDishesPerDishType;

    }

    public Map<Dish, Double> getDishesWithAverageRate() {
        List<OrderedDish> allOrderedDishes = getAllOrderedDishes();
        Map<Dish, Double> dishesWithAverageRate = allOrderedDishes
                .stream()
                .collect(
                        Collectors.groupingBy(
                                o -> o.getDish(),
                                Collectors.averagingDouble(OrderedDish::getRating)));
        return dishesWithAverageRate;
    }

    public List<OrderedDish> getAllOrderedDishes() {
        List<OrderedDish> orderedDishes = new ArrayList();
        orders.forEach((order) -> {
            order.getPersonalOrders().forEach((personalOrder) -> {
                orderedDishes.addAll(personalOrder.getOrderedDishes());
            });
        });
        return orderedDishes;
    }

    /**
     *
     * @return Best rated Waiter
     */
    public Waiter getBestRatedWaiter() {
        return Collections.max(waiters, Comparator.comparingDouble(Waiter::getAverageRate));
    }
    
    /**
     * 
     * @return Number of orders grouped by payment strategy
     */
    public Map<PaymentStrategy, Long> getCountOfOrdersByPaymentStrategy() {
        return orders.stream().collect(Collectors.groupingBy(o -> o.getPaymentStrategy(), Collectors.counting()));
    }

    //==================== GETTERS & SETTERS ======================
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

    public Queue<List<Client>> getClientsRow() {
        return clientsRow;
    }

    public void setClientsRow(Queue<List<Client>> clientsRow) {
        this.clientsRow = clientsRow;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
