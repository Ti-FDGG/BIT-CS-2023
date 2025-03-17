
import java.io.*;
import java.util.*;


public class ReplaceTest {
    private static class ReplacedPerson implements Serializable {
        private String name;
        private int age;

        public ReplacedPerson(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getAge() {
            return this.age;
        }

        @Serial
        private Object writeReplace() throws ObjectStreamException {
            ArrayList<Object> list = new ArrayList<Object>();
            list.add(name);
            list.add(age);
            return list;
        }

        @Override
        public String toString() {
            return name + "有" + age + "岁";
        }
    }

    private static String replaceObjFileName = "replace.dat";

    public static void main(String[] args) {

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(replaceObjFileName));
             ObjectInputStream ois = new ObjectInputStream(
                     new FileInputStream(replaceObjFileName))) {
            ReplacedPerson per = new ReplacedPerson("张三", 34);
            oos.writeObject(per);
            System.out.println("对象：“" + per + "”已写入到" + replaceObjFileName + "文件中");
            ArrayList list = (ArrayList) ois.readObject();
            System.out.println("从" + replaceObjFileName + "文件中读出：" + list);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}


