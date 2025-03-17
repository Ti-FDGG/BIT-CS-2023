package com.jinxuliang.systemdialog.chooser;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class UseDirectoryChooser extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("选择文件夹示例");

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("src"));

        Button button = new Button("Select Directory");
        button.setOnAction(e -> {
            File selectedDirectory = directoryChooser.showDialog(primaryStage);
            if (selectedDirectory != null)
                System.out.println(selectedDirectory.getAbsolutePath());
        });


        VBox vBox = new VBox(button);
        vBox.setPadding(new Insets(10));
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox, 280, 120);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
