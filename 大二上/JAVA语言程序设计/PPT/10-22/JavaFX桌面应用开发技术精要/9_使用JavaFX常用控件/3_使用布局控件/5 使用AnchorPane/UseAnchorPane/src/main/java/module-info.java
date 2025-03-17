module com.jinxuliang.useanchorpane {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.useanchorpane to javafx.fxml;
    exports com.jinxuliang.useanchorpane;
    exports com.jinxuliang.useanchorpane.other;
    opens com.jinxuliang.useanchorpane.other to javafx.fxml;
}