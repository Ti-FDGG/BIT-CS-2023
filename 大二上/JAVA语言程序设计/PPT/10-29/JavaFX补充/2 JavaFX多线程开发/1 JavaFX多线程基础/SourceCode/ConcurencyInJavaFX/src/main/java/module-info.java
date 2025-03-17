module com.jinxuliang.concurencyinjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.concurencyinjavafx to javafx.fxml;
    exports com.jinxuliang.concurencyinjavafx;

}