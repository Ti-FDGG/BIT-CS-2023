package com.jinxuliang.usejavafxstyle;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class StyleRuleWithFxml extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(
					getClass().getResource("MainView.fxml"));
			Scene scene = new Scene(root);
			var cssFile=getClass().getResource("application.css")
					.toString();
			scene.getStylesheets().add(cssFile);
			primaryStage.setScene(scene);
			primaryStage.setTitle("JavaFX设置样式示例");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
