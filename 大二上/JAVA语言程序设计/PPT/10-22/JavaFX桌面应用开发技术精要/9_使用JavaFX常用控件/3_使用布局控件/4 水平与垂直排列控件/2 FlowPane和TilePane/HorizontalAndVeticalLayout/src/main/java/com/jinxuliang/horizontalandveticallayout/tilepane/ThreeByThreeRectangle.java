package com.jinxuliang.horizontalandveticallayout.tilepane;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ThreeByThreeRectangle extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        TilePane tilePane = new TilePane();
        tilePane.setPrefColumns(3);
        tilePane.setPrefRows(3);
        tilePane.setTileAlignment(Pos.CENTER);
        tilePane.setPrefTileHeight(100);
        tilePane.setPrefTileWidth(100);

        tilePane.getChildren().addAll(
                new Rectangle(50, 50, Color.RED),
                new Rectangle(50, 50, Color.GREEN),
                new Rectangle(50, 50, Color.BLUE),
                new Rectangle(50, 50, Color.YELLOW),
                new Rectangle(50, 50, Color.CYAN),
                new Rectangle(50, 50, Color.PURPLE),
                new Rectangle(50, 50, Color.BROWN),
                new Rectangle(50, 50, Color.PINK),
                new Rectangle(50, 50, Color.ORANGE)
        );

        Scene scene = new Scene(tilePane);
        scene.setFill(Color.LIGHTGRAY);

        primaryStage.setTitle("3x3");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
