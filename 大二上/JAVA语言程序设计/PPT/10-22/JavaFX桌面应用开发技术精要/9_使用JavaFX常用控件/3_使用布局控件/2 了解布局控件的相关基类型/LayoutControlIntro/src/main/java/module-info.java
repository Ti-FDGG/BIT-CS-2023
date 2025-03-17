module com.jinxuliang.layoutcontrolintro {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.layoutcontrolintro to javafx.fxml;

    exports com.jinxuliang.layoutcontrolintro.pane;
}