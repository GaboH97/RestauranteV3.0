/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Gabriel Huertas <gabriel970826@gmail.com>
 */
public class Kitchen {

    private List<Chef> chefs;
    private Queue<Order> orders;

    public Kitchen() {
        chefs = new ArrayList<>();
        orders = new LinkedList<>();
        setUp();
    }

    /**
     * Method that adds orders to the pending orders list
     *
     * @param orders
     */
    public void addPendingOrders(LinkedList<Order> orders) {
        orders.forEach(o -> addOrderToPendingOrders(o));
    }

    public void addOrderToPendingOrders(Order order) {
        orders.add(order);
    }
    
    private void prepareOrder(){
        Order order = orders.poll();
    }

    public void setUp() {

        Chef chef1 = new Chef(0, "Jorge", 0.90);
        ArrayList<CookingSkill> cookingSkills = new ArrayList<>();
        cookingSkills.add(CookingSkill.ENTREE);
        cookingSkills.add(CookingSkill.MAIN);
        cookingSkills.add(CookingSkill.DESSERT);

        chef1.setCookingSkills(cookingSkills);
        chefs.add(chef1);

        cookingSkills = new ArrayList<>();

        Chef chef2 = new Chef(0, "Pedro", 0.88);
        cookingSkills.add(CookingSkill.ENTREE);
        cookingSkills.add(CookingSkill.MAIN);

        chef2.setCookingSkills(cookingSkills);
        chefs.add(chef2);
    }

    public List<Chef> getChefs() {
        return chefs;
    }

    public void setChefs(List<Chef> chefs) {
        this.chefs = chefs;
    }

    public Queue<Order> getOrders() {
        return orders;
    }

    public void setOrders(Queue<Order> orders) {
        this.orders = orders;
    }
}
