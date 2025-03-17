package com.jinxuliang.classinstance.model;

public class MyTestClass {

    public static final int MAX_VALUE = 255;
    private int num;
    private String info;

    //region getter and setter
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    //endregion

    @Override
    public String toString() {
        return "MyClass{" + "num=" + num + ", info='" + info + '\'' + '}';
    }
}
