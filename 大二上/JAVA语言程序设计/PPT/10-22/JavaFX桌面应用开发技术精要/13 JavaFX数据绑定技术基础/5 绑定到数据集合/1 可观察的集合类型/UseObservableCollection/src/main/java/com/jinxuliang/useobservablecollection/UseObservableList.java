package com.jinxuliang.useobservablecollection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UseObservableList {
    public static void main(String[] args) {

        // 创建一个包容两个字符串的可观察集合对象
        ObservableList<String> list =
                FXCollections.observableArrayList("one", "two");
        System.out.println("List集合创建完毕: " + list);
        // 添加两个字符串
        list.addAll("three", "four");
        System.out.println("添加完两个元素后: " + list);
        //移除[1,3)范围内的元素
        list.remove(1, 3);
        System.out.println("移除了[1,3）范围内的元素后：" + list);
        // 保留唯一的一个元素
        list.retainAll("one");
        System.out.println("只留下\"one\"一个元素，其余全部移除：" + list);

        // 创建另外一个可观察的集合对象
        ObservableList<String> list2 =
                FXCollections.observableArrayList("1", "2", "3");
        // 清空原有内容，加入list2中的内容
        list.setAll(list2);
        System.out.println("清空List1,将List2元素全部加入: " + list);
        // 创建第3个集合
        ObservableList<String> list3 =
                FXCollections.observableArrayList("ten", "twenty", "thirty");

        System.out.println("list2 : " + list2);
        System.out.println("list3 : " + list3);
        // 合并list2和list3两个集合
        ObservableList<String> list4 = FXCollections.concat(list2, list3);
        System.out.println("合并list2和list3之后:" + list4);
    }
}
