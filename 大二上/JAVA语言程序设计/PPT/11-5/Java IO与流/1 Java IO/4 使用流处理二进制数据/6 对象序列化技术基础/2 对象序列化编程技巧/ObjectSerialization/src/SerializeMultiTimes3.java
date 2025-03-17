import model.Person;

import java.io.*;

public class SerializeMultiTimes3 {

    private static final String objDataFileName1 = "multiTimes1.dat";
    private static final String objDataFileName2 = "multiTimes2.dat";

    public static void main(String[] args) {
        writePersonMultiTimes();
        readPerson();
    }

    private static void writePersonMultiTimes() {
        //将被两次序列化的Person对象
        Person person = new Person("张三", 30);
        writePersonToFile(person, objDataFileName1);

        //同一对象，字段值修改！
        person.setName("李四");
        writePersonToFile(person, objDataFileName2);
    }

    //将Person对象的当前值，序列化到指定的文件中
    static void writePersonToFile(Person person, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(fileName))) {
            //将person对象写入输出流
            oos.writeObject(person);
            System.out.println("对象：“" + person + "”已写入到文件"
                    + fileName + "中！");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void readPerson() {

        Person person1 = readFromFile(objDataFileName1);
        System.out.println("第一次反序列化：" + person1);
        Person person2 = readFromFile(objDataFileName2);
        System.out.println("第二次反序列化：" + person2);
        //检查两个变量是否引用同一个Person对象
        System.out.println(person1 == person2);
    }

    //从指定文件中反序列化Person对象
    private static Person readFromFile(String fileName) {
        Person person = null;
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(fileName))) {
            //从输入流中读取一个Java对象，并将其强制类型转换为Person类
            person = (Person) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return person;
    }
}
