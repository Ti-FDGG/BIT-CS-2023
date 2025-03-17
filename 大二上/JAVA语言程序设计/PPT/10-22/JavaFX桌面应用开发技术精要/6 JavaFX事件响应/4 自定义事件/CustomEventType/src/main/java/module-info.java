module com.example.customeventtype {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jinxuliang.customeventtype to javafx.fxml;
    exports com.jinxuliang.customeventtype;
}