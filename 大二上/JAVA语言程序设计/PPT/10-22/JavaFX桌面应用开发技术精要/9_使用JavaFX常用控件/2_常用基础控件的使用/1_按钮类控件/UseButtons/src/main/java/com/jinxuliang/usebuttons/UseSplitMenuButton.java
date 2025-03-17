package com.jinxuliang.usebuttons;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class UseSplitMenuButton extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        //实例化按钮对象
        var splitMenuButton = new SplitMenuButton();
        //设置用户第一次看到的文本
        splitMenuButton.setText("Click here!");
        //设定要使用的字体
        Font font = Font.font("Courier New", FontWeight.BOLD, 16);
        splitMenuButton.setFont(font);
        //设定直接单击按钮时的响应
        splitMenuButton.setOnAction((e) -> {
            System.out.println("SplitMenuButton clicked!");
        });

        //创建三个菜单项
        MenuItem choice1 = new MenuItem("Choice 1");
        MenuItem choice2 = new MenuItem("Choice 2");
        MenuItem choice3 = new MenuItem("Choice 3");
        //为三个菜单项编写事件响应代码
        choice1.setOnAction((e) -> {
            System.out.println("Choice 1 selected");
        });
        choice2.setOnAction((e) -> {
            System.out.println("Choice 2 selected");
        });
        choice3.setOnAction((e) -> {
            System.out.println("Choice 3 selected");
        });
        //将菜单项与按钮关联
        splitMenuButton.getItems().addAll(choice1, choice2, choice3);


        var vbox = new VBox(splitMenuButton);
        vbox.setPadding(new Insets(20));
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.TOP_CENTER);

        var scene = new Scene(vbox, 400, 200);
        primaryStage.setTitle("使用SplitMenuButton");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
