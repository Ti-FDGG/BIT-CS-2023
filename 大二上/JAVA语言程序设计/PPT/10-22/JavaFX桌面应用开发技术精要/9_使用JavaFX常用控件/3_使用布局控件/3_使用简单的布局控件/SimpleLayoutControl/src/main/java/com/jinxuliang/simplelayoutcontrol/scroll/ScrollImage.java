package com.jinxuliang.simplelayoutcontrol.scroll;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScrollImage extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        //从资源文件夹中读取图片
        String image = getClass().getResource("image.jpg").toExternalForm();
        Image grassland = new Image(image);
        ImageView imageView = new ImageView(grassland);
        //将ImageView加入ScrollPane，再加入VBox
        ScrollPane scrollPane = new ScrollPane(imageView);
        vBox.getChildren().add(scrollPane);
        //480*320的场景大小，明显小于图片，所以，滚动条是必然出现的
        Scene scene = new Scene(vBox, 480, 320);
        primaryStage.setScene(scene);
        primaryStage.setTitle("滚动查看大图片");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
