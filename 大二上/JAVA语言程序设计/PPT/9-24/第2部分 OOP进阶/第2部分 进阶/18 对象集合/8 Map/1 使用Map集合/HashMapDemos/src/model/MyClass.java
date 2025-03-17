package model;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;

import static java.util.Comparator.comparing;

public class MyClass {
    //一个用于比较MyClass对象的第三方比较器，使用的是最原始的匿名类型语法
    //可以使用Lambda表达式进行简化
    public static final Comparator<MyClass> BY_NUMS =
            comparing(new Function<MyClass, Integer>() {
                @Override
                public Integer apply(MyClass myClass) {
                    return myClass.num;
                }
            });
    //使用Lambda表达式特性简化后的表达
    public static final Comparator<MyClass> BY_INFO=comparing(MyClass::getInfo);

    private int num;
    private String info;

    @Override
    public String toString() {
        return "MyClass{" +
                "nums=" + num +
                ", info='" + info + '\'' +
                '}';
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyClass myClass = (MyClass) o;
        return num == myClass.num && Objects.equals(info, myClass.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, info);
    }
}

