module PublicLibrary {
    exports com.jinxuliang;
    requires lombok;
    opens com.jinxuliang;
    exports com.jinxuliang.model;
    opens com.jinxuliang.model;
}

