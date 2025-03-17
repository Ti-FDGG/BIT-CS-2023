module com.jinxuliang.classinstance {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.classinstance to javafx.fxml;
    opens com.jinxuliang.classinstance.model to javafx.fxml;
    exports com.jinxuliang.classinstance;
}