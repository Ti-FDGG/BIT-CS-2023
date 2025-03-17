package com.jinxuliang.useobservablecollection;

import javafx.collections.*;

public class TestMapChangeListener {
    public static void main(String[] args) {
        //创建可观察的Map集合
        ObservableMap<String, Integer> map =
                FXCollections.observableHashMap();
        //添加MapChangeListener，跟踪集合中元素的变化
        map.addListener(
                (MapChangeListener<String, Integer>) change
                        -> System.out.println("发生了变化：" + change));
        //添加两个元素
        map.put("one", 1);
        map.put("two", 2);
        //修改现有的元素
        map.put("one", map.get("one") * 100);
    }

}
