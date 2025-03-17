import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ReadWriteFileDemo {
    public static void main(String[] args) throws IOException {

//        writeToFile();
//        useWriteString();
//        useBufferedWriter();
//        readFileLineByLine();
//        useBufferReader();
//        readAlllinesFromFile();
        readAllBytesDemo();
    }

    //#region 文件读取

    private static void useBufferReader() throws IOException {
        Path source = Paths.get("poem.txt");
        //使用Files，可以直接创建BufferedReader
        try (BufferedReader reader = Files.newBufferedReader(source,
                StandardCharsets.UTF_8)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    //使用lines方法逐行读取文本文件
    private static void readFileLineByLine() throws IOException {
        Path filePath = Paths.get(".", "poem.txt");
        try (Stream<String> lines = Files.lines(filePath)) {
            lines.forEach(System.out::println);
        }
    }

    //使用readAllBytes()读取所有文件内容
    private static void readAllBytesDemo() throws IOException {
        Path path = Paths.get("poem.txt");
        byte[] fileContentData = Files.readAllBytes(path);
        System.out.println("读入字节数：" + fileContentData.length);
        String fileContent = new String(fileContentData,
                0, fileContentData.length, StandardCharsets.UTF_8);
        System.out.println(fileContent);
    }

    //使用readAllLines()读取所有文件内容
    static void readAlllinesFromFile() throws IOException {
        Path filePath = Paths.get("poem.txt");
        Files.readAllLines(filePath)
                .forEach(System.out::println);
    }

    //endregion

    //#region 文件写入

    //使用write方法写入文件
    static void writeToFile() {
        Path textFile = Paths.get("study.txt");
        String line1 = "好好学习，";
        String line2 = "天天向上！";
        List<String> lines = Arrays.asList(line1, line2);
        try {
            Files.write(textFile, lines, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("文件写入完毕！");
    }

    //使用writeString方法写入文件
    static void useWriteString() throws IOException {
        Path testFilePath = Path.of("poem.txt");
        String poem = """
                龟虽寿
                神龟虽寿，犹有竟时；
                螣蛇乘雾，终为土灰。
                老骥伏枥，志在千里；
                烈士暮年，壮心不已。
                盈缩之期，不但在天；
                养怡之福，可得永年。
                幸甚至哉，歌以咏志。
                """;
        Files.writeString(testFilePath, poem);
        System.out.println("文件写入完毕！");
    }

    //使用BufferedWriter写入文件
    static void useBufferedWriter() throws IOException {
        try (var writer = Files.newBufferedWriter(
                Path.of("poem2.txt"))) {
            writer.write("《山居秋暝》");
            writer.newLine();
            writer.write("唐·王维");
            writer.newLine();
            writer.write("空山新雨后，天气晚来秋。");
            writer.newLine();
            writer.write("明月松间照，清泉石上流。");
            writer.newLine();
            writer.write("竹喧归浣女，莲动下渔舟。");
            writer.newLine();
            writer.write("随意春芳歇，王孙自可留。");
            writer.newLine();
        }
        System.out.println("文件写入完毕！");
    }

    //#endregion

}
