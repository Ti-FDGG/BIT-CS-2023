package com.jinxuliang.javafxdatabindingdemo.viewbindtocontroller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


import java.net.URL;
import java.util.ResourceBundle;

public class ViewBindController implements Initializable {
    @FXML
    private Button btnClickMe;
    @FXML
    private Label lblInfo;

    private final IntegerProperty counter =
            new SimpleIntegerProperty();

    public IntegerProperty counterProperty() {
        return counter;
    }

    public int getCounter() {
        return counter.get();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnClickMe.setOnAction(e -> {
            //直接修改JavaFX Bean属性counter的值
            counter.setValue(counter.getValue() + 1);
        });
    }
}
