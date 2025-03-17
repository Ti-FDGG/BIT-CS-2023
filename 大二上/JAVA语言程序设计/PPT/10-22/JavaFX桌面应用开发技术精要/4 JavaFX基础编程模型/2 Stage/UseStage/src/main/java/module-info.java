module com.jinxuliang.usestage {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.usestage to javafx.fxml;
    exports com.jinxuliang.usestage;
}