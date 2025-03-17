package com.jinxuliang.closeotherstage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Button btnOpenNewStage;

    @FXML
    private Button btnCloseStage;

    private OtherController otherController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnOpenNewStage.setOnAction(e -> {
            openOtherStage();
        });
        btnCloseStage.setOnAction(e -> {
            closeOtherStage();
        });
    }

    private void closeOtherStage() {
        if(otherController!=null){
            otherController.closeStage();
            btnCloseStage.setDisable(true);
        }
        btnOpenNewStage.setDisable(false);
    }

    private void openOtherStage() {
        var loader = new FXMLLoader(getClass().getResource("other.fxml"));
        try {
            AnchorPane root = loader.load();
            //保存控制器引用
            otherController = loader.getController();
            Scene scene = new Scene(root);
            var stage = new Stage();
            stage.setScene(scene);
            stage.show();
            //禁用相应的按钮
            btnOpenNewStage.setDisable(true);
            btnCloseStage.setDisable(false);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}