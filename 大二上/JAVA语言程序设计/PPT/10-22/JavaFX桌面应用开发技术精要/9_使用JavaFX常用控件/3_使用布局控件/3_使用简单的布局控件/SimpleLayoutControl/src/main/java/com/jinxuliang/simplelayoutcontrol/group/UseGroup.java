package com.jinxuliang.simplelayoutcontrol.group;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UseGroup extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("使用Group");

        Button button1 = new Button("一个按钮");
        Button button2 = new Button("按钮");
        Group group = new Group();
        group.getChildren().add(button1);
        group.getChildren().add(button2);

        Scene scene = new Scene(group, 230, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}