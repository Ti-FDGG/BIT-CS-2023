module com.jinxuliang.bindtocollection {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.bindtocollection to javafx.fxml;
    exports com.jinxuliang.bindtocollection;

}