import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MethodReference {

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        //创建几个测试用Person对象，加入到了people集合中
        people.add(new Person("张三", 48));
        people.add(new Person("李四", 30));
        people.add(new Person("王五", 24));

        MethodReference obj = new MethodReference();
        obj.sortDataWithInstanceMethod(people);
        obj.sortDataWithStaticMethod(people);
        people.forEach(System.out::println);
    }

    public void sortDataWithInstanceMethod(List<Person> people) {
        // 实例方法引用
        Collections.sort(people, this::compareAge);
    }

    public void sortDataWithStaticMethod(List<Person> people) {
        // 静态方法引用
        Collections.sort(people, Person::compareAge);
    }

    // 实例方法
    public int compareAge(Person p1, Person p2) {
        Integer age1 = p1.getAge();
        return age1.compareTo(p2.getAge());
    }

}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // region getter and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
//endregion

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }

    public static int compareAge(Person p1, Person p2) {
        Integer age1 = p1.getAge();
        return age1.compareTo(p2.getAge());
    }

}