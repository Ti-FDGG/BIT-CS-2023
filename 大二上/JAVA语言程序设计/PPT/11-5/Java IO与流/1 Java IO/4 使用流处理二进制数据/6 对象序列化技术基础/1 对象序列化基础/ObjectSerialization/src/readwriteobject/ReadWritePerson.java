package readwriteobject;


import model.Person;

import java.io.*;

public class ReadWritePerson {
    private static final String objDataFileName = "person.dat";

    public static void main(String[] args) {
        writePerson();
        readPerson();
    }

    private static void writePerson() {
        //创建一个ObjectOutputStream输出流
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(objDataFileName))) {
            Person person = new Person("张三", 30);
            //将person对象写入输出流
            oos.writeObject(person);
            System.out.println("对象：“" + person + "”已写入到文件"
                    + objDataFileName + "中！");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void readPerson() {
        //创建一个ObjectInputStream输出流
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(objDataFileName))) {
            //从输入流中读取一个Java对象，并将其强制类型转换为Person类
            Person person = (Person) ois.readObject();
            System.out.println("从文件" + objDataFileName + "中创建对象：" + person);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}

