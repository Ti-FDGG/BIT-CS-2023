package serializemutable;

import model.Person;

import java.io.*;

public class SerializeMutable
{
    public static void main(String[] args)
    {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try
        {
            //创建一个ObjectOutputStream输入流
            oos = new ObjectOutputStream(
                    new FileOutputStream("mutable.txt"));
            Person per = new Person("孙悟空", 500);
            //系统会per对象转换字节序列并输出
            oos.writeObject(per);
            System.out.println("对象"+per+"已写入文件mutable.txt");
            //改变per对象的name属性
            per.setName("猪八戒");
            //系统只是输出序列化编号，所以改变后的name不会被序列化
            oos.writeObject(per);
            System.out.println("对象"+per+"已写入文件mutable.txt");
            //创建一个ObjectInputStream输入流
            ois = new ObjectInputStream(
                    new FileInputStream("mutable.txt"));
            Person p1 = (Person)ois.readObject();
            System.out.println("从文件mutable.txt中读出对象"+p1);
            Person p2 = (Person)ois.readObject();
            System.out.println("从文件mutable.txt中读出对象"+p2);
            //下面输出true，即反序列化后p1等于p2
            System.out.println(p1 == p2);
            //下面依然看到输出”孙悟空“，即改变后的属性没有被序列化
            System.out.println(p2.getName());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if (ois != null)
                    ois.close();
                if (oos != null)
                    oos.close();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
