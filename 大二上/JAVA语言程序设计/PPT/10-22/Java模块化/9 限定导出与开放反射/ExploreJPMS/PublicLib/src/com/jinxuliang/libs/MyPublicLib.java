package com.jinxuliang.libs;

import java.util.Random;

public class MyPublicLib {
    //定义一个私有的字段
    private static int secret = new Random().nextInt(100);
    static {
        System.out.println("secret的初始值为："+secret);
    }
    //一个私有的静态方法，获取secret字段的值
    private static int getSecret(){
        return secret;
    }

}

