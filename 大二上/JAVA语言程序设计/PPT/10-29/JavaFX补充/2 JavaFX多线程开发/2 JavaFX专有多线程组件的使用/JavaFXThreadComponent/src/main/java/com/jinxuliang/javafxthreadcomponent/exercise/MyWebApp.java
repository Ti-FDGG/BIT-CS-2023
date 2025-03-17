package com.jinxuliang.javafxthreadcomponent.exercise;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MyWebApp extends Application {
    private HBox bottomControls;
    private ProgressBar pb;
    private Label messageLabel;

    private TextField tfURL;

    private TextArea contents;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent p = createMainView();

        Scene scene = new Scene(p);

        primaryStage.setTitle("多线程访问互联网");
        primaryStage.setWidth(667);
        primaryStage.setHeight(376);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Parent createMainView() {

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        HBox topControls = new HBox();
        topControls.setAlignment(Pos.CENTER_LEFT);
        topControls.setSpacing(4);

        Label label = new Label("输入网址：");
        tfURL = new TextField();
        HBox.setHgrow(tfURL, Priority.ALWAYS);
        Button btnGetHTML = new Button("获取网页");
        btnGetHTML.setOnAction(this::getHTML);
        topControls.getChildren().addAll(label, tfURL, btnGetHTML);

        contents = new TextArea();
        VBox.setVgrow(contents, Priority.ALWAYS);

        bottomControls = new HBox();

        bottomControls.setSpacing(4);
        HBox.setMargin(bottomControls, new Insets(4));

        pb = new ProgressBar();
        pb.setVisible(false);
        messageLabel = new Label("请输入一个网址");
        bottomControls.getChildren().addAll(messageLabel,pb);

        vbox.getChildren().addAll(topControls, contents, bottomControls);

        return vbox;
    }

    public void getHTML(ActionEvent evt) {

        String url = tfURL.getText();

        Task<String> task = new Task<>() {

            @Override
            protected String call() throws Exception {
                updateMessage("访问： " + url);
                updateProgress(0.5d, 1.0d);
                HttpURLConnection connection = null;
                BufferedInputStream is = null;
                byte[] data = null;
                try {
                    connection = (HttpURLConnection) new URL(url).openConnection();
                    updateProgress(0.6d, 1.0d);
                    is = new BufferedInputStream(connection.getInputStream());
                    data = is.readAllBytes();

                } finally {
                    if (is != null) {
                        is.close();
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }

                //updateMessage("数据接收完毕");
                updateProgress(1.0d, 1.0d);

                return new String(data, StandardCharsets.UTF_8);
            }

            @Override
            protected void succeeded() {
                contents.setText(getValue());
                updateMessage("数据提取完毕");
            }

            @Override
            protected void failed() {
//                Alert alert = new Alert(Alert.AlertType.ERROR, getException().getMessage());
//                alert.showAndWait();
                updateMessage(getException().getMessage());
            }
        };

        pb.visibleProperty().bind(task.runningProperty());
        pb.progressProperty().bind(task.progressProperty());
        messageLabel.textProperty().bind(task.messageProperty());

        new Thread(task).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
