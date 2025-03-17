package exercise;

import java.util.*;

//类A的equals方法总是返回true,
//但没有重写其hashCode()方法
class A {
    public boolean equals(Object obj) {
        return true;
    }
}

//类B的hashCode()方法总是返回1,
//但没有重写其equals()方法
class B {
    public int hashCode() {
        return 1;
    }
}

//类C的hashCode()方法总是返回2
//equals方法总是返回true
class C {
    public int hashCode() {
        return 2;
    }

    public boolean equals(Object obj) {
        return true;
    }
}

public class TestHashSet {
    public static void main(String[] args) {
        var hashSet = new HashSet();
        //分别向Set集合中添加2个A对象，2个B对象，2个C对象
        hashSet.add(new A());
        hashSet.add(new A());
        hashSet.add(new B());
        hashSet.add(new B());
        hashSet.add(new C());
        hashSet.add(new C());
        System.out.println(hashSet);
    }
}