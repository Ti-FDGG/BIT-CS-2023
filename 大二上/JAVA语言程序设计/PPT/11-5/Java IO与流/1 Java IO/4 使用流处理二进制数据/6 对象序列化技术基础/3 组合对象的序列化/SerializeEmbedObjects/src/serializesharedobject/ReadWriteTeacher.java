package serializesharedobject;



import model.Person;

import java.io.*;

public class ReadWriteTeacher {
    private static final String dataFileName = "teacher.dat";

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
            Teacher teacher1 = (Teacher) ois.readObject();
            System.out.println("从" + dataFileName + "中读出对象teacher1：" + teacher1);
            Teacher teacher2 = (Teacher) ois.readObject();
            System.out.println("从" + dataFileName + "中读出对象teacher2：" + teacher2);
            Person student = (Person) ois.readObject();
            System.out.println("从" + dataFileName + "中读出对象student: " + student);
            Teacher teacher3 = (Teacher) ois.readObject();
            System.out.println("从" + dataFileName + "中读出对象teacher3：" + teacher3);
            System.out.println("唐僧的student字段，是否引用孙悟空对象？"
                    + (teacher1.getStudent() == student));//输出true
            System.out.println("菩提祖师对象一的student字段，是否引用孙悟空对象？"
                    + (teacher2.getStudent() == student));  //输出true
            System.out.println("菩提祖师对象二的student字段，是否引用孙悟空对象？"
                    + (teacher3.getStudent() == student));  //输出true
            System.out.println("菩提祖师对象一和菩提祖师对象二，是否是同一个对象？"
                    + (teacher2 == teacher3)); //输出false
        }
    }

    private static void writeToFile() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(dataFileName))) {
            //创建一个学生，两个老师对象，注意，两个老师对象共享同一个学生对象
            Person student = new Person("孙悟空", 500);
            Teacher teacher1 = new Teacher("唐僧", student);
            Teacher teacher2 = new Teacher("菩提祖师", student);
            //依次将四个对象写入输出流
            oos.writeObject(teacher1);
            oos.writeObject(teacher2);
            oos.writeObject(student);
            oos.writeObject(teacher2);//"菩提祖师"对象被序列化了两次
            System.out.println("以下对象已被写入到" + dataFileName + "中");
            System.out.println("(1)" + teacher1);
            System.out.println("(2)" + teacher2);
            System.out.println("(3)" + student);
            System.out.println("(4)" + teacher2);
        }
    }
}
