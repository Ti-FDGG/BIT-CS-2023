package com.jinxuliang.closeotherstage;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OtherController {

    @FXML
    private Label lblInfo;

    public void closeStage(){
        lblInfo.getScene().getWindow().hide();
    }

}
