package serializesharedobject;


import model.Person;

import java.io.*;

public class ReadWriteTeacher {
    private static String dataFileName = "teacher.dat";

    public static void main(String[] args)
            throws IOException, ClassNotFoundException {
        writeToFile();
        readFromFile();
    }

    private static void readFromFile()
            throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(dataFileName))) {
            //依次读取ObjectInputStream输入流中的四个对象
            Teacher t1 = (Teacher) ois.readObject();
            System.out.println("从" + dataFileName + "中读出对象t1：" + t1);
            Teacher t2 = (Teacher) ois.readObject();
            System.out.println("从" + dataFileName + "中读出对象t2：" + t2);
            Person p = (Person) ois.readObject();
            System.out.println("从" + dataFileName + "中读出对象p：" + p);
            Teacher t3 = (Teacher) ois.readObject();
            System.out.println("从" + dataFileName + "中读出对象t3：" + t3);
            System.out.println("t1的student引用和p是否相同："
                    + (t1.getStudent() == p));//输出true
            System.out.println("t2的student引用和p是否相同："
                    + (t2.getStudent() == p));  //输出true
            System.out.println("t2和t3是否是同一个对象："
                    + (t2 == t3)); //输出true
        }
    }

    private static void writeToFile() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(dataFileName))) {
            Person per = new Person("孙悟空", 500);
            Teacher t1 = new Teacher("唐僧", per);
            Teacher t2 = new Teacher("菩提祖师", per);
            //依次将四个对象写入输出流
            oos.writeObject(t1);
            oos.writeObject(t2);
            oos.writeObject(per);
            oos.writeObject(t2);//"菩提祖师"被序列化了两次
            System.out.println("以下对象已被写入到" + dataFileName + "中");
            System.out.println(t1);
            System.out.println(t2);
            System.out.println(per);
            System.out.println(t2);
        }
    }
}
