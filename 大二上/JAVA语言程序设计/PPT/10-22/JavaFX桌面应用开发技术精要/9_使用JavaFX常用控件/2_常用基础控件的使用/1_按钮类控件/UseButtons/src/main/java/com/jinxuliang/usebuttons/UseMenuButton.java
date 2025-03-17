package com.jinxuliang.usebuttons;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class UseMenuButton extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("使用MenuButton");

        //设置菜单项
        MenuItem menuItem1 = new MenuItem("选项一");
        MenuItem menuItem2 = new MenuItem("选项二");
        MenuItem menuItem3 = new MenuItem("选项三");
        //设定事件响应
        menuItem1.setOnAction(e->{
            new Alert(Alert.AlertType.INFORMATION,"“选项一”被选择").show();
        });

        //设定图标
        var imageUrl = getClass().getResource("icon.png");
        FileInputStream input = new FileInputStream(imageUrl.getPath());
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(32);
        imageView.setFitHeight(32);
        //构建选项按钮
        MenuButton menuButton = new MenuButton("选项按钮",
                imageView, menuItem1, menuItem2, menuItem3);
        //可以选择在右部弹出菜单
        //menuButton.setPopupSide(Side.RIGHT);

        //调整字体
        Font font = Font.font("Courier New", FontWeight.BOLD, 16);
        menuButton.setFont(font);

        VBox vbox = new VBox(menuButton);
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(20));

        Scene scene = new Scene(vbox, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
