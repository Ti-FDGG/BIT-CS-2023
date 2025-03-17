package com.jinxuliang.usestage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DraggingStage extends Application {
    private Stage stage;
    private double dragOffsetX;
    private double dragOffsetY;
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Label msgLabel = new Label(
                "用鼠标拖动本窗体");
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> stage.close());
        VBox root = new VBox();
        root.setPadding(new Insets(20));
        root.setSpacing(20);
        root.setBackground(new Background(new BackgroundFill(Color.AQUA, CornerRadii.EMPTY, Insets.EMPTY)));
        root.getChildren().addAll(msgLabel, closeButton);
        Scene scene = new Scene(root, 300, 200);
        scene.setOnMousePressed(e -> handleMousePressed(e));
        scene.setOnMouseDragged(e -> handleMouseDragged(e));
        stage.setScene(scene);
        stage.setTitle("拖动窗体示例");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
    protected void handleMousePressed(MouseEvent e) {
        this.dragOffsetX = e.getScreenX() - stage.getX();
        this.dragOffsetY = e.getScreenY() - stage.getY();
    }
    protected void handleMouseDragged(MouseEvent e) {
        stage.setX(e.getScreenX() - this.dragOffsetX);
        stage.setY(e.getScreenY() - this.dragOffsetY);
    }
}
