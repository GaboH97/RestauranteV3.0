package models.entities;

/**
 *
 * @author Gabriel Huertas <gabriel970826@gmail.com>
 */
public class OrderedDish {
    
    private Dish dish;
    private Integer rating;
    private OrderedDishState orderedDishState;

    public OrderedDish(Dish dish) {
        this.dish = dish;
        this.rating = 0;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public OrderedDishState getOrderedDishState() {
        return orderedDishState;
    }

    public void setOrderedDishState(OrderedDishState orderedDishState) {
        this.orderedDishState = orderedDishState;
    }    
}
