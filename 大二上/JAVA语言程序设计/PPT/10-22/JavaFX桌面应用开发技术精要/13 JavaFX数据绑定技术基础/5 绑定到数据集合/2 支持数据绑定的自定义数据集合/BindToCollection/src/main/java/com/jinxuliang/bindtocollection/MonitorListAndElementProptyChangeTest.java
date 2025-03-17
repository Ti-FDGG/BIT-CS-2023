package com.jinxuliang.bindtocollection;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.time.LocalTime;

//跟踪列表以及表中元素属性值的变化
public class MonitorListAndElementProptyChangeTest {
    public static void main(String[] args) {
        //创建一个“可观察”的对象集合，并且指定类中的info属性值变化也需要被监听
        ObservableList<MyJavaFXBeanClass> dataList =
                FXCollections.observableArrayList(MyTestHelper.myJavaFXBeanClassExtractor);
        //附加改变监听器
        dataList.addListener((ListChangeListener<MyJavaFXBeanClass>) change ->
                System.out.println("发生了变化：" + change));
        System.out.println("\n向集合中添加5个元素\n");
        for (int i = 0; i < 5; i++) {
            dataList.add(MyTestHelper.createExampleObj());
        }

        System.out.println("\n现在开始修改第一个元素的info属性\n");
        dataList.get(0).setInfo("Modify @" + LocalTime.now());
        System.out.println("\n现在开始修改第二个元素的num属性\n");
        dataList.get(0).setNum(1);
        System.out.println("\n现在开始修改第三个元素的info属性\n");
        dataList.get(2).setInfo("Modify @" + LocalTime.now());

    }
}
