module com.example.useobservablecollection {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.useobservablecollection to javafx.fxml;
    exports com.jinxuliang.useobservablecollection;
}