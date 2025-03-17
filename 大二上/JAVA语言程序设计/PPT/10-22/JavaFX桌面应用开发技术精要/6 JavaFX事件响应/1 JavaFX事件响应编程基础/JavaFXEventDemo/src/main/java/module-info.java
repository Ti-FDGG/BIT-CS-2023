module com.example.javafxeventdemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.javafxeventdemo to javafx.fxml;
    exports com.jinxuliang.javafxeventdemo;
}