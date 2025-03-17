package com.jinxuliang.useobservablecollection;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UseInvalidationListener {
    public static void main(String[] args) {
        // 创建一个集合对象
        ObservableList<String> list =
                FXCollections.observableArrayList("one", "two");
        //给集合添加一个监听器
        list.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                System.out.println("集合元素有变化："+observable);
            }
        });
        list.add("three");
        list.addAll("four", "five");
        list.remove(0,2);
        list.set(0, "new value");
    }
}
