package models.entities;

import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class Chef {

    public static final int REST_DURATION = 20; //In minutes

    private int id;
    private String name;
    private ArrayList<DishType> cookingSkills;
    private double successRate;

    public Chef(int id, String name, double successRate) {
        this.id = id;
        this.name = name;
        this.cookingSkills = new ArrayList<>();
        this.successRate = successRate;
    }

    public void Cook(OrderedDish orderedDish) {
        orderedDish.setOrderedDishState(OrderedDishState.PREPARING);
    }

    public boolean hasSkill(DishType cookingSkill) {
        return cookingSkills.contains(cookingSkill);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<DishType> getCookingSkills() {
        return cookingSkills;
    }

    public void setCookingSkills(ArrayList<DishType> cookingSkills) {
        this.cookingSkills = cookingSkills;
    }

    public double getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(double successRate) {
        this.successRate = successRate;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Name ").append(name).append(System.getProperty("line.separator"))
                .append("Cooking skills: ").append(System.getProperty("line.separator"));
        cookingSkills.forEach(cs -> builder.append("\t").append(cs).append(System.getProperty("line.separator")));
        builder.append("Success rate: ").append(successRate).append(System.getProperty("line.separator"));
        return builder.toString();
    }

}
