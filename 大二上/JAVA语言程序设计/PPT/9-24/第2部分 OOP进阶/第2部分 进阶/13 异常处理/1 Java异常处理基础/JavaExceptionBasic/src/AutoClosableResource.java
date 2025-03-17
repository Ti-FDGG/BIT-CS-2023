import java.io.*;
import java.nio.charset.Charset;

public class AutoClosableResource {

    public static void main(String[] args) {

        String fileName = "hello.txt";
        var file = new File(fileName);
        if (!file.exists()) {
            try (var outputStream = new FileOutputStream(file)) {
                outputStream.write("Hello,World!"
                        .getBytes(Charset.defaultCharset()));
                System.out.println("数据文件己创建");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (FileReader reader = new FileReader(file);
             BufferedReader br = new BufferedReader(reader)) {
            var text = br.readLine();
            System.out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
