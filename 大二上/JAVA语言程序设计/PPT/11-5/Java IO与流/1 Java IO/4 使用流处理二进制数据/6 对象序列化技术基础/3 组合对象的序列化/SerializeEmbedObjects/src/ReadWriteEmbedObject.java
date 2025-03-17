import java.io.*;
import java.time.LocalTime;


public class ReadWriteEmbedObject {

    private static final String objDataFileName = "embedObj.dat";

    public static void main(String[] args)
            throws IOException, ClassNotFoundException {
        writeEmbedObj();
        readEmbedObj();
    }

    private static void writeEmbedObj() throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(objDataFileName))) {
            //创建一个包容有内部对象的组合对象
            OuterClass container = new OuterClass(new InnerClass());
            //序列化组合对象
            out.writeObject(container);
            System.out.println("复合对象已经写到文件" + objDataFileName + "中。");
        }
    }

    private static void readEmbedObj()
            throws IOException, ClassNotFoundException {
        System.out.println("从" + objDataFileName + "文件中读取复合对象。");
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(objDataFileName))) {
            //反序列化外部对象
            OuterClass other = (OuterClass) in.readObject();
            //输出外部对象的字段值，以证明其确实顺利地重新创建了。
            System.out.println(other);
        }
    }

}


class OuterClass implements Serializable {
    public OuterClass(InnerClass innerObject) {
        this.innerObject = innerObject;
    }
    public InnerClass innerObject = null;
    @Override
    public String toString() {
        return "OuterClass" + hashCode() + "{\n" +
                "\tinnerObject = " + innerObject +
                "\n}";
    }
}

class InnerClass implements Serializable {
    @Override
    public String toString() {
        return "InnerClass" + hashCode();
    }
}
