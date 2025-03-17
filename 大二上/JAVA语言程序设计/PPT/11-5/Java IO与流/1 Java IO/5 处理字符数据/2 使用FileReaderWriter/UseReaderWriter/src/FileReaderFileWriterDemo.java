import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileReaderFileWriterDemo {
    final static String MSG = "使用默认编码，读取和写入文本文件";
    final static String FILE_NAME = "temp.txt";
    public static void main(String[] args) throws IOException {
        System.out.println("默认编码：" + Charset.defaultCharset());
        //写入文件
        try (FileWriter fw = new FileWriter(FILE_NAME)) {
            fw.write(MSG, 0, MSG.length());
        }
        //读取文件
        char[] buf = new char[MSG.length()];
        try (FileReader fr = new FileReader(FILE_NAME)) {
            fr.read(buf, 0, MSG.length());
            System.out.println(buf);
        }
    }
}