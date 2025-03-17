package com.jinxuliang.usehboxandvbox;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HBoxFillHeight extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {

		HBox root = new HBox(10);
		Label descLbl = new Label("描述信息:");
		//加入一个多行文本框，可显示3行10列文本
		TextArea desc = new TextArea();
		desc.setPrefColumnCount(10);
		desc.setPrefRowCount(3);
		
		Button okBtn = new Button("确定");
		Button cancelBtn = new Button("取消");
		//让“取消按钮”可以垂直伸展
		cancelBtn.setMaxHeight(Double.MAX_VALUE);

		CheckBox fillHeightCbx = new CheckBox("控件高度自适应");
		fillHeightCbx.setSelected(true);
		//动态切换fillHeight属性值
		fillHeightCbx.setOnAction(e -> 
				root.setFillHeight(fillHeightCbx.isSelected()));

		root.getChildren().addAll(
						descLbl, desc, fillHeightCbx, okBtn, cancelBtn);

		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("使用HBox的fillHeight属性");
		stage.show();
	}
}
