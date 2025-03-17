package com.jinxuliang.usestage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShowAndWaitApp extends Application {
    protected static int counter = 0;
    protected Stage lastOpenStage;
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox();
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));
        Button openButton = new Button("开新窗体");
        openButton.setOnAction(e -> open(++counter));
        root.getChildren().add(openButton);
        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.setTitle("主窗体");
        stage.show();
        this.lastOpenStage = stage;
    }

    private void open(int stageNumber) {
        Stage stage = new Stage();
        stage.setTitle("窗体" + stageNumber);
        Button sayHelloButton = new Button("打印信息");
        sayHelloButton.setOnAction(
                e -> System.out.println(
                        "打印的信息来自于窗体 #" + stageNumber));
        Button openButton = new Button("开新窗体");
        openButton.setOnAction(e -> open(++counter));
        VBox root = new VBox();
        root.getChildren().addAll(sayHelloButton, openButton);
        Scene scene = new Scene(root, 200, 200);
        stage.setScene(scene);
        stage.setX(this.lastOpenStage.getX() + 50);
        stage.setY(this.lastOpenStage.getY() + 50);
        this.lastOpenStage = stage;

        System.out.println("在stage.showAndWait()之前，窗体 " +
                stageNumber);
        stage.showAndWait();
        System.out.println("在stage.showAndWait()之后，窗体 " +
                stageNumber);
    }
}
