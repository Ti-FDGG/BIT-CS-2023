import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ReadWriteMapToFile {
    public static void main(String[] args) throws IOException {

        var collection=createDataCollection();
        writeToDataFile(collection);
        readCollectionFromFile();

    }

    private static void readCollectionFromFile() throws IOException {
        try (var reader = new DataInputStream
                (new BufferedInputStream(
                        new FileInputStream("data.dat")
                ))) {
            //注意一下如何判断读入结束
            boolean eof = false;
            while (!eof) {
                try {
                    int index = reader.readInt();
                    String content = reader.readUTF();
                    System.out.println(index + ":" + content);
                } catch (EOFException exception) {
                    eof = true;
                }
            }
        }
    }

    //创建一个用于测试的集合
    private static Map<Integer, String> createDataCollection() {
        Map<Integer, String> data = new HashMap<>();
        data.put(1, "一");
        data.put(2, "二");
        data.put(3, "三");
        return data;
    }

    private static void writeToDataFile(Map<Integer, String> data) throws IOException {
        try (var dataFile = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream("data.dat")
                )
        )) {
            data.forEach((index, content) -> {
                try {
                    dataFile.writeInt(index);
                    dataFile.writeUTF(content);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("写入结束！");
        }
    }
}
