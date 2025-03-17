module com.jinxuliang.javafxthreadcomponent {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.javafxthreadcomponent to javafx.fxml;
    exports com.jinxuliang.javafxthreadcomponent;
    exports com.jinxuliang.javafxthreadcomponent.exercise;
    opens com.jinxuliang.javafxthreadcomponent.exercise to javafx.fxml;
    exports com.jinxuliang.javafxthreadcomponent.tasks;
    opens com.jinxuliang.javafxthreadcomponent.tasks to javafx.fxml;
}