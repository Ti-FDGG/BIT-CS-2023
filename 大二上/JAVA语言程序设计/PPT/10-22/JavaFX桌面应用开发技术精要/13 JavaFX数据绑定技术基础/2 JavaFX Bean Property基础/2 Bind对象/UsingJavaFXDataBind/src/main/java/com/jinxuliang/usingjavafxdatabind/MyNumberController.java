package com.jinxuliang.usingjavafxdatabind;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MyNumberController implements Initializable {
    @FXML
    private Label lblCounter;
    private final MyNumber myNumber = new MyNumber();

    @FXML
    protected void onHelloButtonClick() {
        myNumber.setNumber(myNumber.getNumber() + 1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //建立单向绑定
        lblCounter.textProperty().bind(myNumber.numberProperty().asString());
    }
}