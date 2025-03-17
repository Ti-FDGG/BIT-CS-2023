package com.jinxuliang.multiwindowdemo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ThirdWindowController {
	
	@FXML DatePicker datePicker;
	@FXML TextField messageTextField;

	private Stage myStage;
	//引用主窗体控制器
	private MainWindowController mainWindowController;

	public void init(MainWindowController controller,Stage stage) {
		this.mainWindowController=controller;
		myStage=stage;
	}

	private String result;
	//生成要传送的消息
	public String getResult() {
		 result=datePicker.getValue()+":"+messageTextField.getText();
		return result;
	}

	public void cancel() {
		result="用户取消了输入";
		//直接调用主窗体控制器的相应方法传送信息
		mainWindowController.showMessage(result);
		myStage.close();
	}

	public void  ok() {
		result=getResult();
		//直接调用主窗体控制器的相应方法传送信息
		mainWindowController.showMessage(result);
		myStage.close();
	}

}
