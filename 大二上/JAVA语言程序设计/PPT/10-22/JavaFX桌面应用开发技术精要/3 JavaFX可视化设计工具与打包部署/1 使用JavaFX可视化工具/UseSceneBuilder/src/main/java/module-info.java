module com.jinxuliang.usescenebuilder {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.usescenebuilder to javafx.fxml;
    exports com.jinxuliang.usescenebuilder;
}