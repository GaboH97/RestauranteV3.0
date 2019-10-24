package models.entities;

import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class Group {

    private ArrayList<Client> clients;

    public Group() {
        this.clients = new ArrayList<>();
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }
}
