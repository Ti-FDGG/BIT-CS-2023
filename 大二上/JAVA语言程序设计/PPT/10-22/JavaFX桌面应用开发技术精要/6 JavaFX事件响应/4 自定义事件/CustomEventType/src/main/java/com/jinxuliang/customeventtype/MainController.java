package com.jinxuliang.customeventtype;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Label lblInfo;

    @FXML
    private Button btnTest;

    @FXML
    private Node root;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //给根控件添加对自定义事件的响应代码
        root.addEventHandler(MyEvent.MY_EVENT, e -> {
            lblInfo.setText("value:" + e.getNumber() + "\ninfo:" + e.getInfo());
        });
        //按钮单击时，触发自定义事件
        btnTest.setOnAction(e -> {
            var myevent = new MyEvent(MyEvent.MY_EVENT);
            myevent.setNumber(100);
            myevent.setInfo("自定义事件触发于：" + LocalTime.now());
            //触发事件
            Event.fireEvent(root, myevent);
        });
    }
}