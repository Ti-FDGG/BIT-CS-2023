package com.jinxuliang;

import cn.edu.bit.cs.moduleb.B;

public class TestModuleTransitive {
    public static void main(String[] args) {
        var obj = new B();
        System.out.println(obj.getC().getCurrentTime());
    }
}


