package com.jinxuliang.usestage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class CenterScreen extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("一个显示在屏幕中央的Stage");
        Button button = new Button("Hello");
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(button);
        Scene scene = new Scene(root, 300, 200);
        stage.setScene(scene);
        //在窗体显示之前，无法读取其长宽尺寸信息，所以以下这句不起作用，
        //无法将窗体正好移到屏幕中央
        //moveStageToCenter(stage);
        stage.show();
        //在屏幕中央显示窗体
        moveStageToCenter(stage);
    }

    private static void moveStageToCenter(Stage stage) {
        //获取当前主屏幕的尺寸信息
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        //只有在窗体显示之后，才能获取它的尺寸
        double x = bounds.getMinX() + (bounds.getWidth() - stage.getWidth()) / 2.0;
        double y = bounds.getMinY() + (bounds.getHeight() - stage.getHeight()) / 2.0;
        //设置窗体的左上角坐标
        stage.setX(x);
        stage.setY(y);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
