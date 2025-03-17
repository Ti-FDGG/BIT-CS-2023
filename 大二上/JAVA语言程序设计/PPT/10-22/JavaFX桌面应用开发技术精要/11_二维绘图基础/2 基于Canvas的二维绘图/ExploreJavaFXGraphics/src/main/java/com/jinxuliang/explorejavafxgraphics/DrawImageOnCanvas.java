package com.jinxuliang.explorejavafxgraphics;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DrawImageOnCanvas extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(10));

        Canvas canvas = new Canvas(610, 450);
        drawOnCanvas(canvas);

        vBox.getChildren().addAll(canvas);
        Scene scene = new Scene(vBox);

        primaryStage.setScene(scene);
        primaryStage.setTitle("基于Canvas绘图");
        primaryStage.show();
    }

    private void drawOnCanvas(Canvas canvas) {
        //获取绘图上下文
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //从资源文件中读取图片
        String image = getClass().getResource("lake.jpg").toExternalForm();
        Image moon = new Image(image);
        //在Canvas的左上角，按照宽600像素，高379像素绘图，这实际上就是图片的原始分辨率
        gc.drawImage(moon, 0, 0, 600, 379);
        //使用宽度为4像素的蓝色线条，绘制一条分割线
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(4d);
        gc.strokeLine(0,390,600,390);
        //在（250，435）处绘制图片标题
        gc.setFont(new Font(40));
        gc.setFill(Color.CADETBLUE);
        gc.fillText("湖光山色", 250, 435);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

