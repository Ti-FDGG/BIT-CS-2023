import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

public class FindFiles {
    public static void main(String[] args) throws IOException {
        useDirectoryStreamToFind();
        useFind();
        useWalkFileTreeToFind();


    }

    private static void useFind() throws IOException {
        //在当前文件夹下递归查找，列出所有的java文件
        Path curDir = Paths.get(".");
        BiPredicate<Path, BasicFileAttributes> findWhat = (path, attributes) ->
                !attributes.isDirectory() && path.toString().contains(".java");
        try (Stream<Path> files = Files.find(curDir,
                Integer.MAX_VALUE, findWhat)) {
            files.forEach(System.out::println);
        }
    }

    private static void useWalkFileTreeToFind() throws IOException {
        Path searchDir = Paths.get(".");
        FileFinder fileFinder = new FileFinder("*.java");
        Files.walkFileTree(searchDir, fileFinder);
        fileFinder.foundPaths.forEach(System.out::println);
    }

    private static void useDirectoryStreamToFind() throws IOException {
        Path searchDir = Paths.get("./src");
        try (var files = Files.newDirectoryStream(searchDir,
                file -> file.getFileName().toString().endsWith(".java"))) {
            files.forEach(f -> {
                System.out.println(f.getFileName());
            });
        }
    }


}

//JDK 7 +
class FileFinder extends SimpleFileVisitor<Path> {
    private final PathMatcher matcher;
    public ArrayList<Path> foundPaths = new ArrayList<>();

    public FileFinder(String pattern) {
        matcher = FileSystems.getDefault()
                .getPathMatcher("glob:" + pattern);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
            throws IOException {
        if (matcher.matches(file.getFileName())) {
            foundPaths.add(file);
        }
        return FileVisitResult.CONTINUE;
    }
}

