module com.jinxuliang.chatclientjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires ChatRoomLibray;

    opens com.jinxuliang.chatclientjavafx to javafx.fxml;
    exports com.jinxuliang.chatclientjavafx;
}