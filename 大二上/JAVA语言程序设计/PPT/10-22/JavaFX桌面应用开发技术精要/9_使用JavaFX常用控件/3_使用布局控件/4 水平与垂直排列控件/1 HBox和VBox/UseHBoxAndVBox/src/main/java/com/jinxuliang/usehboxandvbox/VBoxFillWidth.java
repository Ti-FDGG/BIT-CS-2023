// VBoxFillWidth.java
package com.jinxuliang.usehboxandvbox;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VBoxFillWidth extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {

		Button b1 = new Button("按钮");
		Button b2 = new Button("比较长的按钮");
		Button b3 = new Button("长长长长长长的按钮");
		Button b4 = new Button("中等长按钮");
		
		// 让按钮控件可以水平伸缩
		// so they can grow horizontally
		b1.setMaxWidth(Double.MAX_VALUE);
		b2.setMaxWidth(Double.MAX_VALUE);
		b3.setMaxWidth(Double.MAX_VALUE);
	 	b4.setMaxWidth(Double.MAX_VALUE);
		
		VBox root = new VBox(10, b1, b2, b3, b4);
		//当fillWidth属性为false时，按钮宽度依文本长度而定
		//为true时，所有按钮都有相同的宽度
		root.setFillWidth(true);

		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("使用VBox的fillWidth属性");
		stage.show();
	}
}
