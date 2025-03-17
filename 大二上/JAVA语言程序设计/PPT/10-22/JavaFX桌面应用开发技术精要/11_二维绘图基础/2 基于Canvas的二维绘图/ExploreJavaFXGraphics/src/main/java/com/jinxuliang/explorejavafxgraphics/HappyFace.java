package com.jinxuliang.explorejavafxgraphics;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class HappyFace extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        var scene = new Scene(root);
        var canvas = new Canvas(400, 300);
        root.getChildren().add(canvas);
        stage.setScene(scene);
        stage.setTitle("笑脸相迎");

        var gc = canvas.getGraphicsContext2D();
        gc.strokeOval(100, 50, 200, 200);
        gc.fillOval(155, 100, 10, 20);
        gc.fillOval(230, 100, 10, 20);
        gc.strokeArc(150, 160, 100, 50, 180, 180, ArcType.OPEN);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
