module com.jinxuliang.multiwindowdemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.multiwindowdemo to javafx.fxml;

    exports com.jinxuliang.multiwindowdemo;
    exports com.jinxuliang.multiwindowdemo.controllers;
    opens com.jinxuliang.multiwindowdemo.controllers to javafx.fxml;
}