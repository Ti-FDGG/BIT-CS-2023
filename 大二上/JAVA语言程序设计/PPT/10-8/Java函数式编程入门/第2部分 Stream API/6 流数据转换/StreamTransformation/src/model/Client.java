package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Client {
    private int Id;
    private String Name;
    private String Address;

    public Client(int id, String name) {
        Id = id;
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getId() {
        return Id;
    }

    @Override
    public String toString() {
        return "Client{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Address='" + Address + '\'' +
                '}';
    }

    public static Client createExampleClient() {
        Random ran = new Random();
        var ranValue = ran.nextInt(100);
        var client= new Client(ranValue, "Client" + ranValue);
        client.setAddress("Address of "+client.Name);
        return client;
    }

    public static List<Client> createExampleClients(int count) {
        var clients = new ArrayList<Client>();
        for (int i = 0; i < count; i++) {
            clients.add(createExampleClient());
        }
        return clients;
    }
}
