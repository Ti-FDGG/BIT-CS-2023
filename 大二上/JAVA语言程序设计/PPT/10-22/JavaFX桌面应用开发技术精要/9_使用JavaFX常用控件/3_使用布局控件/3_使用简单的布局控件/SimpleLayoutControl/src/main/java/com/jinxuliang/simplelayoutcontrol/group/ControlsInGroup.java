// NodesLayoutInGroup.java
package com.jinxuliang.simplelayoutcontrol.group;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControlsInGroup extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {

		Button okBtn = new Button("确定");
		Button cancelBtn = new Button("取消");

		// 设定两个按钮的显示位置
		okBtn.setLayoutX(80);
		okBtn.setLayoutY(20);
		cancelBtn.setLayoutX(140);
		cancelBtn.setLayoutY(20);

		Group root = new Group();		
		root.getChildren().addAll(okBtn, cancelBtn);

		Scene scene = new Scene(root,280,70);
		stage.setScene(scene);
		stage.setTitle("在Group中布局控件");
		stage.show();
	}
}
