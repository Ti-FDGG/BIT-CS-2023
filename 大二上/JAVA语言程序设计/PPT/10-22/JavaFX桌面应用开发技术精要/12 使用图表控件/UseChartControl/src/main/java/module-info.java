module com.jinxuliang.usechartcontrol {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.usechartcontrol to javafx.fxml;
    exports com.jinxuliang.usechartcontrol;
}