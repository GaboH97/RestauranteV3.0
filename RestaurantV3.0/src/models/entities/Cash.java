package models.entities;

/**
 *
 * @author Lenovo
 */
public class Cash {
    
    private Cashier cashier;
    private boolean available;

    public Cash(Cashier cashier, boolean available) {
        this.cashier = cashier;
        this.available = available;
    }

    public Cash(Cashier cashier) {
        this.cashier = cashier;
        this.available = true;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    
}
