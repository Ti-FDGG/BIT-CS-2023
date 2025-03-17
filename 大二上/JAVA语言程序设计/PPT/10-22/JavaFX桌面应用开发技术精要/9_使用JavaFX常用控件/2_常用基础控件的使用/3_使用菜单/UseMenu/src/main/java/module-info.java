module com.jinxuliang.usemenu {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.usemenu to javafx.fxml;
    exports com.jinxuliang.usemenu;
    exports com.jinxuliang.usemenu.examples;
    opens com.jinxuliang.usemenu.examples to javafx.fxml;
}