package com.jinxuliang.usechartcontrol;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UseAreaChart extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("AreaChart示例");

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("雇员人数");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("员工平均工资");

        AreaChart areaChart = new AreaChart(xAxis, yAxis);

        addDataToAreaChart(areaChart);

        VBox vbox = new VBox(areaChart);

        Scene scene = new Scene(vbox);

        primaryStage.setScene(scene);


        primaryStage.show();
    }

    private static void addDataToAreaChart(AreaChart areaChart) {
        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("2020");

        dataSeries1.getData().add(new XYChart.Data( 1, 567));
        dataSeries1.getData().add(new XYChart.Data( 5, 612));
        dataSeries1.getData().add(new XYChart.Data(10, 800));
        dataSeries1.getData().add(new XYChart.Data(20, 780));
        dataSeries1.getData().add(new XYChart.Data(40, 810));
        dataSeries1.getData().add(new XYChart.Data(80, 850));

        areaChart.getData().add(dataSeries1);
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}
