package com.jinxuliang.javafxdatabindingdemo.bidirectionbindtoslider;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

public class BiDirectionBindController implements Initializable {
    @FXML
    private Slider slider;
    @FXML
    private TextField textField;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        slider.setValue(50);
        textField.setText(50 + "");
        textField.textProperty().bindBidirectional(
                slider.valueProperty(), NumberFormat.getInstance());
    }
}