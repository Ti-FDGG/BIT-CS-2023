module com.jinxuliang.fxmlsetproperty {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.fxmlsetproperty to javafx.fxml;
    opens com.jinxuliang.fxmlsetproperty.model to javafx.fxml;

    exports com.jinxuliang.fxmlsetproperty;
}