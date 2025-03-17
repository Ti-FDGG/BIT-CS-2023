package com.jinxuliang.usemenu;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private MenuItem mnuOpen;
    @FXML
    private MenuItem mnuSave;
    @FXML
    private MenuItem mnuClose;
    @FXML
    private Label lblInfo;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mnuClose.setOnAction(e->{
            Platform.exit();
        });

        mnuOpen.setOnAction(e->{
            lblInfo.setText("点击了Open菜单项");
        });
        mnuSave.setOnAction(e->{
            lblInfo.setText("点击了Save菜单项");
        });
    }
}