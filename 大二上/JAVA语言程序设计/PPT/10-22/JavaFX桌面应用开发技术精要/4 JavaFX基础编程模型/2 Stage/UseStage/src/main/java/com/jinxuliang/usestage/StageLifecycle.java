package com.jinxuliang.usestage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StageLifecycle extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        Button button = new Button("关闭窗体");
        button.setOnAction(e -> primaryStage.close());
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(button);
        Scene scene = new Scene(root, 310, 150);
        primaryStage.setTitle("Stage的生命周期演示");
        primaryStage.setScene(scene);

        responseToStageLifecycle(primaryStage);

        primaryStage.show();
    }

    private static void responseToStageLifecycle(Stage primaryStage) {
        primaryStage.setOnCloseRequest(e -> {
            System.out.println("OnCloseRequest");
            e.consume(); //禁止用户点击窗体“关闭”图标关闭自己
        });
        primaryStage.setOnHidden(e -> {
            System.out.println("OnHidden");
        });
        primaryStage.setOnHiding(e -> {
            System.out.println("OnHiding");
        });

        primaryStage.setOnShowing(e -> {
            System.out.println("OnShowing");
        });

        primaryStage.setOnShown(e -> {
            System.out.println("OnShown");
        });
    }
}
