// HBoxHGrow.java
package com.jinxuliang.usehboxandvbox;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class HBoxHGrow extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {

		Label nameLbl = new Label("姓名：");
		TextField nameFld = new TextField();
		Button okBtn = new Button("确定");
		Button cancelBtn = new Button("取消");

		HBox root = new HBox(10); 
		root.getChildren().addAll(nameLbl, nameFld, okBtn, cancelBtn);
		
		// 设置TextField控件，宽度可以自由伸缩
		HBox.setHgrow(nameFld, Priority.ALWAYS);
		
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("使用HBox的Grow属性实现水平伸缩");
		stage.show();
	}
}
