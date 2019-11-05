package models.entities;

/**
 *
 * @author Gabriel Huertas <gabriel970826@gmail.com>
 */
public enum WaiterState {
    
    ATTENDING_TABLE, // Waiter is taking the order
    IN_KITCHEN, // Waiter is either leaving an order or picking up the dishes
    CLEANING, // Waiter is cleaning a table
    AVAILABLE, //Waiter is available to do any action
    RESTING; // Waiter is taking a rest (as specified in the docs)
}
