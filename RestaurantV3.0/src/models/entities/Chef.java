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
    private ArrayList<CookingSkill> cookingSkills;
    private double successRate;

    public Chef(int id, String name, double successRate) {
        this.id = id;
        this.name = name;
        this.cookingSkills = new ArrayList<>();
        this.successRate = successRate;
    }
    
    public boolean hasSkill(CookingSkill cookingSkill){
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

    public ArrayList<CookingSkill> getCookingSkills() {
        return cookingSkills;
    }

    public void setCookingSkills(ArrayList<CookingSkill> cookingSkills) {
        this.cookingSkills = cookingSkills;
    }

    public double getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(double successRate) {
        this.successRate = successRate;
    }
}
