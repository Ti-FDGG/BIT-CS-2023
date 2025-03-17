package homework;

import java.util.*;

class MyTestClass implements Comparable {
    int value;

    public MyTestClass(int count) {
        this.value = count;
    }

    public String toString() {
        return "homework.MyTestClass(value:" + value + ")";
    }

    public boolean equals(Object obj) {
        if (obj instanceof MyTestClass) {
            MyTestClass t = (MyTestClass) obj;
            if (t.value == this.value) {
                return true;
            }
        }
        return false;
    }

    public int compareTo(Object obj) {
        MyTestClass r = (MyTestClass) obj;
        if (this.value > r.value) {
            return 1;
        } else if (this.value == r.value) {
            return 0;
        } else {
            return -1;
        }
    }
}

public class WhatIsWrongWithTreeSet2 {
    public static void main(String[] args) {
        var ts = new TreeSet<MyTestClass>();
        ts.add(new MyTestClass(5));
        ts.add(new MyTestClass(-3));
        ts.add(new MyTestClass(9));
        ts.add(new MyTestClass(-2));
        //打印TreeSet集合，集合元素是有序排列的
        System.out.println("打印TreeSet集合，集合元素是有序排列的.");
        System.out.println(ts);
        //取出第一个元素
        MyTestClass first = (MyTestClass) ts.first();
        //为第一个元素的count属性赋值
        first.value = 20;
        //取出最后一个元素
        MyTestClass last = (MyTestClass) ts.last();
        //为最后一个元素的value属性赋值，与倒数第二个元素value属性相同
        last.value = -2;
        //再次输出value将看到TreeSet里的元素处于无序状态，且有重复元素
        System.out.println("修改第一个元素为20，最后一个元素为-2.\n" + ts);
        //删除属性被改变的元素，删除失败，集合维持不变
        ts.remove(new MyTestClass(-2));
        System.out.println("尝试删除值为-2的元素：\n" + ts);
        //删除属性没有改变的元素，删除成功
        ts.remove(new MyTestClass(5));
        System.out.println("尝试删除值为5的元素：\n" + ts);
    }
}
