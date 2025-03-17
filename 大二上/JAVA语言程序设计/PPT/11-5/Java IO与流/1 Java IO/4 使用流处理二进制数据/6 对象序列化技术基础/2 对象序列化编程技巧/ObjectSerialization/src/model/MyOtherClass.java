package model;

import java.io.Serializable;

public class MyOtherClass implements Serializable {

    public MyOtherClass(String value) {
        this.stringValue = value;
    }

    public String stringValue;

    @Override
    public String toString() {
        return "MyOtherClass{" +
                "stringValue='" + stringValue + '\'' +
                '}';
    }
}
