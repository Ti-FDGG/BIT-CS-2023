package com.jinxuliang.javafxthreadcomponent.tasks;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;

public class MyScheduleService extends ScheduledService<Integer> {
    @Override
    protected Task<Integer> createTask() {
        return new MyScheduledTask();
    }
}
