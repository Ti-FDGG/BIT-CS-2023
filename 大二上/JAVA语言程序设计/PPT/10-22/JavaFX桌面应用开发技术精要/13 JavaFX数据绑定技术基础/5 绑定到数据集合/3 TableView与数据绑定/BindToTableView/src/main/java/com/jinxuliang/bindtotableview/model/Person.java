package com.jinxuliang.bindtotableview.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Person {
    private String name;
    private IntegerProperty age;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person [Name=" + name + ", age=" + age + "]";
    }


    public final IntegerProperty ageProperty() {
        if (age == null) {
            age = new SimpleIntegerProperty();
        }
        return age;
    }

    public int getAge() {
        return ageProperty().get();
    }

    public void setAge(int age) {
        ageProperty().set(age);
        ;
    }

    public Person(String name, int age) {
        super();
        this.name = name;
        ageProperty().set(age);
    }


}
