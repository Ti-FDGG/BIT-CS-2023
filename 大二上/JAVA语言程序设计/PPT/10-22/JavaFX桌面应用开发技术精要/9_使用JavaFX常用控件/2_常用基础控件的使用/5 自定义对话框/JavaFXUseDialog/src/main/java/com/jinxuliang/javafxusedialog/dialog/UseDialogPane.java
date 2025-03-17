package com.jinxuliang.javafxusedialog.dialog;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class UseDialogPane extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Label lblInfo = new Label("展示用户操作结果");
        Button btnDialog = new Button("显示自定义对话框");
        btnDialog.setOnAction(e->{

            //创建对话框组件
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.initOwner(btnDialog.getScene().getWindow());
            dialog.setTitle("自定义对话框");
            //准备从资源中加载fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("mydialog.fxml"));
            try {
                //从fxml中创建控件树，并将其作为对话框的主体
                DialogPane dialogPane = dialog.getDialogPane();
                dialogPane.setContent(loader.load());
                //添加两个标准按钮
                dialogPane.getButtonTypes().add(ButtonType.OK);
                dialogPane.getButtonTypes().add(ButtonType.CANCEL);

                //获取控制器实例
                var dialogController=(MyDialogController)loader.getController();
                //显示对话框
                Optional<ButtonType> result = dialog.showAndWait();
                //获取用户输入
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    lblInfo.setTextFill(Color.DARKBLUE);
                    lblInfo.setText(dialogController.getUserInput());
                } else {
                    lblInfo.setTextFill(Color.RED);
                    lblInfo.setText("用户选择了“取消”按钮");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);
        vBox.getChildren().addAll(btnDialog,lblInfo);

        primaryStage.setTitle("自定义对话框示例");
        primaryStage.setScene(new Scene(vBox, 300, 125));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
