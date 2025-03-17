package com.jinxuliang.usingjavafxdatabind;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class MyNumber {
    private IntegerProperty number=new SimpleIntegerProperty(0);

    public int getNumber(){
        return number.get();
    }

    public void setNumber(int value){
        number.set(value);
    }

    public final IntegerProperty numberProperty(){
        return number;
    }
}
