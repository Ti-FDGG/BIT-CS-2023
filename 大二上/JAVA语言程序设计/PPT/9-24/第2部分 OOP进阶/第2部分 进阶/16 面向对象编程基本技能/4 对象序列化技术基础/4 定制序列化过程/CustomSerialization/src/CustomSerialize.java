
import java.io.*;

public class CustomSerialize {
    private static final String objDataFileName = "customSerializeObj.dat";

    public static void main(String[] args) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(objDataFileName));
             ObjectInputStream ois = new ObjectInputStream(
                     new FileInputStream(objDataFileName))
        ) {
            MyPerson per = new MyPerson("张三", 23);
            oos.writeObject(per);
            System.out.println("向文件" + objDataFileName + "中写入对象：" + per);
            MyPerson p = (MyPerson) ois.readObject();
            System.out.println("从文件" + objDataFileName + "中读入对象：" + p);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

class MyPerson implements Serializable {
    private String name;
    private int age;
    public MyPerson(String name, int age) {
        this.name = name;
        this.age = age;
    }
    //提供编译器的语法检查（可选）
    @Serial
    private void writeObject(ObjectOutputStream out)
            throws IOException {
        out.writeObject(new StringBuffer(name).reverse());
        out.writeInt(age);
    }
    @Serial
    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        this.name = ((StringBuffer) in.readObject()).toString();
        this.age = in.readInt();
    }
    @Override
    public String toString() {
        return name + "有" + age + "岁";
    }
    //region getter and setter
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
    //endregion
}
