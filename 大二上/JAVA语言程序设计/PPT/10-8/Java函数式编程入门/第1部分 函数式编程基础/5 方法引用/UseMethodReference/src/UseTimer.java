import javax.swing.*;
import java.util.Scanner;

public class UseTimer {
    public static void main(String[] args) {
        traditionalWay();
        useMethodReference();
    }

    static void traditionalWay() {
        //使用Lambda表达式定义Timer对象
        var timer = new Timer(1000, event -> System.out.println(event));
        timer.start();
        System.out.println("敲回车键退出……");
        new Scanner(System.in).nextLine();
    }

    static void useMethodReference() {
        //以下两句是等价的
        //（1）使用Lambda表达式构造Timer对象
        //var timer=new Timer(1000, event-> System.out.println(event));
        //（2）使用方法引用构造Timer对象
        var timer = new Timer(1000, System.out::println);
        timer.start();
        System.out.println("敲回车键退出……");
        new Scanner(System.in).nextLine();
    }
}

