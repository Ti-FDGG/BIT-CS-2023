// TilePaneTest.java
package com.jinxuliang.horizontalandveticallayout.tilepane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.time.Month;

public class MonthsInTilePane extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {

		double hgap = 5.0;
		double vgap = 5.0;
		TilePane root = new TilePane(hgap, vgap);
		//设定每行显示3列
		root.setPrefColumns(3);
		
		// 12个月的英文，有长有短
		for(Month month: Month.values()) {
			Button b = new Button(month.toString());
			//让按钮自适应大小
			b.setMaxHeight(Double.MAX_VALUE);
			b.setMaxWidth(Double.MAX_VALUE);
			//加入到TilePane中
			root.getChildren().add(b);
		}
		
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("一年有12个月");
		stage.show();
	}
}
