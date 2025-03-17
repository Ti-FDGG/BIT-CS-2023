import model.Person;

import java.io.*;

public class SerializeMultiTimes {

    private static final String objDataFileName = "multiTimes.data";
    private static final int SerializeTimes = 3;

    public static void main(String[] args) {
        writePersonMultiTimes();
        readPerson();
    }

    private static void writePersonMultiTimes() {
        //将被多次序列化的Person对象
        Person person = new Person("张三", 30);
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(objDataFileName))) {
            for (int i = 1; i <= SerializeTimes; i++) {
                //如果是序列化多个Person对象，会得到不同的结果
                // Person person = new Person("张三", 30);
                //将person对象写入输出流
                oos.writeObject(person);
                System.out.println(i + " 对象：“" + person + "”已写入到文件"
                        + objDataFileName + "中！");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void readPerson() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(objDataFileName))) {
            //用一个数组来保存反序列化结果
            Person[] people = new Person[SerializeTimes];
            for (int i = 0; i < people.length; i++) {
                //从输入流中读取一个Java对象，并将其强制类型转换为Person类
                people[i] = (Person) ois.readObject();
                System.out.println("[" + i + "] " + people[i]);
            }
            //检查数组中的各个元素，是否引用同一个Person对象
            System.out.println(people[0] == people[1]);
            System.out.println(people[1] == people[2]);

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
