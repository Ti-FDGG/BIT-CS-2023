package com.jinxuliang.app;

import com.jinxuliang.lib1.Lib1Class;
import com.jinxuliang.lib2.Lib2Class;

public class Main {
    public static void main(String[] args) {
        //通过聚合模块使用Lib1和Lib2中的类
        Lib1Class.printInfo();
        Lib2Class.printInfo();
    }
}


