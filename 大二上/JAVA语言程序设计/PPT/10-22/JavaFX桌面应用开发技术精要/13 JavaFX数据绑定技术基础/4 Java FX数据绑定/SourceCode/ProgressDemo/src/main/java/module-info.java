module com.jinxuliang.progressdemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.progressdemo to javafx.fxml;
    exports com.jinxuliang.progressdemo;
}