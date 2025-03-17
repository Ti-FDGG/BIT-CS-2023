package com.jinxuliang.systemdialog.picker;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class UseColorPicker extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("使用ColorPicker");


        Label lblColor = new Label("使用设置的颜色显示标签文本");
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.BLACK);
        colorPicker.valueProperty().addListener(obj -> {
            lblColor.setTextFill(colorPicker.getValue());
        });

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.getChildren().addAll(lblColor, colorPicker);
        Scene scene = new Scene(vBox, 360, 240);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
