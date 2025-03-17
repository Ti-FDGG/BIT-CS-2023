module com.jinxuliang.databindpropertyeventdemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.databindpropertyeventdemo to javafx.fxml;
    exports com.jinxuliang.databindpropertyeventdemo;
}