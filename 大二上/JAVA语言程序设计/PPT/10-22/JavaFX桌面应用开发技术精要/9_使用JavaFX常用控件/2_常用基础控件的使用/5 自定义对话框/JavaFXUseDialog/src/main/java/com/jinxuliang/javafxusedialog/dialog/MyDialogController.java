package com.jinxuliang.javafxusedialog.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class MyDialogController {
    @FXML
    private TextArea txtUserInput;

    //获取用户输入
    public String getUserInput(){
        return txtUserInput.getText();
    }
}

