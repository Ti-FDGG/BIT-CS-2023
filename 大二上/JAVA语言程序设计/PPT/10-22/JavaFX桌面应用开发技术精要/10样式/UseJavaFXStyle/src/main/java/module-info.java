module com.jinxuliang.usejavafxstyle {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.usejavafxstyle to javafx.fxml;
    opens com.jinxuliang.usejavafxstyle.homework to javafx.fxml;

    exports com.jinxuliang.usejavafxstyle;
    exports com.jinxuliang.usejavafxstyle.homework;
}