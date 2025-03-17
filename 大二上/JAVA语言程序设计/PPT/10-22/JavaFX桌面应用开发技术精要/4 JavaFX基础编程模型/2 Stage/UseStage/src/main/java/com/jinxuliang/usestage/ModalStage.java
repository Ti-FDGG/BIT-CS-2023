package com.jinxuliang.usestage;

import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ModalStage extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("主窗体");
        primaryStage.setWidth(600);
        primaryStage.setHeight(400);
        primaryStage.show();

        Stage stage = new Stage();
        stage.setTitle("模态窗体");
        stage.setWidth(300);
        stage.setHeight(200);
        //以模态方式显示
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}
