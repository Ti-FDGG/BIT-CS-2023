package com.jinxuliang.usebuttons;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class CustomToolTip extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        var button = new Button("新建");

        Tooltip tooltip = new Tooltip("创建一个新的数据文件");
        tooltip.setTextAlignment(TextAlignment.CENTER);
        //加载图标
        var imageUrl = getClass().getResource("icon.png");
        FileInputStream input = new FileInputStream(imageUrl.getPath());
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        //设置Tooltip使用加载好的图标
        tooltip.setGraphic(imageView);
        //关联ToolTip与按钮
        button.setTooltip(tooltip);

        var vbox = new VBox(button);
        vbox.setPadding(new Insets(20));

        var scene = new Scene(vbox, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.setTitle("自定义Tooltip");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
