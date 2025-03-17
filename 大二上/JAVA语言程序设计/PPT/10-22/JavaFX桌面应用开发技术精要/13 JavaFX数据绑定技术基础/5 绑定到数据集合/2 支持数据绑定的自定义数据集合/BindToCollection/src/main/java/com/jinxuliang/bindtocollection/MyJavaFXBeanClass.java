package com.jinxuliang.bindtocollection;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Callback;

import java.util.concurrent.ThreadLocalRandom;

//一个属性全部为JavaFX Bean Property的示例数据类
public class MyJavaFXBeanClass {
    public MyJavaFXBeanClass(int num, String info) {
        this.num.set(num);
        this.info.set(info);
    }

    private IntegerProperty num = new SimpleIntegerProperty();
    private StringProperty info = new SimpleStringProperty();

    public int getNum() {
        return num.get();
    }

    public IntegerProperty numProperty() {
        return num;
    }

    public void setNum(int num) {
        this.num.set(num);
    }

    public String getInfo() {
        return info.get();
    }

    public StringProperty infoProperty() {
        return info;
    }

    public void setInfo(String info) {
        this.info.set(info);
    }

    @Override
    public String toString() {
        return "MyClass{" +
                "num=" + num +
                ", info=" + info +
                '}';
    }


}
