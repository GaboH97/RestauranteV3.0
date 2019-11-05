/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.entities.Restaurant;

/**
 *
 * @author Gabriel Huertas <gabriel970826@gmail.com>
 */
public class Controller {
    
    private final Restaurant restaurant;

    public Controller() {
        this.restaurant = Restaurant.getInstance();
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}
