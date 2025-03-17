package com.jinxuliang.udpnetworkdevicecenterjavafxapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DeviceCenterApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                DeviceCenterApplication.class.getResource(
                        "main-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        //获取控制器引用
        MainController controller = fxmlLoader.getController();
        stage.setTitle("UDP示例：设备管理中心");
        stage.setScene(scene);
        //当主窗体关闭时，退出后台线程
        stage.setOnCloseRequest(e -> {
            controller.close();
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}