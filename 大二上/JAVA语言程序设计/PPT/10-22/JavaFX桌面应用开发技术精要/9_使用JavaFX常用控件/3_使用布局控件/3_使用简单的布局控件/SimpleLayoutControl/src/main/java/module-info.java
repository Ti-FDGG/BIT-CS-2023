module com.jinxuliang.simplelayoutcontrol {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.jinxuliang.simplelayoutcontrol.group;
    exports com.jinxuliang.simplelayoutcontrol.stackpane;
    exports com.jinxuliang.simplelayoutcontrol.scroll;

    opens com.jinxuliang.simplelayoutcontrol.scroll to javafx.fxml;
}