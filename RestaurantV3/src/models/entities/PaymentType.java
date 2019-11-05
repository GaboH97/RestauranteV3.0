package models.entities;

/**
 * 
 * @author Gabriel Huertas <gabriel970826@gmail.com>
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
