package com.jinxuliang.progressdemo;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;

public class MainController implements Initializable {
	@FXML Button btnIncrease;
	@FXML Button btnDecrease;
	@FXML ProgressBar progressBar;
	@FXML ProgressIndicator progressIndicator;
	@FXML Label lblInfo;
	
	
	MyNumber myNumber=new MyNumber();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		myNumber.setNumber(0);
		
		progressBar.progressProperty().bind(myNumber.numberProperty());
		progressIndicator.progressProperty().bind(myNumber.numberProperty());
		
		
		
		btnIncrease.setOnAction(e->{
			double newValue=myNumber.getNumber()+0.1;
			if(newValue>1) {
				newValue=1;
			}
			
			this.myNumber.setNumber(newValue);
		});
		
		btnDecrease.setOnAction(e->{
			double newValue=myNumber.getNumber()-0.1;
			if(newValue<0) {
				newValue=0;
			}
			this.myNumber.setNumber(newValue);
		});
		
		myNumber.numberProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				
				lblInfo.setText((int)(newValue.doubleValue()*100)+"%");
			}
		});
		
	}	
}
