package com.jinxuliang.usehboxandvbox;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HBoxAlignment extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {

		Button okBtn = new Button("确定");
		Button cancelBtn = new Button("取消");

		HBox hbox = new HBox(10);
		hbox.setPrefSize(200, 100);
		hbox.getChildren().addAll(okBtn, cancelBtn);

		// 设置子控件显示在右下角
		hbox.setAlignment(Pos.BOTTOM_RIGHT);
		
		hbox.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(hbox,300,200);
		stage.setScene(scene);
		stage.setTitle("设置HBox子控件的显示区域");
		stage.show();		
	}
}
