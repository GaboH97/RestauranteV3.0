package models.entities;

/**
 *
 * @author Lenovo
 */
public class Dish {

    private String name;
    private DishType dishType;
    private double price;
    private double cookingTime; //In minutes

    public Dish(String name, DishType dishType, double price, double cookingTime) {
        this.name = name;
        this.dishType = dishType;
        this.price = price;
        this.cookingTime = cookingTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DishType getDishType() {
        return dishType;
    }

    public void setDishType(DishType dishType) {
        this.dishType = dishType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(double cookingTime) {
        this.cookingTime = cookingTime;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Name: ").append(name).append(System.getProperty("line.separator"))
                .append("Dish Type: ").append(dishType).append(System.getProperty("line.separator"))
                .append("Cooking Time: ").append(cookingTime).append(System.getProperty("line.separator"))
                .append("Price: ").append(price).append(System.getProperty("line.separator"));

        return builder.toString();
    }

}
