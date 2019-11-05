package models.entities;

/**
 *
 * @author Gabriel Huertas
 */
public enum PaymentStrategy {
    
    /**
     * Each client pays for what he/she has consumed
     */
    AW("American Way"),
    /**
     * One client pays for everyone
     */
    OFE("One-For-Everyone"),
    /**
     * All clients payments are equally distributed
     */
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
