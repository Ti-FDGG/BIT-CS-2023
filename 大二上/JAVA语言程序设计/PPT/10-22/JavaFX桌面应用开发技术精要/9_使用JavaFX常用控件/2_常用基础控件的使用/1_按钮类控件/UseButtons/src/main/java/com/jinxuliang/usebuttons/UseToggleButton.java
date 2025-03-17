package com.jinxuliang.usebuttons;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class UseToggleButton extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("ToggleButton示例");


        ToggleButton toggleButton1 = new ToggleButton("Left");
        ToggleButton toggleButton2 = new ToggleButton("Right");
        ToggleButton toggleButton3 = new ToggleButton("Up");
        ToggleButton toggleButton4 = new ToggleButton("Down");

        ToggleGroup toggleGroup = new ToggleGroup();

        toggleButton1.setToggleGroup(toggleGroup);
        toggleButton2.setToggleGroup(toggleGroup);
        toggleButton3.setToggleGroup(toggleGroup);
        toggleButton4.setToggleGroup(toggleGroup);

        HBox hbox = new HBox(toggleButton1, toggleButton2, toggleButton3, toggleButton4);

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
