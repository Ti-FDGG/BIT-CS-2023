package com.jinxuliang.javafxthreadcomponent;

import com.jinxuliang.javafxthreadcomponent.tasks.MyScheduleService;
import com.jinxuliang.javafxthreadcomponent.tasks.MyService;
import com.jinxuliang.javafxthreadcomponent.tasks.MyTask;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;

import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainController implements Initializable {
    @FXML
    private Button btnTask;
    @FXML
    private Button btnService;

    @FXML
    private Button btnScheduleService;

    @FXML
    private Label lblInfo;

    private MyScheduleService scheduleService = null;
    private MyService myService = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnTask.setOnAction(e -> {
            var task = createTask();
            runTaskUseThread(task);
            //runTaskUseThreadPool(task);

        });

        btnService.setOnAction(e -> {
            cancelAllTask();
            myService = new MyService();
            myService.start();
            myService.messageProperty().addListener(obj -> {
                lblInfo.setText(myService.getMessage());
            });
            myService.valueProperty().addListener(obj -> {
                lblInfo.setText("结果：" + myService.getValue().toString());
            });
        });

        btnScheduleService.setOnAction(e -> {
            cancelAllTask();
            scheduleService = new MyScheduleService();
            scheduleService.setDelay(Duration.seconds(3));
            scheduleService.setPeriod(Duration.seconds(1));
            scheduleService.start();
            scheduleService.lastValueProperty().addListener(obj -> {
                lblInfo.setText("定时任务：" + scheduleService.getLastValue());
            });

        });
    }

    private MyTask createTask() {
        MyTask task = new MyTask();
        task.progressProperty().addListener(obj -> {
            lblInfo.setText("已经完成" + (int) (task.getProgress() * 100) + "%");
        });
        task.valueProperty().addListener(obj -> {
            lblInfo.setText("结果为：" + task.getValue());
        });
        return task;
    }

    //使用线程池运行
    private static void runTaskUseThreadPool(MyTask task) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(task);
        executor.shutdown();
    }

    //使用后台线程运行
    private static void runTaskUseThread(MyTask task) {
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    private void cancelAllTask() {
        if (scheduleService != null) {
            scheduleService.cancel();
            lblInfo.setText("上次的定时任务己取消");
            scheduleService=null;
        }
        if (myService != null) {
            myService.cancel();
            lblInfo.setText("myService已经取消");
            myService=null;
        }
    }
}