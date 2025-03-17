package com.jinxuliang.classinstance;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClassInstanceApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClassInstanceApp.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),600,750);
        stage.setTitle("类型的实例化");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}