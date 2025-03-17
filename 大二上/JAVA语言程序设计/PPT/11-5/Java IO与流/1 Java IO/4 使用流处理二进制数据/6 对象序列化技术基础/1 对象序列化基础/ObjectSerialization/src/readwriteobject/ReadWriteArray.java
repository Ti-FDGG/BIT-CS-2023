package readwriteobject;

import model.Person;

import java.io.*;

public class ReadWriteArray {
    static String dataFileName = "people.dat";

    public static void main(String[] args) throws IOException {
        writeArray();
        readArray();
    }

    private static void writeArray() throws IOException {
        Person[] people = new Person[]{
                new Person("张三", 20),
                new Person("李四", 30),
                new Person("王五", 40)
        };
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(dataFileName)
        )) {
            out.writeObject(people);
        }
        System.out.println("数组已经写到" + dataFileName + "中");
    }

    private static void readArray() throws IOException {
        System.out.println("从" + dataFileName + "中读...");
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(dataFileName)
        )) {
            Person[] people = (Person[]) in.readObject();
            for (Person person : people) {
                System.out.println(person);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
