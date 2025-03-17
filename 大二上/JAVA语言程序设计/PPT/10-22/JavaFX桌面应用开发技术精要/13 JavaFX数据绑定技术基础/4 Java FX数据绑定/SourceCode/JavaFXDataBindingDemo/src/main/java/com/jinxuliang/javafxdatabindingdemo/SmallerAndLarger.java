package com.jinxuliang.javafxdatabindingdemo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static javafx.beans.binding.Bindings.*;

public class SmallerAndLarger extends Application {
	@Override
	public void start(Stage primaryStage) {

		Button smaller = new Button("小");
		Button larger = new Button("大");
		Rectangle gauge = new Rectangle(0, 5, 50, 15);
		Rectangle outline = new Rectangle(0, 5, 220, 15);
		outline.setFill(null);
		outline.setStroke(Color.BLACK);
		Pane pane = new Pane();
		pane.getChildren().addAll(gauge, outline);

		// 点击按钮，修改Rectangle宽度
		smaller.setOnAction(event -> gauge.setWidth(gauge.getWidth() - 10));
		larger.setOnAction(event -> gauge.setWidth(gauge.getWidth() + 10));
		// 将Rectangle的宽度，绑定到Button的disable属性上，从而实现动态的激活或屏蔽
		smaller.disableProperty().bind(lessThanOrEqual(gauge.widthProperty(), 0));
		larger.disableProperty().bind(greaterThanOrEqual(gauge.widthProperty(), 220));

		HBox box = new HBox(10);
		box.setPadding(new Insets(20));
		box.getChildren().addAll(smaller, pane, larger);
		Scene scene = new Scene(box);

		primaryStage.setScene(scene);
		primaryStage.setTitle("大大大，小小小！");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
