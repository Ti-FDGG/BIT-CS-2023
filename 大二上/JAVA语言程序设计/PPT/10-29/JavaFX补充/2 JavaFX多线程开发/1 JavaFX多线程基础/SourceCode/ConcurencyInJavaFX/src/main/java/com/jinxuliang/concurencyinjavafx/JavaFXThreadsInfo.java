package com.jinxuliang.concurencyinjavafx;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Map;

public class JavaFXThreadsInfo extends Application
    implements EventHandler<ActionEvent>, ChangeListener<Number> {

    private final Model model;
    private View view;

    public static void main(String[] args) {
        launch(args);
    }

    public JavaFXThreadsInfo() {
        model = new Model();
    }

    @Override
    public void start(Stage stage) throws Exception {
        view = new View(model);
        hookupEvents();
        stage.setTitle("JavaFX应用线程清单");
        stage.setScene(view.scene);
        stage.show();
    }

    private void hookupEvents() {
    	//单击按钮时，调用handle()方法
        view.updateButton.setOnAction(this);
        //用户选中不同选项时，调用changed()方法
        view.threadNames.getSelectionModel()
        .selectedIndexProperty()
        .addListener(this);
    }

    @Override
    public void changed(ObservableValue<? extends Number> observableValue,
                        Number oldValue, Number newValue) {
        int index = (Integer) newValue;
        if (index >= 0) {
            view.stackTrace.setText(model.stackTraces.get(index));
        }
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        model.update();
    }

    public static class Model {
        public ObservableList<String> threadNames;
        public ObservableList<String> stackTraces;

        public Model() {
            threadNames = FXCollections.observableArrayList();
            stackTraces = FXCollections.observableArrayList();
            update();
        }

        public void update() {
            threadNames.clear();
            stackTraces.clear();
            //提取所有的运行状态线程信息
            final Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
            for (Map.Entry<Thread, StackTraceElement[]> entry : map.entrySet()) {
                threadNames.add("\"" + entry.getKey().getName() + "\"");
                stackTraces.add(formatStackTrace(entry.getValue()));
            }
        }

        private String formatStackTrace(StackTraceElement[] value) {
            StringBuilder sb = new StringBuilder("线程堆栈信息: \n");
            for (StackTraceElement stackTraceElement : value) {
                sb.append("    at ").append(stackTraceElement.toString()).append("\n");
            }
            return sb.toString();
        }
    }

    private static class View {
        public ListView<String> threadNames;
        public TextArea stackTrace;
        public Button updateButton;
        public Scene scene;

        private View(Model model) {
            threadNames = new ListView<>(model.threadNames);
            stackTrace = new TextArea();
            updateButton = new Button("刷新");
            VBox vBox = new VBox(10, threadNames, stackTrace, updateButton);
            vBox.setPadding(new Insets(10, 10, 10, 10));
            scene = new Scene(vBox, 440, 640);
        }
    }
}