module com.jinxuliang.fxmlbasic {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.fxmlbasic to javafx.fxml;
    exports com.jinxuliang.fxmlbasic;
}