module com.jinxuliang.javafxusejdbc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.jinxuliang.javafxusejdbc to javafx.fxml;
    opens com.jinxuliang.javafxusejdbc.controller to javafx.fxml;

    exports com.jinxuliang.javafxusejdbc;
    exports com.jinxuliang.javafxusejdbc.repo;
    exports com.jinxuliang.javafxusejdbc.entity;
    exports com.jinxuliang.javafxusejdbc.controller;

}

