package com.jinxuliang.javafxdatabindingdemo.mynumber;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MyNumberController implements Initializable {
    //数据源
    final MyNumber myNum = new MyNumber();
    //UI控件
    @FXML
    private Label lblStatus;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //在UI控件与数据源之间建立数据绑定
        lblStatus.textProperty()
                .bind(myNum.numberProperty().asString());
    }
    @FXML
    private void ButtonClick() {
        //点击按钮，则修改Number属性值
        myNum.setNumber(myNum.getNumber() + 1);
    }
}
