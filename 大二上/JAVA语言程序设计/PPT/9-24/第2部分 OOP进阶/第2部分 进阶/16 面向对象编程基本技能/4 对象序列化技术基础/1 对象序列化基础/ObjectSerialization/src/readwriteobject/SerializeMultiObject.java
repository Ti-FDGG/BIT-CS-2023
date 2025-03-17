package readwriteobject;

import java.io.*;

public class SerializeMultiObject {

    private static final String objDataFileName="ObjectData.dat";

    public static void main(String args[]) throws IOException, ClassNotFoundException {

        writeMultiObjToFile();
        readMultiObjFromFile();

    }

    private static void readMultiObjFromFile()
            throws IOException, ClassNotFoundException {
        System.out.println("现在从文件中重建对象并输出对象的字段值");
        try(ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(objDataFileName))){
            var myClassObj1 = (MyClass) in.readObject();
            var myClassObj2 = (MyClass) in.readObject();
            var myOtherClassObj = (MyOtherClass) in.readObject();
            System.out.println(myClassObj1);
            System.out.println(myClassObj2);
            System.out.println(myOtherClassObj);
        }
    }

    private static void writeMultiObjToFile() throws IOException {
        try( var out = new ObjectOutputStream(
                new FileOutputStream(objDataFileName))){
            var myClassObj1 = new MyClass(100);
            var myClassObj2 = new MyClass(200);
            var  myOtherClassObj = new MyOtherClass("Hello");
            out.writeObject(myClassObj1);
            out.writeObject(myClassObj2);
            out.writeObject(myOtherClassObj);
        }
        System.out.println("三个对象已经写入到文件中。");
    }
}

class MyClass implements Serializable {

    public MyClass(int value) {

        this.intValue=value;
    }

    public int intValue;

    @Override
    public String toString() {
        return "MyClass{" +
                "intValue=" + intValue +
                '}';
    }
}

class MyOtherClass implements Serializable {

    public MyOtherClass(String value) {
        this.stringValue=value;
    }

    public String stringValue;

    @Override
    public String toString() {
        return "MyOtherClass{" +
                "stringValue='" + stringValue + '\'' +
                '}';
    }
}
