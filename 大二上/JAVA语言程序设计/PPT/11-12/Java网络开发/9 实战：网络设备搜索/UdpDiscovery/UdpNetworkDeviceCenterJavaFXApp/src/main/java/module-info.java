module com.jinxuliang.udpnetworkdevicecenterjavafxapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires PublicLibrary;
    requires lombok;
    requires com.google.gson;
    requires io.reactivex.rxjava3;

    opens com.jinxuliang.udpnetworkdevicecenterjavafxapp to javafx.fxml;
    exports com.jinxuliang.udpnetworkdevicecenterjavafxapp;
}

