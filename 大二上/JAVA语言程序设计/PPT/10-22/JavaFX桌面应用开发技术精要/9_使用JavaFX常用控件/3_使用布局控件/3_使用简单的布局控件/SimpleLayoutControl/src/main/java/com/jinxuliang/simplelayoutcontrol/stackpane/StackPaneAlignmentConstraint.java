// StackPaneAlignmentConstraint.java
package com.jinxuliang.simplelayoutcontrol.stackpane;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StackPaneAlignmentConstraint extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {

		Rectangle rect = new Rectangle(220, 60);
		rect.setFill(Color.LAVENDER);
		//默认对齐方式是居中
		Text center = new Text("Center");
		Text topLeft = new Text("top-left");
		StackPane.setAlignment(topLeft, Pos.TOP_LEFT);
		Text bottomRight = new Text("bottom-right");
		StackPane.setAlignment(bottomRight, Pos.BOTTOM_RIGHT);
		//将所有控件追加到StackPane中
		StackPane root = new StackPane(rect, center, topLeft, bottomRight);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root,320,200);
		stage.setScene(scene);
		stage.setTitle("StackPane控件对齐方式");
		stage.show();
	}
}
