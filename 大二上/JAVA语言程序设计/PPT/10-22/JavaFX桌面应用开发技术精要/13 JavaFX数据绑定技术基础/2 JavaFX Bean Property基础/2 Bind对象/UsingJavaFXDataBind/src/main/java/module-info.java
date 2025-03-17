module com.jinxuliang.usingjavafxdatabind {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.usingjavafxdatabind to javafx.fxml;
    exports com.jinxuliang.usingjavafxdatabind;
}