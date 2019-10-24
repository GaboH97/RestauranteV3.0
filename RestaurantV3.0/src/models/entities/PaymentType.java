package models.entities;

/**
 *
 * @author Lenovo
 */
public enum PaymentType {
    
    CC("Credit card"),
    C("Cash");
    
    private String name;

    PaymentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
