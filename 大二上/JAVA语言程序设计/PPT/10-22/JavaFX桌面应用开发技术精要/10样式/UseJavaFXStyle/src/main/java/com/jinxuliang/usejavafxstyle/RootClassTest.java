package com.jinxuliang.usejavafxstyle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RootClassTest extends Application {
    @Override
    public void start(Stage stage) {
        Label nameLbl = new Label("姓名:");
        TextField nameTf = new TextField("");
        Button closeBtn = new Button("关闭");
        HBox root = new HBox();
        root.getChildren().addAll(nameLbl, nameTf, closeBtn);
        Scene scene = new Scene(root, 450, 120);
        var url = getClass().getResource("root.css").toExternalForm();
        scene.getStylesheets().add(url);
        stage.setScene(scene);
        stage.setTitle("使用root样式类定义全局样式");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
