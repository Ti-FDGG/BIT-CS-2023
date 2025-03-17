package com.jinxuliang.systemdialog.chooser;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class UseFileChooser extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX使用打开文件对话框");

        var fileChooser = new FileChooser();
        //指定初始显示的文件夹
        fileChooser.setInitialDirectory(new File("c:"));
        //设定筛选过滤条件
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
                , new FileChooser.ExtensionFilter("HTML Files", "*.htm")
                , new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        Button button = new Button("选择文件");
        button.setOnAction(e -> {
            //显示“打开文件”对话框
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                var alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("选择文件");
                alert.setHeaderText("您选择了文件：");
                alert.setContentText(selectedFile.toString());
                alert.show();
            }

        });

        VBox vBox = new VBox(button);
        vBox.setPadding(new Insets(20));
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox, 400, 200);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}