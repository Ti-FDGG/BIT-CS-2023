import java.io.*;

public class WriteReadBinary {
    static String fileName = "binary.dat";

    public static void main(String[] args) throws IOException {
        writeToFile();
        readFromFile();
    }

    private static void writeToFile() throws IOException {
        int i = 100;
        double d = Math.PI;
        try (var os = new DataOutputStream(
                new FileOutputStream(fileName))) {
            os.writeInt(i);
            os.writeDouble(d);
        }
        System.out.println("数据写入结束");
    }

    private static void readFromFile() throws IOException {
        try (var os = new DataInputStream(
                new FileInputStream(fileName))) {
            int i = os.readInt();
            double d = os.readDouble();
            System.out.printf("从文件中读取：i=%d, d=%f", i, d);
        };
    }
}
