package com.jinxuliang.javafxthreadcomponent.tasks;

import javafx.concurrent.Task;

import java.util.concurrent.ThreadLocalRandom;

public class MyScheduledTask extends Task<Integer> {
    @Override
    protected Integer call() throws Exception {
        var returnValue = ThreadLocalRandom.current().nextInt();
        System.out.println(returnValue);
        return returnValue;
    }
}
