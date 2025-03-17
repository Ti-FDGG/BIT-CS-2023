module com.jinxuliang.javafxdatabindingdemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.javafxdatabindingdemo to javafx.fxml;
    opens com.jinxuliang.javafxdatabindingdemo.mynumber to javafx.fxml;
    opens com.jinxuliang.javafxdatabindingdemo.bidirectionbindtoslider to javafx.fxml;
    opens com.jinxuliang.javafxdatabindingdemo.viewbindtocontroller to javafx.fxml;

    exports com.jinxuliang.javafxdatabindingdemo;
    exports com.jinxuliang.javafxdatabindingdemo.mynumber;
    exports com.jinxuliang.javafxdatabindingdemo.bidirectionbindtoslider;
    exports com.jinxuliang.javafxdatabindingdemo.viewbindtocontroller;
}