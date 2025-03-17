package com.jinxuliang.usebuttons;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UseCheckBox extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("使用CheckBox");
        Label lblInfo = new Label();
        CheckBox checkBox = new CheckBox("Green");
        checkBox.setIndeterminate(true);
        lblInfo.setText("当前CheckBox居于Indeterminate状态？"+checkBox.isIndeterminate());
        //checkBox.setSelected(true);

        checkBox.selectedProperty().addListener(observable ->
                lblInfo.setText("当前状态：" + checkBox.isSelected()));


        checkBox.setOnAction(e -> {
            System.out.println("单击事件触发");
        });

        VBox vBox = new VBox(checkBox);
        vBox.getChildren().add(lblInfo);
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20));

        Scene scene = new Scene(vBox,280,100);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
