package com.jinxuliang.javafxbeanbasic;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.*;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ExploreJavaFXProperties {

    public static void main(String[] args) {

        useJDKJavaFXProperties();

        //addAndRemoveInvalidationListener();
        //addAndRemoveChangeListener();
    }

    // 使用系统内置的JavaFX Bean Property
    private static void useJDKJavaFXProperties() {
        // 可读可写的属性
        StringProperty strProp = new SimpleStringProperty();
        strProp.set("Hello");
        System.out.println(strProp.get());
        // 只读的属性
        ReadOnlyStringWrapper wrapper = new ReadOnlyStringWrapper("World");
        ReadOnlyStringProperty readOnlyStringProperty = wrapper.getReadOnlyProperty();
        System.out.println(readOnlyStringProperty.getValue());
    }

    // 测试JavaFX Bean Property的属性值更改通知(InvalidationListener)
    private static void addAndRemoveInvalidationListener() {
        // 创建一个JavaFX Property
        var intProperty = new SimpleIntegerProperty(1024);
        // 创建一个 InvalidationListener监听器对象
        final InvalidationListener invalidationListener =
                observable ->
                        System.out.println("值有变化，observable对象处于invalidated状态");
        // 挂接监听器对象到JavaFX Property
        intProperty.addListener(invalidationListener);
        System.out.println("已经添加invalidation监听器。");

        //首次赋值测试
        System.out.println("调用intProperty.set(2048)，将触发“值改变”通知。");
        intProperty.set(2048);
        //不读取值而多次赋值
        System.out.println("调用intProperty.setValue(3072)，不会触发值改变通知。");
        intProperty.setValue(3072);
        System.out.println("调用intProperty.setValue(4096)，不会触发值改变通知。");
        intProperty.setValue(4096);
        System.out.println("提取当前值："+intProperty.get());
        System.out.println("调用intProperty.setValue(5120)，将触发“值改变”通知。");
        intProperty.setValue(5120);
        System.out.println("提取当前值："+intProperty.get());
        //现在移除监听器对象
        System.out.println("已移除invalidation监听器。");
        intProperty.removeListener(invalidationListener);
        // 再次赋值测试
        System.out.println("调用intProperty.set(4096)，已无监听器可供响应。");
        intProperty.set(4096);
    }

    // 测试JavaFX Bean Property的属性值更改通知(ChangeListener)
    private static void addAndRemoveChangeListener() {
        // 创建一个JavaFX Property
        var intProperty = new SimpleIntegerProperty(1024);

        // 创建一个 InvalidationListener监听器对象
        final ChangeListener changeListener =
                (ObservableValue observableValue,
                 Object oldValue,
                 Object newValue) ->
                        System.out.println("observableValue已更改: 老值 = "
                                + oldValue + ",新值 = " + newValue);
        // 挂接监听器对象到JavaFX Property
        intProperty.addListener(changeListener);
        System.out.println("值更改监听器已添加。");

        // 赋值测试
        System.out.println("调用 intProperty.set(5120)，监听器将响应");
        intProperty.set(5120);

        // 移除监听器对象
        intProperty.removeListener(changeListener);
        System.out.println("值更改监听器已被移除。");

        // 再次赋值测试
        System.out.println("此时调用intProperty.set(6144)，无人监听。");
        intProperty.set(6144);
    }
}
