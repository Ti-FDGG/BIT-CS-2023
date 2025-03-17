package com.jinxuliang.useobservablecollection;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class ListPropertyBindToStringProperty {
    public static void main(String[] args) {
        ListProperty<String> lp =
                new SimpleListProperty<>(FXCollections.observableArrayList());
        StringProperty initStr = new SimpleStringProperty("大小: ");
        StringProperty desc = new SimpleStringProperty();
        desc.bind(initStr.concat(lp.sizeProperty())
                .concat(", 为空？ ")
                .concat(lp.emptyProperty())
                .concat(", 内容: ")
                .concat(lp.asString()));
        System.out.println("在添加元素前: " + desc.get());
        lp.addAll("张三", "李四");
        System.out.println("在添加元素后: " + desc.get());
    }
}
