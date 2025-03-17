package com.jinxuliang.usehboxandvbox;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VBoxAlignment extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {

		Button okBtn = new Button("确定");
		Button cancelBtn = new Button("取消");

		VBox vbox = new VBox(10);
		//设定宽度和高度
		vbox.setPrefSize(300, 200);
		vbox.getChildren().addAll(okBtn, cancelBtn);
		//子控件向右下角对齐
		vbox.setAlignment(Pos.BOTTOM_RIGHT);
		
		vbox.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(vbox,350,150);
		stage.setScene(scene);
		stage.setTitle("使用VBox的Alignment属性");
		stage.show();		
	}
}
