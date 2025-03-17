package com.jinxuliang.sceneandnode;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SceneGraph extends Application {
	@Override
	public void start(Stage primaryStage) {
		//标签用于显示信息
		Label message = new Label("Hello, JavaFX!");
		message.setFont(new Font(40));

		//按钮支持事件响应
		Button red = new Button("字体变红");
		//使用Lambda表达式定义按钮单击事件响应代码
		red.setOnAction(event -> message.setTextFill(Color.RED));

		//VBox是一个布局（Layout）控件，它按照从上到下的顺序排列多个控件
		VBox root = new VBox();
		root.setPadding(new Insets(10));
		root.setAlignment(Pos.CENTER);
		root.setSpacing(10);
		//将按钮和标签加入到VBox中，先加入的排在上面
		root.getChildren().addAll(red, message);

		//构建场景并显示
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("SceneGraph");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}