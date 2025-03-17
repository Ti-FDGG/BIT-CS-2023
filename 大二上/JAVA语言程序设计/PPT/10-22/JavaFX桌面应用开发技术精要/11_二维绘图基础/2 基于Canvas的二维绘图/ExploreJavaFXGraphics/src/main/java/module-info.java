module com.jinxuliang.explorejavafxgraphics {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.explorejavafxgraphics to javafx.fxml;
    exports com.jinxuliang.explorejavafxgraphics;
}