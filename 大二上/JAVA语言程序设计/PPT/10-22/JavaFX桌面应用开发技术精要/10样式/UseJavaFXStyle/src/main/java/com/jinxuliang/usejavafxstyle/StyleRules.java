package com.jinxuliang.usejavafxstyle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class StyleRules extends Application {
    Circle cir;
    Circle cir2;

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("普通button");
        btn.setOnAction((ActionEvent e) -> {
            btn.setVisible(false);
        });

        Button btn2 = new Button();

        btn2.setText("应用了样式mybutton的按钮");
        //动态添加样式类

        btn2.getStyleClass().add("mybutton");
        btn2.setOnAction((ActionEvent e) -> {
            cir2.setFill(Color.DEEPPINK);
            btn.setVisible(true);
        });

        cir = new Circle(100, 100, 40);
        //设定圆对象的id
        cir.setId("cir");
        cir2 = new Circle(100, 100, 60);

        StackPane stack1 = new StackPane();
        Label title = new Label("使用代码应用样式规则");
        title.setId("title");
        stack1.setPadding(new Insets(10));
        stack1.setAlignment(Pos.CENTER);
        stack1.getChildren().add(title);

        StackPane stack2 = new StackPane();
        stack2.setPadding(new Insets(10));
        stack2.getChildren().addAll(cir, btn);

        StackPane stack3 = new StackPane();
        stack3.setPadding(new Insets(10));
        stack3.getChildren().addAll(cir2, btn2);

        BorderPane pane = new BorderPane();
        pane.setTop(stack1);
        pane.setCenter(stack2);
        pane.setBottom(stack3);

        Scene scene = new Scene(pane, 350, 350);

        var cssFile = getClass().getResource("styles.css").toString();
        scene.getStylesheets().add(cssFile);

        primaryStage.setTitle("Style Rules");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
