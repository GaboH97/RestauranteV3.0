package models.entities;

/**
 *
 * @author Gabriel Huertas <gabriel970826@gmail.com>
 */
public enum OrderedDishState {
    
    	ORDERED, //Order has been placed
        PREPARING, //Chef is cooking the ordered dish
        PREPARED, //Chef has finised cooking the dish
        EATING, //Client is eating the ordered dish
        CONSUMED; //Client has finished eating the ordered dish

}
