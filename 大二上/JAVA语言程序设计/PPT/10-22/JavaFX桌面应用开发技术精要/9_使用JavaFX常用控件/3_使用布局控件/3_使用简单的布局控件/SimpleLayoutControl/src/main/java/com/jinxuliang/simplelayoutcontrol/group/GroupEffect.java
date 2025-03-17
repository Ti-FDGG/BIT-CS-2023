package com.jinxuliang.simplelayoutcontrol.group;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.stage.Stage;

public class GroupEffect extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {

		//创建两个按钮
		Button okBtn = new Button("确定");
		Button cancelBtn = new Button("取消");
		// 设定显示布局
		okBtn.setLayoutX(70);
		okBtn.setLayoutY(50);
		cancelBtn.setLayoutX(120);
		cancelBtn.setLayoutY(50);
		//追加到Group控件中
		Group root = new Group();
		root.getChildren().addAll(okBtn, cancelBtn);
		//给Group中的所有控件，添加阴影、旋转变换，并设置其透明度
		root.setEffect(new DropShadow());
		root.setRotate(10);
		root.setOpacity(0.80);
		


		Scene scene = new Scene(root,260,130);
		stage.setScene(scene);
		stage.setTitle("给Group添加特效");
		stage.show();
	}
}
