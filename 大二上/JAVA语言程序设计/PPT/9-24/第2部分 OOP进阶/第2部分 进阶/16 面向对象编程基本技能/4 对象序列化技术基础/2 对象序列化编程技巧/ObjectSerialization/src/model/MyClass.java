package model;

import java.io.Serializable;

public class MyClass implements Serializable {

    public MyClass(int value) {

        this.intValue = value;
    }

    public int intValue;

    @Override
    public String toString() {
        return "MyClass{" +
                "intValue=" + intValue +
                '}';
    }
}
