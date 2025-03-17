package com.jinxuliang.app;

import com.jinxuliang.MyPublicFunc;

public class MyFirstModuleApp {
    public static void main(String[] args) {
        //调用另一模块中的方法
        System.out.println(MyPublicFunc.getInfo());
    }
}

