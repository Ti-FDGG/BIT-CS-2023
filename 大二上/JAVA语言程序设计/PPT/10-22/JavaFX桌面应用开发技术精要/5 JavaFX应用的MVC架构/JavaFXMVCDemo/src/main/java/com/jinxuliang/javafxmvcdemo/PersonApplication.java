package com.jinxuliang.javafxmvcdemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PersonApplication extends Application {
    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        mainWindow();
    }

    private Stage primaryStage;

    //显示主窗体
    public void mainWindow() {

        try {
            //装载视图
            FXMLLoader loader = new FXMLLoader(
                    PersonApplication.class.getResource("person-view.fxml"));
            AnchorPane root = (AnchorPane) loader.load();

            //获取控制器引用
            PersonController controller = loader.getController();
            //将Application类对象引用传入控制器
            controller.setApplication(this);

            //构建场景和舞台
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("JavaFX应用的MVC架构");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}