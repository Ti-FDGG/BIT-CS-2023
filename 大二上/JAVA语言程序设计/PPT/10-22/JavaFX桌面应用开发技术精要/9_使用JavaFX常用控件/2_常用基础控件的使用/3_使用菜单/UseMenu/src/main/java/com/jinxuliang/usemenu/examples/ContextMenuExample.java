package com.jinxuliang.usemenu.examples;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ContextMenuExample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {

        Label lblInfo = new Label("右击文本编辑框，显示弹出菜单");

        //构建弹出式菜单
        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItem1 = new MenuItem("选项一");
        MenuItem menuItem2 = new MenuItem("选项二");
        MenuItem menuItem3 = new MenuItem("选项三");
        menuItem1.setOnAction((event) -> {
            lblInfo.setText("选项一被点击");
        });
        contextMenu.getItems().addAll(menuItem1, menuItem2, menuItem3);
        //使用自定义菜单替换掉TextArea内置的菜单
        TextArea textArea = new TextArea();
        textArea.setContextMenu(contextMenu);

        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(textArea, lblInfo);

        Scene scene = new Scene(vBox,300,200);

        primaryStage.setScene(scene);
        primaryStage.setTitle("弹出式上下文菜单");

        primaryStage.show();
    }
}
