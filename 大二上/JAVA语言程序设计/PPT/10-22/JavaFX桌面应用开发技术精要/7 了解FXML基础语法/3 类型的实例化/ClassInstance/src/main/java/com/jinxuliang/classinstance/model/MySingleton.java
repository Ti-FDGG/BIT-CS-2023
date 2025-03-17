package com.jinxuliang.classinstance.model;

public class MySingleton {
    private static MySingleton INSTANCE = new MySingleton();

    private MySingleton() {
    }

    public static MySingleton getInstance() {
        return INSTANCE;
    }
}
