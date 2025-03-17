// HBoxTest.java
package com.jinxuliang.usehboxandvbox;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class UseHBox extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {

		//创建将要放到HBox中的所有控件
		Label nameLbl = new Label("姓名:");
		TextField nameFld = new TextField();
		Button okBtn = new Button("确定");
		Button cancelBtn = new Button("取消");
		//实例化HBox
		HBox root = new HBox();
		//设定控件间的间隔
		root.setSpacing(10);
		//添加所有控件
		root.getChildren()
				.addAll(nameLbl, nameFld, okBtn, cancelBtn);

		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" + 
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("HBox的使用方法");
		stage.show();
	}
}
