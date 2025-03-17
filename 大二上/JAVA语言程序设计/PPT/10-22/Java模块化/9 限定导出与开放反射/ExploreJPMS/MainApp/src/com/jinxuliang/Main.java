package com.jinxuliang;

import com.jinxuliang.libs.MyPublicLib;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws
            NoSuchFieldException, IllegalAccessException,
            ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException,
            InstantiationException {


        visitPrivateMemberViaReflection();

        createInstanceOfNonPublicClass();


    }

    //使用反射创建未导出的非公有类型实例
    private static void createInstanceOfNonPublicClass()
            throws ClassNotFoundException,
            NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException {
        //指定要实例化的类名
        String className="com.jinxuliang.internal.MyInternalClass";
        //加载类型
        var clazz = Class.forName(className);
        //获取构造函数引用
        var constructor=clazz.getDeclaredConstructor();
        //让其可被访问
        constructor.setAccessible(true);
        //创建实例
        Object target = constructor.newInstance();
        //证明实例确实已创建
        System.out.println(target);
    }

    //通过反射访问类型的私有成员
    private static void visitPrivateMemberViaReflection()
            throws NoSuchFieldException,
            IllegalAccessException,
            NoSuchMethodException,
            InvocationTargetException {
        var clazz = MyPublicLib.class;
        //获取私有字段的引用
        Field valueField = clazz.getDeclaredField("secret");
        valueField.setAccessible(true);
        valueField.set(valueField, 100);//给它一个新值
        //获取私有方法的引用
        Method method = clazz.getDeclaredMethod("getSecret");
        method.setAccessible(true);
        //调用静态方法
        var returnValue = method.invoke(null);
        //输出方法调用返回值
        System.out.println(returnValue);
    }
}
