package com.jinxuliang.useobservablecollection;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

public class UseListProperty {

    public static void main(String[] args) {
        System.out.println("实例化两个集合属性对象：lp1和lp2");
        ListProperty<String> lp1 =
                new SimpleListProperty<>(FXCollections.observableArrayList());
        ListProperty<String> lp2 =
                new SimpleListProperty<>(FXCollections.observableArrayList());
        System.out.println("在两个ListProperty（lp1和lp2）间建立单向绑定关系");
        lp1.bindContent(lp2);
        print("初始状态:", lp1, lp2);
        lp2.addAll("1", "2");
        print("lp2加入两个元素后:", lp1, lp2);
        System.out.println("取消单向绑定后，再建立双向绑定关系");
        lp1.unbindContent(lp2);
        lp1.bindContentBidirectional(lp2);
        //在左端集合添加元素
        lp1.addAll("3", "4");
        print("lp1添加完元素后:", lp1, lp2);
        //在右端集合添加元素
        lp2.addAll("5", "6");
        print("lp2添加完元素后:", lp1, lp2);
    }

    public static void print(String msg, ListProperty<String> lp1,
                             ListProperty<String> lp2) {
        System.out.println(msg + " lp1: " + lp1.get() +
                ", lp2: " + lp2.get());
    }
}
