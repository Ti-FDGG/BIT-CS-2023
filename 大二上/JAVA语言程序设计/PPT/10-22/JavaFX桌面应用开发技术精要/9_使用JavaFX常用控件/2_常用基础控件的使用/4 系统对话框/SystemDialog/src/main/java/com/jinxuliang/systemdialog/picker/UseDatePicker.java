package com.jinxuliang.systemdialog.picker;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UseDatePicker extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("使用DatePicker");

        DatePicker datePicker = new DatePicker();
        Label lblDate = new Label("选择一个日期");
        datePicker.valueProperty().addListener(obj -> {
            lblDate.setText(datePicker.getValue().toString());
        });


        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);
        vBox.getChildren().addAll(lblDate, datePicker);

        Scene scene = new Scene(vBox, 300, 240);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
