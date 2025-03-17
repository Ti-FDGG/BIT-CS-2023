package com.jinxuliang.multiwindowdemo;

import java.io.IOException;

import com.jinxuliang.multiwindowdemo.controllers.*;
import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;

public class MultiWindowApp extends Application {
    private Stage primaryStage;
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showMainWindow();
    }
    // 显示主窗体
    public void showMainWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    MultiWindowApp.class.getResource("MainWindowView.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            MainWindowController controller = loader.getController();
            controller.setMain(this);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("主窗体");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 打开一个新的窗体
    public void openNewWindow() {
        try {
            //从资源中加载视图
            FXMLLoader loader = new FXMLLoader(
                    MultiWindowApp.class.getResource("NewWindowView.fxml"));
            //构建控件树
            AnchorPane root = loader.load();
            //如果需要的话，可以在此获取视图关联的控件器的引用（可选）
            NewWindowController controller = loader.getController();
            //获取控制器引用之后，可以调用它所定义的公有方法，
            // 生成场景图
            Scene scene = new Scene(root);
            // 创建舞台，并设置它要使用的场景
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.setTitle("新开的窗体");
            // 显示舞台，窗体出现在屏幕上
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 更改主窗体的内容
    public void changeMainWindowContent() {

        try {
            //加载新的FXML文档
            FXMLLoader loader = new FXMLLoader(
                    MultiWindowApp.class.getResource("MainWindow2View.fxml"));
            AnchorPane root = (AnchorPane) loader.load();

            MainWindow2Controller controller = loader.getController();
            controller.setMain(this);

            //生成新的场景
            Scene scene = new Scene(root);
            //更新当前Stage的场景
            primaryStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 主窗体向从窗体传送信息
    public void mainToOther(String message) {

        try {
            FXMLLoader loader = new FXMLLoader(
                    MultiWindowApp.class.getResource("OtherWindowView.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            Stage secondStage = new Stage();
            // 启用模态对话框
            secondStage.initModality(Modality.APPLICATION_MODAL);
            OtherWindowController controller = loader.getController();
            controller.init(this, secondStage);
            //发送信息
            controller.setMessage(message);
            //显示从窗体
            Scene scene = new Scene(root);
            secondStage.setScene(scene);
            secondStage.setTitle("从窗体");
            secondStage.show();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public void thirdWindow(MainWindowController mainWindowController) {

        try {
            FXMLLoader loader = new FXMLLoader(MultiWindowApp.class.getResource("ThirdWindowView.fxml"));
            GridPane root = (GridPane) loader.load();
            Stage thirdStage = new Stage();
            // 启用模态对话框
            thirdStage.initModality(Modality.APPLICATION_MODAL);

            ThirdWindowController controller = loader.getController();
            controller.init(mainWindowController, thirdStage);

            Scene scene = new Scene(root);
            thirdStage.setScene(scene);
            thirdStage.setTitle("从窗体");
            thirdStage.show();

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
