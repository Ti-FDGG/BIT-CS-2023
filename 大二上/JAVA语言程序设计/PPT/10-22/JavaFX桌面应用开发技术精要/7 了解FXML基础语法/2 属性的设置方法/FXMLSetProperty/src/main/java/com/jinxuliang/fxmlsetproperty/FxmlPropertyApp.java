package com.jinxuliang.fxmlsetproperty;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class FxmlPropertyApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //指定加载中文资源
        Locale locale = new Locale("zh");
        //这里也可以指定使用英文资源
        locale = new Locale("en");
        Locale.setDefault(locale);
        //注意一下资源包的位置
        var resourceBundle = ResourceBundle.getBundle(
                "com.jinxuliang.fxmlsetproperty.infos");
        FXMLLoader fxmlLoader = new FXMLLoader(
                FxmlPropertyApp.class.getResource("main-view.fxml"),
                resourceBundle
        );

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("设置fxml元素属性");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}