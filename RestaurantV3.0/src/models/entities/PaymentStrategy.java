package models.entities;

/**
 *
 * @author Lenovo
 */
public enum PaymentStrategy {
    
    AW("American Way"),
    OFE("One-For-Everyone"),
    AFE("All-for-Everyone");
    
    private String name;

    PaymentStrategy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
