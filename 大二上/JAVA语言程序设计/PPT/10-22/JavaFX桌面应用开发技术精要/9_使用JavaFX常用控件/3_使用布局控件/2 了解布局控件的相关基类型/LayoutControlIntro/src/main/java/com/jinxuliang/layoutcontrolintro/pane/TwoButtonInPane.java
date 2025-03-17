// PaneTest.java
package com.jinxuliang.layoutcontrolintro.pane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TwoButtonInPane extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {

		Button okBtn = new Button("确定");
		Button cancelBtn = new Button("取消");
		okBtn.relocate(90, 40);
		cancelBtn.relocate(150, 40);

		Pane root = new Pane();
		root.getChildren().addAll(okBtn, cancelBtn);

		root.setStyle("-fx-border-style: solid inside;" + 
		              "-fx-border-width: 3;" + 
		              "-fx-border-color: red;");

		Scene scene = new Scene(root,320,100);
		stage.setScene(scene);
		stage.setTitle("在Pane中放置多个控件");
		stage.show();
	}
}
