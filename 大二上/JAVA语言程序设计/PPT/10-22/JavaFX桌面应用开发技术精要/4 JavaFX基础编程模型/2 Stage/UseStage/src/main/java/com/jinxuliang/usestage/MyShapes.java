package com.jinxuliang.usestage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MyShapes extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Ellipse ellipse = new Ellipse(110, 70);
        ellipse.setFill(Color.LIGHTBLUE);
        Text text = new Text("My Shapes");
        text.setFont(new Font("Arial Bold", 24));
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(ellipse, text);
        Scene scene = new Scene(stackPane, 350, 230,
                Color.LIGHTYELLOW);
        stage.setTitle("MyShapes with JavaFX");
        stage.setScene(scene);
        stage.show();
    }
}
