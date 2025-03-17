package com.jinxuliang.javafxdatabindingdemo;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class AlwaysCenter extends Application {
    @Override
    public void start(Stage primaryStage) {
        Circle circle = new Circle(100, 100, 100);
        circle.setFill(Color.BLUE);
        Pane pane = new Pane();
        pane.getChildren().add(circle);

        Scene scene = new Scene(pane, 300, 300);
        //将scene的width属性除以2，绑定到circle的圆心x坐标上
        //将scene的height属性除以2，绑定到circle的圆心y坐标上
        //让圆始终居于窗体中央
        circle.centerXProperty().bind(
                Bindings.divide(scene.widthProperty(), 2));
        circle.centerYProperty().bind(
                Bindings.divide(scene.heightProperty(), 2));

        primaryStage.setScene(scene);
        primaryStage.setTitle("我居中央！");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
