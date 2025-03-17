package com.jinxuliang.usescenebuilder;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

//让控制器实现Initializable接口
public class HelloController implements Initializable {
    @FXML
    private Label lblInfo; //引用FXML文档中的Label控件

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //主窗体显示时，让Label显示当前时间
        lblInfo.setText("当前时间："+ LocalTime.now());
    }
}