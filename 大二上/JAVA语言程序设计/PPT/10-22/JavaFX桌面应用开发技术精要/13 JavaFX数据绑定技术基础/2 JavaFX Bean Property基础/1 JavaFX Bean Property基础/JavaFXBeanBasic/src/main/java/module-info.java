module com.jinxuliang.javafxbeanbasic {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.javafxbeanbasic to javafx.fxml;
    exports com.jinxuliang.javafxbeanbasic;
}