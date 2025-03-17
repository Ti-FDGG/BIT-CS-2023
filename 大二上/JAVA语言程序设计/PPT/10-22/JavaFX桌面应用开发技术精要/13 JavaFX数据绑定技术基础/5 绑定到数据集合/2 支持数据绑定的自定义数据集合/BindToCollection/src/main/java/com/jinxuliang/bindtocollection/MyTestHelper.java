package com.jinxuliang.bindtocollection;

import javafx.beans.Observable;
import javafx.util.Callback;

import java.util.concurrent.ThreadLocalRandom;

public class MyTestHelper {
    //创建一个测试用数据对象
    public static MyJavaFXBeanClass createExampleObj() {
        int ranValue = ThreadLocalRandom.current().nextInt();
        return new MyJavaFXBeanClass(ranValue, "info of " + ranValue);
    }

    //用于定义哪个属性值的改变需要监听
    public static Callback<MyJavaFXBeanClass, Observable[]> myJavaFXBeanClassExtractor =
            obj -> new Observable[]{
                    obj.infoProperty()
            };

}
