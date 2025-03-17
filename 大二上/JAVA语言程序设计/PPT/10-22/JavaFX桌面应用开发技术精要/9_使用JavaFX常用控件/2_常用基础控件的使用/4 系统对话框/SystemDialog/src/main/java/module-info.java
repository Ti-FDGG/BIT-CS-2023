module com.jinxuliang.systemdialog {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.systemdialog to javafx.fxml;
    exports com.jinxuliang.systemdialog;

    exports com.jinxuliang.systemdialog.chooser;
    exports com.jinxuliang.systemdialog.picker;
}