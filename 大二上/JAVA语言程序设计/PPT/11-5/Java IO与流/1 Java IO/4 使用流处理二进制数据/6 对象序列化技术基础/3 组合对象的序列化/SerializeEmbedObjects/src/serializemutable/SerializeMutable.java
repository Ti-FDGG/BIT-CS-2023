package serializemutable;

import model.Person;

import java.io.*;

public class SerializeMutable {
    private static final String dataFileName = "mutable.dat";

    public static void main(String[] args) {
        try (var oos = new ObjectOutputStream(new FileOutputStream(dataFileName));
             var ois = new ObjectInputStream(new FileInputStream(dataFileName))) {
            Person student = new Person("孙悟空", 500);
            //序列化学生对象，此时，其name属性为“孙悟空”
            oos.writeObject(student);
            System.out.println("对象:“" + student + "”已写入文件" + dataFileName);
            //改变Person对象的name属性
            student.setName("猪八戒");
            //再次序列化学生对象
            oos.writeObject(student);
            System.out.println("对象:“" + student + "”已写入文件" + dataFileName);
            //验证序列化结果
            Person student1 = (Person) ois.readObject();
            System.out.println("从文件" + dataFileName + "中读出对象:" + student1);
            Person student2 = (Person) ois.readObject();
            System.out.println("从文件" + dataFileName + "中读出对象:" + student2);
            //反序列化后，两个学生变量，是否引用同一个学生对象？
            System.out.println(student1 == student2);//输出：true
            //学生的姓名是哪个？
            System.out.println(student2.getName()); //输出：孙悟空
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
