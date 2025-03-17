package com.jinxuliang.concurencyinjavafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFXLifecycleDemo extends Application {
    public JavaFXLifecycleDemo() {
        var threadName = Thread.currentThread().getName();
        System.out.println(threadName + "创建JavaFX Application实例：");
    }

    @Override
    public void init() {
        var threadName = Thread.currentThread().getName();
        System.out.println(threadName + " 调用init()方法。");
    }

    @Override
    public void stop() {
        var threadName = Thread.currentThread().getName();
        System.out.println(threadName + " 调用stop()方法。");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        var threadName = Thread.currentThread().getName();
        System.out.println(threadName + " 调用start()方法");

        Button exitBtn = new Button("退出");
        exitBtn.setOnAction(e -> Platform.exit());

        VBox vBox = new VBox(exitBtn);
        vBox.setPadding(new Insets(10));
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX应用的生命周期");
        primaryStage.show();
    }
}
