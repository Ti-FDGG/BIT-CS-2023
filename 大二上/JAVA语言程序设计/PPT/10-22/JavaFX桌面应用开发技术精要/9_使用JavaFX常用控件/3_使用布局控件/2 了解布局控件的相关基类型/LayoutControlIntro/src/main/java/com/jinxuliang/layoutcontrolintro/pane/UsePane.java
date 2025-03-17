package com.jinxuliang.layoutcontrolintro.pane;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UsePane extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {

        Label lblInfo = new Label("Hello Pane");
        lblInfo.setPadding(new Insets(10));

        Pane pane = new Pane();
        pane.getChildren().add(lblInfo);

        Scene scene = new Scene(pane, 250, 100);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Pane的使用");

        primaryStage.show();
    }
}
