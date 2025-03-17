package com.jinxuliang.usestage;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ScreenDetailsApp extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        ObservableList<Screen> screenList = Screen.getScreens();
        System.out.println("显示器个数: " + screenList.size());
        for (Screen screen : screenList) {
            print(screen);
        }
        Platform.exit();
    }

    public void print(Screen s) {
        System.out.println("分辨率（DPI）: " + s.getDpi());
        System.out.print("屏幕范围（Bounds）: ");
        Rectangle2D bounds = s.getBounds();
        print(bounds);
        System.out.print("屏幕可视化范围（Visual Bounds）: ");
        Rectangle2D visualBounds = s.getVisualBounds();
        print(visualBounds);
        System.out.println("-----------------------");
    }

    public void print(Rectangle2D r) {
        System.out.format("minX=%.2f, minY=%.2f, width=%.2f, height=%.2f%n",
                r.getMinX(), r.getMinY(),
                r.getWidth(), r.getHeight());
    }
}
