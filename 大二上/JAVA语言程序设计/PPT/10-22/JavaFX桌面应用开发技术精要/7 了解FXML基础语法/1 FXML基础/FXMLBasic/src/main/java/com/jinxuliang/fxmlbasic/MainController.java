package com.jinxuliang.fxmlbasic;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Label lblInfo;
    @FXML
    private Button mybutton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mybutton.setOnAction(e->{
            lblInfo.setText("当前时间："+ LocalTime.now());
        });
    }
}