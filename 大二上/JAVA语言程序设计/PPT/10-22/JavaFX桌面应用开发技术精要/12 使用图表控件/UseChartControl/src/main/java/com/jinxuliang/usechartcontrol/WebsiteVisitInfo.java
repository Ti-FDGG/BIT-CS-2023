package com.jinxuliang.usechartcontrol;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WebsiteVisitInfo extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("网站访问方式统计");

        CategoryAxis xAxis    = new CategoryAxis();
        xAxis.setLabel("设备类型");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("访问次数");

        BarChart barChart = new BarChart(xAxis, yAxis);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("2021");

        dataSeries1.getData().add(new XYChart.Data("台式PC机", 100));
        dataSeries1.getData().add(new XYChart.Data("手机"  , 657));
        dataSeries1.getData().add(new XYChart.Data("笔记本电脑"  , 321));

        barChart.getData().add(dataSeries1);

        XYChart.Series dataSeries2 = new XYChart.Series();
        dataSeries2.setName("2022");

        dataSeries2.getData().add(new XYChart.Data("台式PC机", 80));
        dataSeries2.getData().add(new XYChart.Data("手机"  , 800));
        dataSeries2.getData().add(new XYChart.Data("笔记本电脑"  , 435));

        barChart.getData().add(dataSeries2);

        VBox vbox = new VBox(barChart);

        Scene scene = new Scene(vbox);

        primaryStage.setScene(scene);


        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
