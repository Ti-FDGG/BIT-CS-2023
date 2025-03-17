package com.jinxuliang.multiwindowdemo.controllers;

import com.jinxuliang.multiwindowdemo.MultiWindowApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class OtherWindowController {

	private Stage stage;

	public void init(MultiWindowApp main, Stage stage) {
		this.stage=stage;
	}
    //关闭从窗体自己
	public void close() {
		stage.close();
	}
	@FXML Label infoLabel;
	//从窗体用于从外部接收信息的方法
	public void setMessage(String message) {
		infoLabel.setText(message);
	}
}
