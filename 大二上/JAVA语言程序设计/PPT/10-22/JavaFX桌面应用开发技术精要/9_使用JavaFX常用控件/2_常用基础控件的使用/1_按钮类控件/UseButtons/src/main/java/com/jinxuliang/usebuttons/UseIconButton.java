package com.jinxuliang.usebuttons;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class UseIconButton extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("图标按钮");

        //注意：路径中不能有空格,有空格的路径，会导致资源文件读取失败
        var imageUrl = UseIconButton.class.getResource("icon.png");
        FileInputStream input = new FileInputStream(imageUrl.getPath());
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        Button button = new Button("Home", imageView);
        button.setOnAction(e->{
            System.out.println("Click事件触发");
        });

        //通过样式设定按钮的边框，背景色，字体大小和颜色
        button.setStyle("-fx-border-color: #ff0000; -fx-border-width: 5px;");
        button.setStyle("-fx-background-color: #00ff00");
        button.setStyle("-fx-font-size: 2em; ");
        button.setStyle("-fx-text-fill: #0000ff");

        var vbox = new VBox(button);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));

        Scene scene = new Scene(vbox,250,150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
