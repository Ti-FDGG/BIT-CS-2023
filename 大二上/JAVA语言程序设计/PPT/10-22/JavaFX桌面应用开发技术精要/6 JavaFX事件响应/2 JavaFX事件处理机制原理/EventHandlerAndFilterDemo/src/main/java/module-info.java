module com.jinxuliang.eventhandlerandfilterdemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.eventhandlerandfilterdemo to javafx.fxml;
    exports com.jinxuliang.eventhandlerandfilterdemo;
}