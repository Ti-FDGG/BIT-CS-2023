package com.jinxuliang.usestage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StageOwner extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(10));

        Button btn = new Button("创建子窗体");
        btn.setOnAction(e -> {
            Stage newStage = new Stage();
            newStage.setTitle("子窗体");
            //设定所有者关系
            newStage.initOwner(primaryStage);
            newStage.setWidth(300);
            newStage.setHeight(200);
            newStage.show();
        });

        vBox.getChildren().addAll(btn);

        Scene scene = new Scene(vBox,300,400);

        primaryStage.setScene(scene);
        primaryStage.setTitle("主窗体");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
