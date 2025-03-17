package com.jinxuliang.javafxthreadcomponent.tasks;

import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class MyService extends Service<ObservableList<Long>> {
    @Override
    protected Task<ObservableList<Long>> createTask() {
        return new MyServiceTask();
    }
}


