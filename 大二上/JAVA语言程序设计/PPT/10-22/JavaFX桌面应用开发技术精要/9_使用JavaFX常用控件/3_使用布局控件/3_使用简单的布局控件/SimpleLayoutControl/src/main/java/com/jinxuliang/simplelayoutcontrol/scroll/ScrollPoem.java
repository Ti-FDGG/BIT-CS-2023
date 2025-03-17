package com.jinxuliang.simplelayoutcontrol.scroll;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ScrollPoem extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException, URISyntaxException {

        VBox root = new VBox();
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");
        //从资源文件夹中读取一首古诗
        var url = getClass().getResource("poem.txt");
        var poem = Files.readString(Path.of(url.toURI()));
        //使用标签控件显示古诗内容
        Label lblPoem = new Label(poem);
        lblPoem.setFont(new Font(15));
        //居中显示
        lblPoem.setAlignment(Pos.TOP_CENTER);
        //利用数据绑定实现动态设置标签控件的宽度
        lblPoem.prefWidthProperty().bind(root.widthProperty().subtract(60));
        //将标签控件放到ScrollPane中，以便支持滚动查看
        ScrollPane sPane = new ScrollPane(lblPoem);
        sPane.setPannable(true);
        //将ScrollPane放到VBox中
        root.getChildren().add(sPane);


        Scene scene = new Scene(root, 250, 300);

        stage.setScene(scene);
        stage.setTitle("滚动查看");
        stage.show();
    }
}
