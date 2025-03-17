package com.jinxuliang.usingjavafxdatabind;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MyNumberApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MyNumberApp.class.getResource("mynumber-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 220);
        stage.setTitle("基于数据绑定设置标签控件显示内容");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}