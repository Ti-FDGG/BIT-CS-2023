package com.jinxuliang.multiwindowdemo.controllers;

import com.jinxuliang.multiwindowdemo.MultiWindowApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainWindowController {

	@FXML TextField userInput;
	
	private MultiWindowApp main;

	public void setMain(MultiWindowApp main) {
		this.main=main;
	}

	//显示一个新的窗体
	public void showNewWindow() {
		main.openNewWindow();
	}

	//主窗体向另一个窗体发送信息
	public void mainToOther() {

		String message="主窗体说：";
		if(userInput.getText().length()>0){
			message += userInput.getText();
		}
		else {
			message+="用户没有输入";
		}
		main.mainToOther(message);
	}

	public void changeWindow() {
		main.changeMainWindowContent();
	}

	
	//显示另一个窗体
	public void showThirdWindow() {
		main.thirdWindow(this);
	}
	
	@FXML Label messageLabel;
	//从另一个窗体中提取信息
	public void showMessage(String message) {
		messageLabel.setText(message);
	}
}
