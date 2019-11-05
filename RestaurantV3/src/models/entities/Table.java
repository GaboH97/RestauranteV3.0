package models.entities;

/**
 *
 * @author Lenovo
 */
public class Table {
    
    public static final int MAX_CAPACITY = 5;
    public static final int CLEAN_DURATION = 5; //In minutes
    
    private static int TABLE_COUNT = 1;
    private int id;
    private boolean available;
    
    public Table() {
        this.id = TABLE_COUNT++;
        this.available = true;
    }
    
    public void occupy(){
        this.available = false;
    }
    
    public boolean isAvailable() {
        return available;
    }
    
    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TableID: ").append(id).append(System.getProperty("line.separator"))
                .append("Available?: ").append(available ? "Yes" : "No").append(System.getProperty("line.separator"));
        return builder.toString();
    }
    
}
