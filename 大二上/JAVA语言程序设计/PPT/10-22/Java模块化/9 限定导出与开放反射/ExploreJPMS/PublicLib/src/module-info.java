module PublicLib {
    exports com.jinxuliang.libs;
    opens com.jinxuliang.libs;
    opens com.jinxuliang.internal to MainApp;
}
