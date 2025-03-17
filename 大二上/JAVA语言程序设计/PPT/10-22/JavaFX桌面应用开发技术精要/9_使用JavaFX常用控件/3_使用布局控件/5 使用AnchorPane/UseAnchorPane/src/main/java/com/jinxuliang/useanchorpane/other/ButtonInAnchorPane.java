package com.jinxuliang.useanchorpane.other;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ButtonInAnchorPane extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {

		Button leftRight = new Button("支持水平伸缩的按钮");
		//设定按钮对象的Anchor
		AnchorPane.setTopAnchor(leftRight, 10.0);
		AnchorPane.setLeftAnchor(leftRight, 10.0);
		AnchorPane.setRightAnchor(leftRight, 10.0);
		//将按钮对象追加到AnchorPane中
		AnchorPane root = new AnchorPane();
		root.getChildren().addAll(leftRight);

		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("AnchorPane中的按钮");
		stage.show();
	}
}
