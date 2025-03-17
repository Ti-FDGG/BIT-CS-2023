package com.jinxuliang.eventhandlerandfilterdemo;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    //注入视图中的三个控件
    @FXML
    BorderPane borderPane;
    @FXML
    VBox vBox;
    @FXML
    Circle circle;

    //统一的事件处理方法，输出event对象的相关属性值
    //由参数决定是否消费此事件
    private void handleAndConsumed(Event event, boolean consume, String message) {
        if (consume) {
            event.consume();
        }
        String info = String.format(
                "EventType:%s Source:%s Target:%s isConsumed:%b",
                event.getEventType(),
                event.getSource().getClass().getSimpleName(),
                event.getTarget().getClass().getSimpleName(),
                event.isConsumed());
        System.out.println(message + ":" + info);
    }

    //为三个节点附加EventFilter和EventHandler
    //通过修改handleAndConsumed()方法的参数值，可以观察事件派发链的实际运行原理
     void initListener() {

        borderPane.addEventFilter(MouseEvent.MOUSE_PRESSED,
                event -> handleAndConsumed(event, false, "BorderPaneEventFilter"));
        borderPane.addEventHandler(MouseEvent.MOUSE_PRESSED,
                event -> handleAndConsumed(event, false, "BorderPaneEventHandler"));

        vBox.addEventFilter(MouseEvent.MOUSE_PRESSED,
                event -> handleAndConsumed(event, false, "VBoxEventFilter"));
        vBox.addEventHandler(MouseEvent.MOUSE_PRESSED,
                event -> handleAndConsumed(event, false, "VBoxEventHandler"));

        circle.addEventFilter(MouseEvent.MOUSE_PRESSED,
                event -> handleAndConsumed(event, false, "CircleEventFilter"));
        circle.addEventHandler(MouseEvent.MOUSE_PRESSED,
                event -> handleAndConsumed(event, false, "CircleEventHandler"));

//如果想测试响应父事件，取消以下代码的注释
//		borderPane.addEventFilter(MouseEvent.ANY,
//				event->handleAndConsumed(event, true, "BorderPaneEventFilter"));

//		circle.addEventHandler(MouseEvent.ANY,
//				event->handleAndConsumed(event, true, "CircleEventHandler"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initListener();
    }
}