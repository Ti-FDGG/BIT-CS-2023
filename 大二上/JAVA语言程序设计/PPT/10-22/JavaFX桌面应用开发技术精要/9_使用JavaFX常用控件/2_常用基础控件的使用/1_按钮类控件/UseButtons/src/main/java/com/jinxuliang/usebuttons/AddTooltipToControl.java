package com.jinxuliang.usebuttons;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AddTooltipToControl extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Label nameLbl = new Label("姓名:");
		TextField nameFld = new TextField();
		Button saveBtn = new Button("保存");
		Button closeBtn = new Button("关闭");

		closeBtn.setOnAction(e -> stage.close());

		nameFld.setTooltip(new Tooltip("输入你的姓名e\n(最长10个字符)"));
		saveBtn.setTooltip(new Tooltip("保存数据"));
		closeBtn.setTooltip(new Tooltip("关闭窗体"));


		HBox root = new HBox(nameLbl, nameFld, saveBtn, closeBtn);
		root.setSpacing(10);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("给控件加上ToolTip");
		stage.show();
	}
}
