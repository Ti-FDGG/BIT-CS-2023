module com.example.usebuttons {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.usebuttons to javafx.fxml;
    exports com.jinxuliang.usebuttons;
}