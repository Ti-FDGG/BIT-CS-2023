package com.jinxuliang.systemdialog;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Optional;


public class UseAlert extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.setAlignment(Pos.TOP_CENTER);
            hBox.setPadding(new Insets(10));
            hBox.setStyle("-fx-font-size: 12");

            Button btnInfo = new Button("模式消息框");
            btnInfo.setOnAction(e -> {
                //设定Alert的类型
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("消息框标题");
                alert.setHeaderText("提示信息（Header）");
                alert.setContentText("详细信息（Content）");
                //显示Alert
                alert.showAndWait();
            });

            Button btnError = new Button("警告或出错提示");
            btnError.setOnAction(e -> {
                //可选类型：AlertType.ERROR和AlertType.WARNING
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("出错了");
                alert.setHeaderText("出错消息(Header)");
                alert.setContentText("出错详情（Content）");
                alert.showAndWait();
            });

            Button btnConfirm = new Button("用户选择框");
            btnConfirm.setOnAction(e -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("请确定");
                alert.setHeaderText("真的需要进行这个操作？");
                alert.setContentText("此操作将永久删除记录，并且无法恢复");
                Optional<ButtonType> result = alert.showAndWait();
                result.filter(btn -> btn == ButtonType.OK)
                        .ifPresent(btn -> {
                            System.out.println("用户选择了确定");
                        });
                result.filter(btn -> btn == ButtonType.CANCEL)
                        .ifPresent(btn -> {
                            System.out.println("用户取消了操作");
                        });
            });

            Button btnTextInputDialog = new Button("用户输入框");
            btnTextInputDialog.setOnAction(e -> {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("请输入");
                dialog.setContentText("您的年纪是多少？");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    System.out.println("年纪:" + result.get());
                } else {
                    System.out.println("用户没有输入");
                }
            });

            Button btnChoiceDialog = new Button("用户选择框");
            btnChoiceDialog.setOnAction(e -> {
                ChoiceDialog<String> dialog = new ChoiceDialog<>();
                dialog.setTitle("使用ChoiceDialog");
                dialog.setContentText("请从下拉列表中选中一项");
                dialog.getItems().addAll("One", "Two", "Three");
                dialog.setSelectedItem("请选择...");
                dialog.showAndWait().ifPresent(result -> {
                    System.out.println("用户选择了：" + result);
                });
            });

            hBox.getChildren().addAll(btnInfo, btnError, btnConfirm, btnTextInputDialog,btnChoiceDialog);


            //AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("MainWindowView.fxml"));
            Scene scene = new Scene(hBox, 500, 80);
            primaryStage.setScene(scene);
            primaryStage.setTitle("使用Alert和TextInputDialog");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
