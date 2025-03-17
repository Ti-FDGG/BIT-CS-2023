import model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class UsePredicate {

    //创建用于测试的Person对象集合
    static List<Person> createPersonCollection() {
        List<Person> people = new ArrayList<>();
        people.add(new Person("张三", 48));
        people.add(new Person("李四", 30));
        people.add(new Person("王五", 73));
        return people;
    }

    public static void main(String[] args) {
        List<Person> people = createPersonCollection();

        traditionalCode(people);
        useLambdaCode(people);
        simplestCode(people);
        LinkedPredicate();
    }

    //最原始的Java实现方式
    static void traditionalCode(List<Person> people) {
        var predicate = new Predicate<Person>() {
            @Override
            public boolean test(Person person) {
                return (person.getAge() > 60);
            }
        };

        for (Person person : people) {
            if (predicate.test(person)) {
                System.out.println(person);
            }
        }
    }

    //使用Lambda表达式的实现方式
    static void useLambdaCode(List<Person> people) {
        Predicate<Person> oldPerson = p -> p.getAge() > 60;
        people.forEach(p -> {
            if (oldPerson.test(p)) {
                System.out.println(p);
            }
        });
    }

    //应用模块化与函数式编程思想消除重复代码
    static void simplestCode(List<Person> people) {
        //动态指定数据筛选条件
        Predicate<Person> predicate = p -> p.getAge() > 60;
        Predicate<Person> predicate2 = p -> p.getAge() < 60;

        //显示老年人
        displayPeople(people, predicate);
        //显示年青人
        displayPeople(people, predicate2);
    }

    //将筛选条件作为方法参数传入
    private static void displayPeople(List<Person> people,
                                      Predicate<Person> predicate) {
        people.forEach(p -> {
            if (predicate.test(p)) {
                System.out.println(p);
            }
        });
    }

    //级联的Predicate
    private static void LinkedPredicate() {
        Predicate<Integer> p = i -> i % 2 == 0;
        Predicate<Integer> q = i -> i % 3 == 0;
        Predicate<Integer> r = i -> i > 10 & i < 40;
        //生成[1,50]区间的整数集合
        var nums = IntStream.rangeClosed(1, 50).boxed().toList();
        //输出（10，40）区间内2和3的倍数
        nums.stream().filter(p.and(q).and(r)).forEach(System.out::println);
    }
}
