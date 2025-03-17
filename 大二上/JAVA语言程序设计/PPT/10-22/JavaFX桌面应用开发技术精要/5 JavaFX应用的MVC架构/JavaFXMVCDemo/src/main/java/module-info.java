module com.jinxuliang.javafxmvcdemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.javafxmvcdemo to javafx.fxml;
    exports com.jinxuliang.javafxmvcdemo;
}