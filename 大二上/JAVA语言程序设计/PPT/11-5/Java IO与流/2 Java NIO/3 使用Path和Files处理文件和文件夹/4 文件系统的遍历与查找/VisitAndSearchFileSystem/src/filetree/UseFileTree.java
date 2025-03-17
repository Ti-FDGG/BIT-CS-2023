package filetree;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class UseFileTree {
    public static void main(String[] args) throws IOException {
        //listCurrentPathContent();
        //useDirectoryStream();
        //useWalk(Paths.get("."));
        //useFileVisitor(Paths.get("."));
    }

    //如果需要比较灵活的处理，可以使用FileVisitor遍历文件系统
    private static void useFileVisitor(Path fileDir) throws IOException {
        var fileVisitor = new MyFileVisitor();
        Files.walkFileTree(fileDir, fileVisitor);
    }

    //使用Files.walk方法深度遍历文件系统
    private static void useWalk(Path fileDir) throws IOException {
        //使用Stream API，可以得到最简单的深度遍历方式
        Files.walk(fileDir, FileVisitOption.FOLLOW_LINKS)
                .forEach(System.out::println);
    }

    //使用list方法列举指定文件夹下的文件
    private static void listCurrentPathContent() throws IOException {
        Path curDir = Paths.get("./src");
        try (var files = Files.list(curDir)) {
            files.forEach(System.out::println);
        }
    }

    //使用DirectoryStream列举指定文件夹下的文件
    private static void useDirectoryStream() throws IOException {
        Path curDir = Paths.get("./src");
        try (var files = Files.newDirectoryStream(curDir)) {
            files.forEach(System.out::println);
        }
    }

}
