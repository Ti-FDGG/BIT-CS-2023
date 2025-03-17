package com.jinxuliang.usechartcontrol;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MonthlyBudget extends Application {

    @Override
    public void start(Stage primaryStage) {

        Label lblInfo = new Label("lblInfo");

        //填充数据
        ObservableList<PieChart.Data> pieData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Electric", 125),
                        new PieChart.Data("Groceries", 235),
                        new PieChart.Data("Entertainment", 200),
                        new PieChart.Data("Cell Phone", 80));
        //绑定显示
        PieChart budget = new PieChart(pieData);
        budget.setTitle("Monthly Expenses");
        //响应事件
        var datas = budget.getData();
        for (var data : datas) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
                lblInfo.setText(data.getName() + " : " + data.getPieValue());
            });
        }


        VBox root = new VBox();
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));

        root.getChildren().addAll(budget, lblInfo);

        Scene scene = new Scene(root, 500, 450);

        primaryStage.setTitle("Monthly Budget");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
