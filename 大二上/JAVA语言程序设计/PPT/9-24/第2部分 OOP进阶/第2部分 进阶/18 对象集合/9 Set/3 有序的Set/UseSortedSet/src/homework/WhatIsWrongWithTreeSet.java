package homework;

import java.util.*;


//MyClass类，重写了equals方法，总是返回false，
//重写了compareTo(Object obj)方法，总是返回正整数
class MyClass implements Comparable {
    int age;

    public MyClass(int age) {
        this.age = age;
    }

    public boolean equals(Object obj) {
        return false;
    }

    public int compareTo(Object obj) {
        return 1;
    }

    @Override
    public String toString() {
        return "homework.MyClass{" +
                "age=" + age +
                '}';
    }
}

public class WhatIsWrongWithTreeSet {
    public static void main(String[] args) {
        TreeSet set = new TreeSet<MyClass>();
        MyClass obj1 = new MyClass(6);
        //向集合中添加两次相同的对象
        set.add(obj1);
        //由于MyClass的compareTo()方法始终返回1
        //因此，TreeSet认为这是两个不同的对象，添加成功
        System.out.println(set.add(obj1));//true
        //下面输出set集合，将看到有2个元素
        //[homework.MyClass{age=6}, homework.MyClass{age=6}]
        System.out.println(set);
        //修改set集合的第一个元素的age属性
        ((MyClass) (set.first())).age = 9;
        //输出set集合的最后一个元素的age属性，将看到也变成了9
        System.out.println(((MyClass) (set.last())).age);
        //输出的结果：证实两个元素都被改了
        //[homework.MyClass{age=9}, homework.MyClass{age=9}]
        System.out.println(set);
    }
}
