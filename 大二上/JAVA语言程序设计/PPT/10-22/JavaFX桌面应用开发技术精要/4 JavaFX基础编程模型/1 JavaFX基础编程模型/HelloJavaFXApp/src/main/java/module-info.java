module com.jinxuliang.hellojavafxapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.hellojavafxapp to javafx.fxml;
    exports com.jinxuliang.hellojavafxapp;
}