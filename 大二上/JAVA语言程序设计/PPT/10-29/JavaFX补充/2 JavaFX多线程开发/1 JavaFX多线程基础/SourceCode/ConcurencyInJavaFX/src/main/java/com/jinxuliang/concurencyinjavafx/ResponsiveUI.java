package com.jinxuliang.concurencyinjavafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ResponsiveUI extends Application {
    private final Model model;
    private View view;

    public static void main(String[] args) {
        launch(args);
    }

    public ResponsiveUI() {
        model = new Model();
    }

    @Override
    public void start(Stage stage) throws Exception {
        view = new View(model);
        hookupEvents();
        stage.setTitle("能及时响应的JavaFX应用");
        stage.setScene(view.scene);
        stage.show();
    }

    private void hookupEvents() {

        view.changeFillButton.setOnAction(actionEvent -> {
            final Paint fillPaint = model.getFillPaint();
            if (fillPaint.equals(Color.LIGHTGRAY)) {
                model.setFillPaint(Color.GRAY);
            } else {
                model.setFillPaint(Color.LIGHTGRAY);
            }
            //在独立的工作线程中执行任务
            Runnable task = () -> {
                try {
                    Thread.sleep(3000);
                    //Lambda表达式执行的任务，将会被推送到
                    //JavaFX application thread中执行
                    //因此，里面可以有直接访问UI控件的代码
                    Platform.runLater(() -> {
                        final Rectangle rect = view.rectangle;
                        double newArcSize =
                            rect.getArcHeight() < 20 ? 30 : 0;
                        rect.setArcWidth(newArcSize);
                        rect.setArcHeight(newArcSize);
                    });
                } catch (InterruptedException e) {
                }
            };
            new Thread(task).start();
        });

        view.changeStrokeButton.setOnAction(actionEvent -> {
            final Paint strokePaint = model.getStrokePaint();
            if (strokePaint.equals(Color.DARKGRAY)) {
                model.setStrokePaint(Color.BLACK);
            } else {
                model.setStrokePaint(Color.DARKGRAY);
            }
        });
    }

    private static class Model {
        private final ObjectProperty<Paint> fillPaint = new SimpleObjectProperty<>();
        private final ObjectProperty<Paint> strokePaint = new SimpleObjectProperty<>();

        private Model() {
            fillPaint.set(Color.LIGHTGRAY);
            strokePaint.set(Color.DARKGRAY);
        }

        final public Paint getFillPaint() {
            return fillPaint.get();
        }

        final public void setFillPaint(Paint value) {
            this.fillPaint.set(value);
        }

        final public Paint getStrokePaint() {
            return strokePaint.get();
        }

        final public void setStrokePaint(Paint value) {
            this.strokePaint.set(value);
        }

        final public ObjectProperty<Paint> fillPaintProperty() {
            return fillPaint;
        }

        final public ObjectProperty<Paint> strokePaintProperty() {
            return strokePaint;
        }
    }

    private static class View {
        public Rectangle rectangle;
        public Button changeFillButton;
        public Button changeStrokeButton;
        public HBox buttonHBox;
        public Scene scene;

        private View(Model model) {
            rectangle = new Rectangle(200, 200);
            rectangle.setStrokeWidth(10);
            rectangle.fillProperty().bind(model.fillPaintProperty());
            rectangle.strokeProperty().bind(model.strokePaintProperty());

            changeFillButton = new Button("更改填充");
            changeStrokeButton = new Button("更改边框");

            buttonHBox = new HBox(10, changeFillButton, changeStrokeButton);
            buttonHBox.setPadding(new Insets(10, 10, 10, 10));
            buttonHBox.setAlignment(Pos.CENTER);

            BorderPane root = new BorderPane(rectangle, null, null, buttonHBox, null);
            root.setPadding(new Insets(10, 10, 10, 10));

            scene = new Scene(root,350,300);
        }
    }
}
