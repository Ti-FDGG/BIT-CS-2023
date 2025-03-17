package com.jinxuliang.fxmlbasic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMLBasicApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                FXMLBasicApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),300,200);
        stage.setTitle("FXML基础");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}