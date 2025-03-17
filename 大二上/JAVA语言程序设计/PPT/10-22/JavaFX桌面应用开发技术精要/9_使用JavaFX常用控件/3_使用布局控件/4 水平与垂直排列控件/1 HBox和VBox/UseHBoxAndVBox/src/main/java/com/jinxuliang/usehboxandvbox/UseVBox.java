// VBoxTest.java
package com.jinxuliang.usehboxandvbox;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UseVBox extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {

		Label nameLbl = new Label("姓名:");
		TextField nameFld = new TextField();
		Button okBtn = new Button("确定");
		Button cancelBtn = new Button("取消");

		VBox root = new VBox(10); // 控件间隔10px
		//与边界之间留出10像素的空白区域
		root.setPadding(new Insets(10));

		root.getChildren().addAll(nameLbl, nameFld, okBtn, cancelBtn);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" + 
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using VBox");
		stage.show();
	}
}
