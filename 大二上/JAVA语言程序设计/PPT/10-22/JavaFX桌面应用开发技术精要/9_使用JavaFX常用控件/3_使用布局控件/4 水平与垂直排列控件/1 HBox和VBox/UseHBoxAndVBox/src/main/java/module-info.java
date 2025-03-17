module com.jinxuliang.usehboxandvbox {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.usehboxandvbox to javafx.fxml;
    exports com.jinxuliang.usehboxandvbox;
}