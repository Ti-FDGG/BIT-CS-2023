// VBoxVGrow.java
package com.jinxuliang.usehboxandvbox;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VBoxVGrow extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {

		Label descLbl = new Label("详细信息:");
		TextArea desc = new TextArea();
		//显示3行10列
		desc.setPrefColumnCount(10);
		desc.setPrefRowCount(3);
		
		VBox root = new VBox(10); 
		root.getChildren().addAll(descLbl, desc);
		
		//设置TextArea控件可以随着VBox大小的变化而自动伸缩
		VBox.setVgrow(desc, Priority.ALWAYS);

		root.setStyle("-fx-padding: 10;" + 
					  "-fx-border-style: solid inside;" + 
					  "-fx-border-width: 2;" +
					  "-fx-border-insets: 5;" + 
					  "-fx-border-radius: 5;" + 
					  "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("设置VBox的垂直伸缩属性");
		stage.show();
	}
}
