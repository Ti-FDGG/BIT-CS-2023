package com.jinxuliang.javafxthreadcomponent.tasks;

import javafx.concurrent.Task;

import java.util.concurrent.TimeUnit;

public class MyTask extends Task<Integer> {
    @Override
    protected Integer call() throws Exception {
        for (int i = 0; i < 100; i++) {
            Thread.sleep(200);
            if (i % 5 == 0)
                updateProgress(i, 100);
        }
        return 100;
    }
}
