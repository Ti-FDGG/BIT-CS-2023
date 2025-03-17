module com.jinxuliang.javafxusedialog {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.jinxuliang.javafxusedialog.dialog;
    opens com.jinxuliang.javafxusedialog.dialog to javafx.fxml;


}