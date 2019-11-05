package models.entities;

/**
 *
 * @author Lenovo
 */
public enum CookingSkill {

    ENTREE(1, "Entreé"),
    MAIN(2, "Main Course"),
    DESSERT(3, "Dessert");

    private int id;
    private String name;

    private CookingSkill(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return name;
    }
    
    
}
