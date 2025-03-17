package com.jinxuliang.javafxdatabindingdemo;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Repeater extends Application {
	@Override
	public void start(Stage primaryStage) {
		  TextArea speaker = new TextArea();
	      TextArea repeater = new TextArea();
	      //在两个TextArea控件的text属性中建立双向绑定
	      repeater.textProperty().bindBidirectional(
	    		  speaker.textProperty());
	      VBox root = new VBox();
	      root.getChildren().addAll(new Label("讲述者"), speaker,
	    		  new Label("复述者"), repeater);
	      Scene scene = new Scene(root);
	      primaryStage.setScene(scene);
	      primaryStage.setTitle("复读机");
	      primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
