package models.entities;

/**
 *
 * @author Lenovo
 */
public class Client {

    private static int CLIENT_ID = 1;
    private int id;
    private String name;
    private String genre;

    public Client() {
        this.id = CLIENT_ID++;
        this.name = "Client " + id;
        this.genre = Math.random() > 0.5 ? "F" : "M";
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return this.name;
    }
    
    
}
