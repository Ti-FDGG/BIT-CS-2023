module com.example.javafxmvvmdemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.javafxmvvmdemo to javafx.fxml;
    exports com.jinxuliang.javafxmvvmdemo;
    exports com.jinxuliang.javafxmvvmdemo.model;
    opens com.jinxuliang.javafxmvvmdemo.model to javafx.fxml;
}