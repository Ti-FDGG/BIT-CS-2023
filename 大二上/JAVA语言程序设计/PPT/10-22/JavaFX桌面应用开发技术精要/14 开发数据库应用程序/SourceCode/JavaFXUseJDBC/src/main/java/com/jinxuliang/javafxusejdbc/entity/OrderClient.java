package com.jinxuliang.javafxusejdbc.entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OrderClient implements Cloneable {
    public OrderClient(int id, String name, String address) {
        this.id = id;
        setName(name);
        setAddress(address);
    }

    public OrderClient() {

    }

    private int id = 0;

    @Override
    public String toString() {
        return "OrderClient{" +
                "id=" + id +
                ", name=" + name.get() +
                ", address=" + address.get() +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private final StringProperty name = new SimpleStringProperty("");
    private final StringProperty address = new SimpleStringProperty("");

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    //克隆自己
    @Override
    public Object clone() throws CloneNotSupportedException {
        var newClient = new OrderClient(id, name.get(), address.get());
        return newClient;
    }
}
