// StackPaneTest.java
package com.jinxuliang.simplelayoutcontrol.stackpane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StyleAndStackPane extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {

		Rectangle rect = new Rectangle(200, 50);
		rect.setStyle("-fx-fill: lavender;" +
		              "-fx-stroke-type: inside;" +
		              "-fx-stroke-dash-array: 5 5;" +
		              "-fx-stroke-width: 1;" +
		              "-fx-stroke: black;" +
		              "-fx-stroke-radius: 5;");

		Text text = new Text("A Rectangle");

		StackPane root = new StackPane();
		root.getChildren().add(rect);
		root.getChildren().add(text);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root,320,100);
		stage.setScene(scene);
		stage.setTitle("StackPane与样式应用");
		stage.show();
	}
}
