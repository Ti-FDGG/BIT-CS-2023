package com.jinxuliang.progressdemo;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class MyNumber {

    private DoubleProperty number;

    public final DoubleProperty numberProperty() {
        if (number == null) {
            number = new SimpleDoubleProperty(0);
        }
        return number;
    }


    public double getNumber() {
        return numberProperty().get();
    }

    public void setNumber(double number) {
        numberProperty().set(number);
    }


}
