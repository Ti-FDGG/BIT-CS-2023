package com.jinxuliang.usebuttons;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class UseRadioButton extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("单选钮");

        RadioButton radioButton1 = new RadioButton("Left");
        RadioButton radioButton2 = new RadioButton("Right");
        RadioButton radioButton3 = new RadioButton("Up");
        RadioButton radioButton4 = new RadioButton("Down");

        //创建一个单选钮组
        ToggleGroup radioGroup = new ToggleGroup();
        //将四个RadioButton归于一组
        radioButton1.setToggleGroup(radioGroup);
        radioButton2.setToggleGroup(radioGroup);
        radioButton3.setToggleGroup(radioGroup);
        radioButton4.setToggleGroup(radioGroup);
        //选中第一个单选钮
        radioGroup.selectToggle(radioButton1);

        //监控选中状态的更改
        radioGroup.selectedToggleProperty().addListener(observable -> {
            RadioButton btn = (RadioButton) radioGroup.getSelectedToggle();
            System.out.println(btn.getText());
        });


        HBox hbox = new HBox(radioButton1, radioButton2, radioButton3, radioButton4);

        hbox.setSpacing(20);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(20));

        Scene scene = new Scene(hbox);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
