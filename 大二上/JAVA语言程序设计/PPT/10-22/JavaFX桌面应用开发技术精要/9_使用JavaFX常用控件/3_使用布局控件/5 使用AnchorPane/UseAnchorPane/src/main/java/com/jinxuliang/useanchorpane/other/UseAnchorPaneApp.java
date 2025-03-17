package com.jinxuliang.useanchorpane.other;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class UseAnchorPaneApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane ap = new AnchorPane();

        // upper-right sign out control
        Hyperlink signoutLink = new Hyperlink("退出登录");

        ap.getChildren().add( signoutLink );

        AnchorPane.setTopAnchor( signoutLink, 10.0d );
        AnchorPane.setRightAnchor( signoutLink, 10.0d );

        // lower-left status label
        Label statusLabel = new Label("程序状态信息");
        ap.getChildren().add( statusLabel );

        AnchorPane.setBottomAnchor( statusLabel, 10.0d );
        AnchorPane.setLeftAnchor( statusLabel, 10.0d );

        // lower-right connection status control
        Circle circle = new Circle();
        circle.setFill(Color.GREEN );
        circle.setRadius(10);

        Label connLabel = new Label("已连接");

        HBox connHBox = new HBox();
        connHBox.setSpacing( 4.0d );
        connHBox.setAlignment(Pos.BOTTOM_RIGHT);
        connHBox.getChildren().addAll( connLabel, circle );

        AnchorPane.setBottomAnchor( connHBox, 10.0d );
        AnchorPane.setRightAnchor( connHBox, 10.0d );

        ap.getChildren().add( connHBox );

        //中部的TextArea，设置了四个方向上的Anchor
        TextArea ta = new TextArea();
        ta.setWrapText(true);
        ta.setText("""
                整个界面的顶层容器是AnchorPane。
                                
                上部是一个Hyperlink控件，其Anchor设置为top和right
                                
                中部是一个TextArea控件
                                
                左下角是一个Label，其Anchor设置为Bottom和Left
                                
                右下角是一个嵌套的容器，HBox包容一个标签和Circle，它的Anchor设置为bottom和Right
                """);
        ap.getChildren().add( ta );

        AnchorPane.setTopAnchor( ta, 40.0d );
        AnchorPane.setBottomAnchor( ta, 40.0d );
        AnchorPane.setRightAnchor( ta, 10.0d );
        AnchorPane.setLeftAnchor( ta, 10.0d );

        Scene scene = new Scene(ap);

        primaryStage.setTitle("AnchorPane综合示例");
        primaryStage.setScene( scene );
        primaryStage.setWidth(568);
        primaryStage.setHeight(320);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
