package com.jinxuliang.sceneandnode;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class DataBind extends Application {
    @Override
    public void start(Stage primaryStage) {
        //创建两个标签对象
        Label info = new Label("Label字体大小绑定到Slider的值");
        info.setFont(new Font(20));
        Label message = new Label("Hello, JavaFX!");
        message.setFont(new Font(50));
        //设置Slider控件的相关属性
        Slider slider = new Slider();
        slider.setMax(50);
        slider.setMin(10);
        slider.setValue(50);

        //在Slider对象和Lable对象之间构建数据绑定关系
        slider.valueProperty().addListener(property
                -> message.setFont(new Font(slider.getValue())));

        //构建场景图
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(info, slider, message);

        //显示Stage
        Scene scene = new Scene(root, 400, 200);
        primaryStage.setTitle("Node对象之间的数据绑定");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
