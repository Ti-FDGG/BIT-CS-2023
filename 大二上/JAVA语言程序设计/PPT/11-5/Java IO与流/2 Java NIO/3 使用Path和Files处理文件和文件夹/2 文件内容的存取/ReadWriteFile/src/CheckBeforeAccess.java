import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CheckBeforeAccess {
    public static void main(String[] args) throws IOException {
        Path fileToAccess = Path.of("poem.txt");
        if (isFileAccessible(fileToAccess)) {
            System.out.println(Files.readString(fileToAccess));
        }
    }
    private static boolean isFileAccessible(Path file) {
        return Files.isRegularFile(file) && Files.isReadable(file);
    }
}
