package com.jinxuliang.hellojavafxapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

//JavaFX程序派生自Application
public class HelloJavaFXApp extends Application {
    //程序运行时，JavaFX框架会创建一个Stage对象，注入到start()方法中
    public void start(Stage stage) {
        //实例化一个JavaFX标签控件
        Label message = new Label("Hello, JavaFX!");
        //设定内容与边界之间的距离
        message.setPadding(new Insets(10));
        //设定文字对齐方式
        message.setAlignment(Pos.CENTER);
        //设定字体大小
        message.setFont(new Font(30));
        //创建一个场景，其中包容上面创建的标签控件对象
        Scene scene = new Scene(message,300,150);
        //将创建好的场景对象设置为“舞台（即窗体）”的当前场景
        stage.setScene(scene);
        //指定窗体标题
        stage.setTitle("学习JavaFX编程模型");
        //显示窗体
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}