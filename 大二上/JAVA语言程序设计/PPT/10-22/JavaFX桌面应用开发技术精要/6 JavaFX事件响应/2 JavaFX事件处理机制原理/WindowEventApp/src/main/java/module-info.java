module com.jinxuliang.windoweventapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.windoweventapp to javafx.fxml;
    exports com.jinxuliang.windoweventapp;
}