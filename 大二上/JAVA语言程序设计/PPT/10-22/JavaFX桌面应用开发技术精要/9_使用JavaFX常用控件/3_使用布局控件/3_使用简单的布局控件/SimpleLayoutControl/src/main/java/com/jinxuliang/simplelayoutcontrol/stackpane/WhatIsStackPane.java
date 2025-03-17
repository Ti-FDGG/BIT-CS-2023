package com.jinxuliang.simplelayoutcontrol.stackpane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class WhatIsStackPane extends Application {
    @Override
    public void start(Stage stage)  {
        //构建StackPane，其参数的顺序决定了控件显示的顺序
        //后来者居上
        StackPane pane = new StackPane(
                new Rectangle(200, 100, Color.LIGHTSKYBLUE),
                new Circle(40, Color.RED),
                new Button("Hello StackPane")
        );

        stage.setScene(new Scene(pane, 300, 300));
        stage.setTitle("什么是StackPane");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
