package com.jinxuliang.javafxthreadcomponent.tasks;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class MyServiceTask extends Task<ObservableList<Long>> {
    @Override
    protected ObservableList<Long> call() throws Exception {
        ObservableList nums = FXCollections.observableArrayList();
        for (int i = 0; i < 100; i++) {
            Thread.sleep(100);
            if (i % 10 == 0) {
                nums.add(i);
                updateMessage("加入数值：" + i);
            }
        }
        return nums;
    }
}
