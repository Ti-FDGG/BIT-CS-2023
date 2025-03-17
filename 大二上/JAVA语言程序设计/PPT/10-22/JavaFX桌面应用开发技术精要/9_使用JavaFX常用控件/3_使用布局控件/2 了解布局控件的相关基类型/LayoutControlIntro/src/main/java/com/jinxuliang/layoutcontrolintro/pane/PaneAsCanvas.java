package com.jinxuliang.layoutcontrolintro.pane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PaneAsCanvas extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane canvas = new Pane();
        //使用样式设置Pane背景是最方便的方法
        canvas.setStyle("-fx-background-color: #000000;");
        //设置绘图尺寸
        canvas.setPrefSize(300, 200);
        //添加两个图形：圆和矩形，并且为其定位
        Circle circle = new Circle(50, Color.BLUE);
        circle.relocate(70, 20);
        Rectangle rectangle = new Rectangle(100, 100, Color.RED);
        rectangle.relocate(120, 70);
        //将所有图形，添加到面板中
        canvas.getChildren().addAll(circle, rectangle);

        Scene scene=new Scene(canvas);
        primaryStage.setScene(scene);
        primaryStage.setTitle("作为画布的Canvas");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
