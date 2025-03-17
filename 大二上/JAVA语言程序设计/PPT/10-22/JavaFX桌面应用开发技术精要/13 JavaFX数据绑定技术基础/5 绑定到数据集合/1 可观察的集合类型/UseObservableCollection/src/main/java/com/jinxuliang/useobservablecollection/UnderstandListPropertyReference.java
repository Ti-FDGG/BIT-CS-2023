package com.jinxuliang.useobservablecollection;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

public class UnderstandListPropertyReference {
    public static void main(String[] args) {
        ListProperty<String> lp1 =
                new SimpleListProperty<>(
                        FXCollections.observableArrayList());
        ListProperty<String> lp2 =
                new SimpleListProperty<>(
                        FXCollections.observableArrayList());
        //看看lp1和lp2引用的是不是同一个集合对象
        System.out.println(lp1.get() == lp2.get()); //false
        lp1.bind(lp2);
        //看看lp1和lp2引用的是不是同一个集合对象
        System.out.println(lp1.get() == lp2.get()); //true
        lp1.addAll("One", "Two");
        System.out.println(lp2.get()); //[One, Two]
        lp2.set(FXCollections.observableArrayList("1", "2"));
        System.out.println(lp1.get());//[1, 2]
        //由于绑定的限制，以下这句抛出运行时异常：A bound value cannot be set.
        //lp1.set(FXCollections.observableArrayList("3", "4"));
        lp1.unbind();
        //解绑后，lp1和lp2仍然引用同一个集合对象
        System.out.println(lp1.get() == lp2.get()); //true
        //但现在，lp1可以重新被设置了
        lp1.set(FXCollections.observableArrayList("3", "4"));
    }
}
