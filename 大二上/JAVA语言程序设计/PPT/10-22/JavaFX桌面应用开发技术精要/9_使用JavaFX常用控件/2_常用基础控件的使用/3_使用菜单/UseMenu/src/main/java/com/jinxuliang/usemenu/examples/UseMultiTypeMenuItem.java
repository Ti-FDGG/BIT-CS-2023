package com.jinxuliang.usemenu.examples;

import com.jinxuliang.usemenu.MenuApplication;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class UseMultiTypeMenuItem extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("多种类型的菜单项");

        //用于承载菜单的容器控件
        MenuBar menuBar = new MenuBar();

        Menu menu = new Menu("有图标的菜单");
        //从资源中加载图标
        var imageUrl= MenuApplication.class.getResource("icon.png");
        FileInputStream input = new FileInputStream(imageUrl.getPath());
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        //将菜单与图标关联起来
        menu.setGraphic(imageView);

        //普通菜单项及点击事件响应
        MenuItem menuItem1 = new MenuItem("菜单项一");
        menuItem1.setOnAction(e -> {
            System.out.println("菜单项一被选中");
        });
        MenuItem menuItem2 = new MenuItem("菜单项二");
        menu.getItems().add(menuItem1);
        menu.getItems().add(menuItem2);

        Menu subMenu = new Menu("有子菜单的菜单项");
        MenuItem menuItem11 = new MenuItem("子菜单项");
        subMenu.getItems().add(menuItem11);
        //添加子菜单项
        menu.getItems().add(subMenu);

        //支持选中的菜单项
        CheckMenuItem checkMenuItem = new CheckMenuItem("点击切换选中与否");
        menu.getItems().add(checkMenuItem);

        //菜单分割条
        SeparatorMenuItem separator = new SeparatorMenuItem();
        menu.getItems().add(separator);

        //归为一组的菜单项，互斥选中
        RadioMenuItem choice1Item = new RadioMenuItem("Choice 1");
        RadioMenuItem choice2Item = new RadioMenuItem("Choice 2");
        RadioMenuItem choice3Item = new RadioMenuItem("Choice 3");
        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().add(choice1Item);
        toggleGroup.getToggles().add(choice2Item);
        toggleGroup.getToggles().add(choice3Item);
        //将上述菜单项加入到菜单组件中
        menu.getItems().add(choice1Item);
        menu.getItems().add(choice2Item);
        menu.getItems().add(choice3Item);

        //创建一个Slide控件
        Slider slider = new Slider(0, 100, 50);
        //响应滑块事件
        slider.valueProperty().addListener(ctl->{
            System.out.println("SlideValue:"+slider.getValue());
        });
        //定义一个自定义菜单控件
        CustomMenuItem customMenuItem = new CustomMenuItem();
        //以滑块控件作为显示内容
        customMenuItem.setContent(slider);
        customMenuItem.setHideOnClick(false);
        menu.getItems().add(customMenuItem);

        //创建一个使用按钮作为显示内容的菜单项
        Button button = new Button("菜单项中的按钮");
        button.setOnAction(e->{
            System.out.println("按钮被点击");
        });
        CustomMenuItem customMenuItem2 = new CustomMenuItem();
        customMenuItem2.setContent(button);
        customMenuItem2.setHideOnClick(false);
        //注意：以下事件响应代码将被调用两次
        customMenuItem2.setOnAction(e->{
            System.out.println("包容按钮的自定义菜单项被点击");
        });
        menu.getItems().add(customMenuItem2);


        //响应菜单项的“显示”与“隐藏”事件
        menu.setOnShowing(e -> { System.out.println("Showing Menu"); });
        menu.setOnShown  (e -> { System.out.println("Shown Menu"); });
        menu.setOnHiding (e -> { System.out.println("Hiding Menu"); });
        menu.setOnHidden (e -> { System.out.println("Hidden Menu"); });

        menuBar.getMenus().add(menu);

        VBox vBox = new VBox(menuBar);
        Scene scene = new Scene(vBox, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("各种类型的菜单项");
        primaryStage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}
