package com.jinxuliang.usejavafxstyle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;


public class ButtonAndStyle extends Application {
	Label lblOutput;
	@Override
	public void start(Stage primaryStage) {

		Label lbl = new Label("点击按钮，查看消息。");
        lblOutput = new Label(null);

        //将应用样式表中的.button规则
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(e->lblOutput.setText("Hello World!"));

        //将应用样式表中的“#textstyle”规则
        lblOutput.setId("textstyle");

        FlowPane root = new FlowPane();
        root.setAlignment(Pos.CENTER);
        root.setVgap(10);
        root.setHgap(10);
        root.setOrientation(Orientation.VERTICAL);
        root.getChildren().addAll(lbl, btn, lblOutput);

        //加载样式表
        Scene scene = new Scene(root, 300, 250);

        scene.getStylesheets()
                .add(getClass()
                        .getResource("button_text.css").toString());

        primaryStage.setTitle("样式的应用");
        primaryStage.setScene(scene);
        primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
