package com.jinxuliang.sceneandnode.userdata;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private Button btn4;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn1.setUserData(0);
        btn2.setUserData(0);
        btn3.setUserData(0);
        btn4.setUserData(0);

        btn1.setOnAction(this::clickButton);
        btn2.setOnAction(this::clickButton);
        btn3.setOnAction(this::clickButton);
        btn4.setOnAction(this::clickButton);
    }

    private void clickButton(ActionEvent e) {
        var button = (Button) e.getSource();
        Integer currentCount = (Integer) button.getUserData();
        currentCount += 1;
        button.setUserData(currentCount);
        button.setText(currentCount.toString());
    }
}