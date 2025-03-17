module com.jinxuliang.closeotherstage {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.closeotherstage to javafx.fxml;
    exports com.jinxuliang.closeotherstage;
}