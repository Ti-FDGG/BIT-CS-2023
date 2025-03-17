package com.jinxuliang.horizontalandveticallayout.tilepane;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CirclesInTilePane extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        //两行两列，自动居中
        TilePane tilePane = new TilePane();
        tilePane.setPrefColumns(2);
        tilePane.setPrefRows(2);
        tilePane.setTileAlignment(Pos.CENTER);

        Circle redCircle = new Circle(50, Color.RED);
        Circle greenCircle = new Circle(50, Color.GREEN);
        Circle blueCircle = new Circle(50, Color.BLUE);
        Circle yellowCircle = new Circle(50, Color.YELLOW);

        List<Circle> circles = new ArrayList<>();
        circles.add(redCircle);
        circles.add(greenCircle);
        circles.add(blueCircle);
        circles.add(yellowCircle);

        circles.forEach(CirclesInTilePane::changeColorInClick);

        Button btn = new Button("打乱顺序");
        btn.setOnAction(e -> {
            Collections.shuffle(circles);
            tilePane.getChildren().clear();
            tilePane.getChildren().addAll(circles);
            tilePane.getChildren().add(btn);
        });

        tilePane.getChildren().addAll(circles);
        tilePane.getChildren().add(btn);
        tilePane.setHgap(10);
        tilePane.setVgap(10);
        tilePane.setPadding(new Insets(10));

        Scene scene = new Scene(tilePane);

        scene.setOnKeyPressed(
                (evt) -> {
                    if (evt.getCode().equals(KeyCode.S)) {
                        Collections.shuffle(circles);
                        tilePane.getChildren().clear();
                        tilePane.getChildren().addAll(circles);
                    }
                }
        );

        primaryStage.setTitle("TileApp");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private static void changeColorInClick(Circle c) {
        c.setOnMouseClicked(e -> {
            Boolean selected = (Boolean) c.getProperties().get("selected");
            if (selected == null || selected == Boolean.FALSE) {
                c.setOpacity(0.3d);
                c.getProperties().put("selected", Boolean.TRUE);
            } else {
                c.setOpacity(1.0d);
                c.getProperties().put("selected", Boolean.FALSE);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}
