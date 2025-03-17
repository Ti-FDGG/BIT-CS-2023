import model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class UseConsumerAndSupplier {
    public static void main(String[] args) {
        consumerAndSupplier();
//        consumerAndThen();
//        consumerAndThen2();
//        printStudentInfo();
//        consumeAndModify();
    }

    private static void consumerAndSupplier() {
        //消费者
        Consumer<String> printer = System.out::println;
        //生产者
        Supplier<Integer> numFactory = () -> new Random().nextInt();
        for (int i = 0; i < 5; i++) {
            //生产者生产数据，消费者消费数据
            printer.accept(numFactory.get().toString());
        }
    }

    private static void consumerAndThen() {
        Consumer<String> consumer1 = info ->
                System.out.println("First Conusmer:" + info);
        //级联两个Consumer
        var consumer2 = consumer1.andThen(
                info -> System.out.println("Second Consumer:"
                        + info.toUpperCase()));
        consumer2.accept("Hello");
    }

    private static void consumerAndThen2() {
        //定义三个独立的Consumer
        Consumer<String> consumer1 = info ->
                System.out.println("First Conusmer:" + info.toUpperCase());
        Consumer<String> consumer2 = info -> System.out.println("Second Consumer:"
                + info.toLowerCase());
        Consumer<String> finalConsumer = System.out::println;
        //级联三个Consumer，但都是“各人自扫门前雪”，互不影响
        consumer1.andThen(consumer2).andThen(finalConsumer).accept("abcd");
    }


    private static void printStudentInfo() {
        //创建一个用于测试的学生对象集合
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "张三"));
        students.add(new Student(2, "李四"));
        students.add(new Student(3, "王五"));
        //创建两个Consummer，分别输出学生的学号与姓名
        Consumer<Student> printId = student -> System.out.print(student.getId() + " ");
        Consumer<Student> printName = student -> System.out.println(student.getName());
        //遍历学生集合，依次输出其信息
        students.forEach(printId.andThen(printName));
    }

    private static void consumeAndModify() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "张三"));
        students.add(new Student(2, "李四"));
        students.add(new Student(3, "王五"));

        Consumer<Student> printAndModifyId = student -> {
            student.setId(student.getId() * 2);
            System.out.print(student.getId() + " ");
        };
        Consumer<Student> printAndModifyName =
                student -> {
                    student.setName(student.getName() + " "
                            + new Random().nextInt());
                    System.out.println(student.getName());
                };

        students.forEach(printAndModifyId.andThen(printAndModifyName));
    }

}
