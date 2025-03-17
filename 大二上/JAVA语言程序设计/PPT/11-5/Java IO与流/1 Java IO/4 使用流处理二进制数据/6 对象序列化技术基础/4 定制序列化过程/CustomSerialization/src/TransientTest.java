import model.PersonIgnoreage;

import java.io.*;

public class TransientTest {
    private static final String dataFileName = "transient.dat";

    public static void main(String[] args)
            throws IOException, ClassNotFoundException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(dataFileName));
             ObjectInputStream ois = new ObjectInputStream(
                     new FileInputStream(dataFileName))) {
            PersonIgnoreage per = new PersonIgnoreage("张三", 46);
            //系统会将对象转换为字节序列并输出
            oos.writeObject(per);
            System.out.println("向文件" + dataFileName + "中写入对象:" + per);
            PersonIgnoreage p = (PersonIgnoreage) ois.readObject();
            System.out.println("向文件" + dataFileName + "中读取对象:" + p);
        }
    }
}


