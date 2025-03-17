package com.jinxuliang.usestage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MultiStage extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Button btnFirst = new Button("First Stage");
        Scene scene = new Scene(btnFirst, 200, 250);
        primaryStage.setScene(scene);
        primaryStage.show();

        Stage otherStage = new Stage();
        Button btnSecond = new Button("Second Stage");
        Scene scene2 = new Scene(btnSecond, 400, 500);
        otherStage.setScene(scene2);
        //如果指定otherStage的所有者是primaryStage，则当Owner关闭时
        //otherStage也会关闭
        otherStage.initOwner(primaryStage);
        otherStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

