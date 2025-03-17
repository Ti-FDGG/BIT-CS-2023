import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

//展示文件与文件夹的常规操作
public class FileAndFolderOption {

    public static void main(String[] args) throws IOException {
//        copyFile();
//        createAndDeleteFile();
//        createTempFile();
//        createFolder();
        moveFile();
    }



    //文件复制
    private static void copyFile() throws IOException {
        Path source = Paths.get("poem.txt");
        Path target = Paths.get("poem_copied.txt");
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("复制结束");
    }

    //创建文件夹
    private static void createFolder() throws IOException {
        Path newDir = Paths.get("data");
        if (!Files.exists(newDir)) {
            Files.createDirectory(newDir);
            System.out.println("文件夹创建成功");
        } else {
            System.out.println("文件夹己经创建过了。");
        }
    }

    //创建和删除文件
    private static void createAndDeleteFile() throws IOException {
        Path createdFilePath = Files.createFile(Paths.get("myFile.txt"));
        System.out.println(createdFilePath + " 文件己创建。");
        boolean deleteIfExists = Files.deleteIfExists(Paths.get("myFile.txt"));
        System.out.println("删除结果 = " + deleteIfExists);
    }

    //创建临时文件
    private static void createTempFile() throws IOException {
        Path tempFile = Files.createTempFile("myapp", ".tmp");
        //C:\Users\JinXu\AppData\Local\Temp\myapp14342790709253233910.tmp
        System.out.println(tempFile);
        //删除它
        Files.deleteIfExists(tempFile);
    }

    //移动文件
    private static void moveFile() throws IOException {
        Path sourceFile = Paths.get("poem.txt");
        Path targetDir = Paths.get("./data/poem-moved.txt");
        try {
            Files.move(sourceFile, targetDir, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("文件移动结束");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
