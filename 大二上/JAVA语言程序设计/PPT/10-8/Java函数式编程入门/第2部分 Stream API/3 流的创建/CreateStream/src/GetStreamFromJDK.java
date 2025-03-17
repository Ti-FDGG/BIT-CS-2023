import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class GetStreamFromJDK {

    public static void main(String[] args) {
        //useNIOFileStream();

        listFileTree();
    }

    private static void useNIOFileStream() {
        //lines()方法，返回的就是一个字符串流
        try (Stream<String> lines = Files.lines(
                Paths.get("data.txt"))) {
            lines.forEach(System.out::println);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // 列出当前文件夹下的所有文件
    public static void listFileTree() {
        Path dir = Paths.get("");
        System.out.printf(" %s contains：\n", dir.toAbsolutePath());
        //walk方法返回一个Stream
        try (Stream<Path> fileTree = Files.walk(dir)) {
            fileTree.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
