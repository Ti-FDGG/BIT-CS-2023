package com.jinxuliang.useanchorpane;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private ImageView imageView;
    @FXML
    private Pane imgContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //关闭按比例缩放功能，这样图片始终都能充满全屏幕
        imageView.setPreserveRatio(false);
        //将ImageView的高度、宽度绑定到图片容器，设置其占满所有空间
        imageView.fitHeightProperty().bind(imgContainer.heightProperty());
        imageView.fitWidthProperty().bind(imgContainer.widthProperty());
    }
}