package com.jinxuliang.usestage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StageOpacity extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(10));


        Text lblInfo = new Text();
        lblInfo.setFont(new Font(20));
        lblInfo.setText(primaryStage.getOpacity() + "");

        Button button = new Button("切换透明度");
        button.setOnAction(e -> {
            var currentOpacity = primaryStage.getOpacity();
            var newOpacity = currentOpacity + 0.1;
            if (newOpacity > 1.0) {
                newOpacity = 0.3;
            }

            primaryStage.setOpacity(newOpacity);
            currentOpacity = newOpacity;
            lblInfo.setText(currentOpacity + "");
        });

        vBox.getChildren().addAll(lblInfo, button);
        Scene scene = new Scene(vBox, 300, 150);

        primaryStage.setScene(scene);
        primaryStage.setTitle("动态调整透明度");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
