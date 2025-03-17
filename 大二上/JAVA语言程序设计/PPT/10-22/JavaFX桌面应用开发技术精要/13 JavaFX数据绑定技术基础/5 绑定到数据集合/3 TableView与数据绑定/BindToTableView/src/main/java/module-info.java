module com.jinxuliang.bindtotableview {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.bindtotableview to javafx.fxml;
    opens com.jinxuliang.bindtotableview.model to javafx.base;
    exports com.jinxuliang.bindtotableview;
}