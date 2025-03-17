package com.jinxuliang.usechartcontrol;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class QuarterlySales extends Application {
    
    @Override
    public void start(Stage primaryStage) {
     
        CategoryAxis hAxis = new CategoryAxis();
        hAxis.setLabel("销售员");
        
        NumberAxis vAxis = new NumberAxis();
        vAxis.setLabel("轿车销售额");
        
        BarChart<String, Number> barChartForSales = new BarChart<>(hAxis, vAxis);
        barChartForSales.setTitle("第一季度销售报告");

        fillData(barChartForSales);

        FlowPane root = new FlowPane();
        root.getChildren().add(barChartForSales);
        
        Scene scene = new Scene(root, 560, 450);
        
        primaryStage.setTitle("季度销售业绩汇报");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //给柱状图添加数据
    private static void fillData(BarChart<String, Number> barChartForSales) {
        XYChart.Series<String,Number> jan = new XYChart.Series<>();
        XYChart.Series<String,Number> feb = new XYChart.Series<>();
        XYChart.Series<String,Number> mar = new XYChart.Series<>();
        jan.setName("一月");
        feb.setName("二月");
        mar.setName("三月");
        jan.getData().add(new XYChart.Data<String, Number>("张明",120000));
        jan.getData().add(new XYChart.Data<String, Number>("李丽",100000));
        feb.getData().add(new XYChart.Data<String, Number>("张明",90000));
        feb.getData().add(new XYChart.Data<String, Number>("李丽",50000));
        mar.getData().add(new XYChart.Data<String, Number>("张明",55000));
        mar.getData().add(new XYChart.Data<String, Number>("李丽",130000));
        //给柱状图添加每月销售数据
        barChartForSales.getData().add(jan);
        barChartForSales.getData().add(feb);
        barChartForSales.getData().add(mar);
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
