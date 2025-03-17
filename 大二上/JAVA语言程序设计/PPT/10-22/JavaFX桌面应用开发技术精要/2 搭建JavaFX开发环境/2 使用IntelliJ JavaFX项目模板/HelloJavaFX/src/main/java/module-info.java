module com.jinxuliang.hellojavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.hellojavafx to javafx.fxml;
    exports com.jinxuliang.hellojavafx;
}