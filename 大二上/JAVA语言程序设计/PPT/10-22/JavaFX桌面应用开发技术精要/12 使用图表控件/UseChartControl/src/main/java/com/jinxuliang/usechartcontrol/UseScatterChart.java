package com.jinxuliang.usechartcontrol;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UseScatterChart extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("散点图示例");

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("雇员人数");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("员工平均工资");

        ScatterChart scatterChart = new ScatterChart(xAxis, yAxis);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("2020");

        dataSeries1.getData().add(new XYChart.Data( 1, 567));
        dataSeries1.getData().add(new XYChart.Data( 5, 612));
        dataSeries1.getData().add(new XYChart.Data(10, 800));
        dataSeries1.getData().add(new XYChart.Data(20, 780));
        dataSeries1.getData().add(new XYChart.Data(40, 810));
        dataSeries1.getData().add(new XYChart.Data(80, 850));

        scatterChart.getData().add(dataSeries1);

        VBox vbox = new VBox(scatterChart);

        Scene scene = new Scene(vbox);

        primaryStage.setScene(scene);


        primaryStage.show();
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}
