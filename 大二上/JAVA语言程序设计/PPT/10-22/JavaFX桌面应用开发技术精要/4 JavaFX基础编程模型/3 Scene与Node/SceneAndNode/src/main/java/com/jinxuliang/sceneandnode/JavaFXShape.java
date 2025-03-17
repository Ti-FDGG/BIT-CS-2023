package com.jinxuliang.sceneandnode;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class JavaFXShape extends Application {
    @Override
    public void start(Stage primaryStage) {
        //Group是一个控件容器
        Group root = new Group();
        //将容器对象加入到场景中
        Scene scene = new Scene(root,400,400, Color.GRAY);
        //构建一个圆对象
        Circle cir = new Circle(200,200,50);
        cir.setFill(Color.CORAL);
        //让圆对象响应鼠标点击事件，每点击一次，半径加5
        cir.setOnMouseClicked(e->cir.setRadius(cir.getRadius()+5));
        //将圆对象加入到容器对象中
        root.getChildren().add(cir);
        //设置“舞台”，并显示在屏幕上
        primaryStage.setTitle("JavaFX图形对象示例");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
