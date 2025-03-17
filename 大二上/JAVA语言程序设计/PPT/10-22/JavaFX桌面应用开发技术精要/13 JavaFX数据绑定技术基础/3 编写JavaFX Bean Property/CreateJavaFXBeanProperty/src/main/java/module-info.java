module com.jinxuliang.createjavafxbeanproperty {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.createjavafxbeanproperty to javafx.fxml;
    exports com.jinxuliang.createjavafxbeanproperty;
}