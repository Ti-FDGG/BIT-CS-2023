package com.jinxuliang.usestage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static javafx.stage.StageStyle.*;

public class StageStyleApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Label styleLabel = new Label("Stage样式");
        Button closeButton = new Button("关闭");
        closeButton.setOnAction(e -> stage.close());
        VBox root = new VBox();
        root.setSpacing(20);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(styleLabel, closeButton);
        Scene scene = new Scene(root, 300,150);
        stage.setScene(scene);

        stage.setTitle("Stage的样式");
        /*依次取消下面的注释，可以看到不同样式窗体的特性 */
        this.show(stage, styleLabel, DECORATED);
        //this.show(stage, styleLabel, UNDECORATED);
        //this.show(stage, styleLabel, TRANSPARENT);
        //this.show(stage, styleLabel, UNIFIED);
        // this.show(stage, styleLabel, UTILITY);

    }

    //按照特定的样式显示窗体
    private void show(Stage stage, Label styleLabel, StageStyle style) {
        //显示样式名称
        styleLabel.setText(style.toString());
        stage.setTitle(style.toString());
        //设置窗体样式
        stage.initStyle(style);
        // 对于透明样式，需要将窗体背景设置为null，否则，会得到一个白色的背景
        if (style == TRANSPARENT) {
            stage.getScene().setFill(null);
            stage.getScene().getRoot().setStyle(
                    "-fx-background-color: transparent");
        } else if(style == UNIFIED) {
            stage.getScene().setFill(Color.TRANSPARENT);
        }
        // 显示窗体
        stage.show();
    }
}
