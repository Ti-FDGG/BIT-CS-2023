// FlowPaneTest.java
package com.jinxuliang.horizontalandveticallayout.flowpane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class UseFlowPane extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		//设置水平和垂直间距
		double hgap = 5;
		double vgap = 10;
		FlowPane root = new FlowPane(hgap, vgap);




		for(int i = 1; i <= 10; i++) {
			root.getChildren().add(new Button("按钮 " + i));
		}
		
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("水平排列控件的FlowPane");
		stage.show();
	}
}
