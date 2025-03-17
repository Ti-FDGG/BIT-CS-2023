import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class UseInputOutputStreamReaderWriter {
    static String fileName = "poem.txt";

    public static void main(String[] args) throws IOException {
        writeToFile();
        readFromFile();
    }

    private static void readFromFile() {
        try (var reader = new InputStreamReader(
                new FileInputStream(fileName),
                StandardCharsets.UTF_8)) {
            //一个字符一个字符地读
            int value;
            while ((value = reader.read()) != -1) {
                System.out.print((char) value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeToFile() {
        try (var writer = new OutputStreamWriter(
                new FileOutputStream(fileName),
                StandardCharsets.UTF_8)) {
            writer.write("白日依山尽\n");
            writer.write("黄河入海流\n");
            writer.write("欲穷千里目\n");
            writer.write("更上一层楼\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("写入结束");
    }
}
