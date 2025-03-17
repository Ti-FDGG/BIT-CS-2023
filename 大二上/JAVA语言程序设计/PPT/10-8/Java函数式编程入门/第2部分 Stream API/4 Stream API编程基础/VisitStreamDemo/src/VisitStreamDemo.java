import model.Person;

import java.util.ArrayList;
import java.util.List;

public class VisitStreamDemo {
    public static void main(String[] args) {
        useForEach();
        //usePeek();
        //useLimit();
        //useSkip();
    }

    static void useForEach() {
        var people = Person.persons();
        System.out.println("涨工资前：");
        people.stream().forEach(System.out::println);
        System.out.println();
        System.out.println("涨10%工资后：");
        people.stream().forEach(p -> p.setIncome(p.getIncome() * 1.1));
        people.stream().forEach(System.out::println);

    }

    //使用peek加工每个元素
    static void usePeek() {
        var people = Person.persons();
        people.stream()
                .peek(p -> p.setIncome(p.getIncome() * 1.1))
                .forEach(System.out::println);
    }

    public static void useLimit() {
        var nums= List.of(1,2,3,4,5,6);
        //取出前3个元素输出
        nums.stream().limit(3).forEach(System.out::println);
    }

    static void useSkip(){
        var nums= List.of(1,2,3,4,5,6);
        //忽略前3个元素，输出后面的元素
        nums.stream().skip(3)
                .forEach(System.out::println);
    }
}
