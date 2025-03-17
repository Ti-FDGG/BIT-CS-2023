import model.Person;

import java.io.*;

public class SerializeMultiTimes2 {

    private static final String objDataFileName = "multiTimes2.data";

    public static void main(String[] args) {
        writePersonMultiTimes();
        readPerson();
    }

    private static void writePersonMultiTimes() {
        //将被两次序列化的Person对象
        Person person = new Person("张三", 30);
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(objDataFileName))) {
            //将person对象写入输出流
            oos.writeObject(person);
            System.out.println(" 对象：“" + person + "”已写入到文件"
                    + objDataFileName + "中！");
            //同一对象，字段值修改！
            person.setName("李四");
            oos.writeObject(person);
            System.out.println(" 对象：“" + person + "”已写入到文件"
                    + objDataFileName + "中！");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void readPerson() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(objDataFileName))) {
            //从输入流中读取一个Java对象，并将其强制类型转换为Person类
            var person1 = (Person) ois.readObject();
            System.out.println("第一次反序列化：" + person1);
            var person2 = (Person) ois.readObject();
            System.out.println("第二次反序列化：" + person2);
            //检查是否引用同一个Person对象
            System.out.println(person1 == person2);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
