package filetree;

import java.nio.file.FileSystems;

public class UseFileStore {
    public static void main(String[] args) {
        FileSystems.getDefault()
                .getFileStores()
                .forEach(System.out::println);
    }
}
