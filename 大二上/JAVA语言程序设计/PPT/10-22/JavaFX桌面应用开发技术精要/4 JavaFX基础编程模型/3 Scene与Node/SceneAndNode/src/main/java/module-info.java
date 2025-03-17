module com.jinxuliang.sceneandnode {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.sceneandnode to javafx.fxml;
    exports com.jinxuliang.sceneandnode;
    exports com.jinxuliang.sceneandnode.userdata;
    opens com.jinxuliang.sceneandnode.userdata to javafx.fxml;
}