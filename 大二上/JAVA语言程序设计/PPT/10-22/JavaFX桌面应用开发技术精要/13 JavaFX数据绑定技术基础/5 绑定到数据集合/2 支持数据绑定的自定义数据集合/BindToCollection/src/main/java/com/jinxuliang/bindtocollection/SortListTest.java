package com.jinxuliang.bindtocollection;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

import java.time.LocalTime;

public class SortListTest {
    public static void main(String[] args) {
        //创建一个“可观察”的对象集合，并且指定类中的info属性值变化也需要被监听
        ObservableList<MyJavaFXBeanClass> dataList =
                FXCollections.observableArrayList(MyTestHelper.myJavaFXBeanClassExtractor);
        System.out.println("\n向集合中添加3个元素\n");
        for (int i = 0; i < 3; i++) {
            dataList.add(MyTestHelper.createExampleObj());
        }
        //输出原始集合内容
        dataList.forEach(System.out::println);


        //指定按照info属性值（是一个字符串）的长度进行排序
        var sorted = new SortedList<MyJavaFXBeanClass>(dataList, (o1, o2) -> {
            var len1 = Integer.valueOf(o1.getInfo().length());
            var len2 = Integer.valueOf(o2.getInfo().length());
            return len1.compareTo(len2);
        });
        System.out.println("\n排序之后\n");
        sorted.forEach(System.out::println);

        System.out.println("\n修改第一个对象元素的info属性值\n");
        System.out.println(dataList.get(0));
        dataList.get(0).setInfo("Modifed @ " + LocalTime.now());
        System.out.println("改完了,此对象当前内容为：");
        System.out.println(dataList.get(0));
        System.out.println("\n再次输出排序集合的内容，看看是否自动排序……\n");
        sorted.forEach(System.out::println);

    }
}
