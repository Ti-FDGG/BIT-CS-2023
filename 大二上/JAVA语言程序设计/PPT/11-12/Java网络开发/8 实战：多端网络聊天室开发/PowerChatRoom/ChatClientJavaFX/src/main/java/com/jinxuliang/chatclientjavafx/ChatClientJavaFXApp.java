package com.jinxuliang.chatclientjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChatClientJavaFXApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                ChatClientJavaFXApp.class.getResource("main-view.fxml"));
        Parent root = fxmlLoader.load();
        MainController controller = fxmlLoader.getController();
        Scene scene = new Scene(root);
        stage.setTitle("JavaFX聊天客户端");
        stage.setScene(scene);
        //关闭主窗体时,退出可能正在运行的线程
        stage.setOnCloseRequest(e -> {
            if (controller != null) {
                controller.quit();
            }
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}