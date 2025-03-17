import java.io.*;
import java.nio.charset.StandardCharsets;

public class UseBufferedReaderWriter {

    static String[] poem = {
            "白日依山尽",
            "黄河入海流",
            "欲穷千里目",
            "更上一层楼"
    };

    public static void main(String[] args) throws IOException {
        writeFile(poem);
        System.out.println("\n----从文件中读----\n");
        readFile();
    }

    static void writeFile(String[] poem) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter("poem.txt", StandardCharsets.UTF_8))) {
            for (var statement : poem) {
                bw.write(statement);
                bw.newLine();
            }
            bw.flush();//立即刷新
        }
        System.out.println("写入完成");
    }

    static void readFile() throws IOException {
        try (BufferedReader br = new BufferedReader(
                new FileReader("poem.txt",
                        StandardCharsets.UTF_8))) {
            String statement;
            //按行读
            while ((statement = br.readLine()) != null) {
                System.out.println(statement);
            }
        }
    }
}
