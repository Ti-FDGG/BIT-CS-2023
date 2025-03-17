package com.jinxuliang.runlaterapp;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button btnNewThread;

    @FXML
    private Label lblInfo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var threadName = Thread.currentThread().getName();
        lblInfo.setText("控制器的initialize():" + threadName);
        btnNewThread.setOnAction(e -> {
            newThread();
        });
    }

    private void newThread() {
        Runnable runnable = () -> {
            var threadName = Thread.currentThread().getName();
            Platform.runLater(() -> {
                String info="Runnable运行于" + threadName + "\nLabel被更新于："
                        + Thread.currentThread().getName();
                System.out.println(info);
                lblInfo.setText(info);
            });
        };
        new Thread(runnable).start();
    }
}