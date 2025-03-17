package com.jinxuliang.databindpropertyeventdemo;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Text text=new Text("拖动窗体边框改变窗体大小");
			text.setFont(new Font(25));
			

			root.setCenter(text);
			BorderPane.setAlignment(text, Pos.CENTER);

			Scene scene = new Scene(root,400,300);

			primaryStage.setScene(scene);
			primaryStage.setTitle("响应JavaFX数据绑定属性事件");
			primaryStage.show();
			
			//监控Stage对象的widthProperty，当窗体大小更改，用标签控件显示其值
		    primaryStage.widthProperty().addListener(new InvalidationListener() {
				@Override
				public void invalidated(Observable observable) {
					text.setText(String.format("宽度：%d,高度：%d", 
							(int)primaryStage.getWidth(),
							(int)primaryStage.getHeight()));
				}
			});
		    
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
