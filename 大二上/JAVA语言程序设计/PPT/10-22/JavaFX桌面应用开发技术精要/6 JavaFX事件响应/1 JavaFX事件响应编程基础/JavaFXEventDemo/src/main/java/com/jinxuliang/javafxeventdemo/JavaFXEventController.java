package com.jinxuliang.javafxeventdemo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class JavaFXEventController
        implements Initializable {
    @FXML
    Label infoLabel;
    @FXML
    Button traditionalButton;
    @FXML
    Button lambdaButton;

    @FXML
    public void GetRandomNumber(ActionEvent event) {
        Random random = new Random();
        String info = "生成的随机数：" + random.nextInt(1000);
        System.out.println(info);
        infoLabel.setText(info);
    }

    //此方法为控件挂接Click事件响应代码
    public void initButtonActionListener() {
        //传统的事件响应方式（Java 8以前版本使用）
        traditionalButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GetRandomNumber(event);
            }
        });
        //Java 8以上版本，可以使用Lambda表达式编写事件响应代码
        lambdaButton.setOnAction(this::GetRandomNumber);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initButtonActionListener();
    }
}