package com.jinxuliang.hellojavafxapp;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class HostServiceApplication extends Application {
    @Override
    public void start(Stage stage)  {

        String baiduURL = "https://baidu.com";
        Button openURLButton = new Button("访问百度");
        openURLButton.setOnAction(e ->
                getHostServices().showDocument(baiduURL));

        VBox root = new VBox();
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(openURLButton);

        //获取应用相关的信息，追加到VBox控件中
        Map<String, String> hostdetails = getHostDetails();
        for (Map.Entry<String, String> entry :
                hostdetails.entrySet()) {
            String desc = entry.getKey() + ": " +
                    entry.getValue();
            root.getChildren().add(new Label(desc));
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("使用getHostServices");
        stage.show();
    }


    protected Map<String, String> getHostDetails() {
        Map<String, String> map = new HashMap<>();
        HostServices host = this.getHostServices();
        //获取jar文件所在的文件夹
        String codeBase = host.getCodeBase();
        if("".equals(codeBase)){
            map.put("CodeBase", "此应用不是以jar包方式运行的");
        }else{
            map.put("CodeBase", codeBase);
        }
        //返回当前文件夹
        String documentBase = host.getDocumentBase();
        map.put("DocumentBase", documentBase);
        //获取图片资源所在的文件夹
        String splashImageURI =
                host.resolveURI(documentBase, "image.jpg");
        map.put("Image URI", splashImageURI);
        return map;
    }

    public static void main(String[] args) {
        launch();
    }
}