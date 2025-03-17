package com.jinxuliang.usechartcontrol;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UseLineChart extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("折线图示例");

        //设定X轴和Y轴
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("雇员人数");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("公司总收入（万元）");
        LineChart lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.getData().addAll(getSeries1(), getSeries2());

        //获取第一个数据系列
        var firstSeries = (XYChart.Series<String, Integer>) lineChart.getData().get(0);
        for (var data : firstSeries.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
                Tooltip.install(data.getNode(),
                        new Tooltip("X:" + data.getXValue() + " Y:" + data.getYValue()));
            });
        }


        VBox vbox = new VBox(lineChart);

        Scene scene = new Scene(vbox);

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private static XYChart.Series<String, Integer> getSeries1() {
        XYChart.Series<String, Integer> dataSeries = new XYChart.Series<>();
        dataSeries.setName("2021");
        dataSeries.getData().add(new XYChart.Data<>("10人以下", 567));
        dataSeries.getData().add(new XYChart.Data<>("10~20人", 612));
        dataSeries.getData().add(new XYChart.Data<>("20~50人", 800));
        dataSeries.getData().add(new XYChart.Data<>("50~100人", 1780));
        dataSeries.getData().add(new XYChart.Data<>("100~200人", 2810));
        dataSeries.getData().add(new XYChart.Data<>("200人以上", 5850));
        return dataSeries;
    }

    private static XYChart.Series<String, Integer> getSeries2() {
        XYChart.Series<String, Integer> dataSeries = new XYChart.Series<>();
        dataSeries.setName("2022");
        dataSeries.getData().add(new XYChart.Data<>("10人以下", 83));
        dataSeries.getData().add(new XYChart.Data<>("10~20人", 273));
        dataSeries.getData().add(new XYChart.Data<>("20~50人", 192));
        dataSeries.getData().add(new XYChart.Data<>("50~100人", 902));
        dataSeries.getData().add(new XYChart.Data<>("100~200人", 1345));
        dataSeries.getData().add(new XYChart.Data<>("200人以上", 4863));


        return dataSeries;
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}
