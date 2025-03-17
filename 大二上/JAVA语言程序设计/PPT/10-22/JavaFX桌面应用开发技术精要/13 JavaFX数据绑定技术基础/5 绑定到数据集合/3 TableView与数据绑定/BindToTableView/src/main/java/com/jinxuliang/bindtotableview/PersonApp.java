package com.jinxuliang.bindtotableview;
	
import java.io.IOException;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class PersonApp extends Application {
	
	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		mainWindow();
	}
	
	public void mainWindow() {

		try {
			FXMLLoader loader = new FXMLLoader(
					PersonApp.class.getResource("MainView.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			
			MainController controller=loader.getController();
			
			controller.setMain(this,primaryStage);
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("绑定到对象集合");
			primaryStage.show();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
