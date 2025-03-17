package com.jinxuliang.runlaterapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RunLaterApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        System.out.println("start():" + Thread.currentThread().getName());
        FXMLLoader fxmlLoader = new FXMLLoader(
                RunLaterApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("JavaFX线程模型");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void init() {
        System.out.println("init():" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        launch();
    }
}