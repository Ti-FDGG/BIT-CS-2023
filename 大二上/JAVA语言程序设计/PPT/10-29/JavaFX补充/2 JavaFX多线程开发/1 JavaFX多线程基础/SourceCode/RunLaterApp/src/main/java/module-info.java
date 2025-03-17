module com.jinxuliang.runlaterapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.runlaterapp to javafx.fxml;
    exports com.jinxuliang.runlaterapp;
}