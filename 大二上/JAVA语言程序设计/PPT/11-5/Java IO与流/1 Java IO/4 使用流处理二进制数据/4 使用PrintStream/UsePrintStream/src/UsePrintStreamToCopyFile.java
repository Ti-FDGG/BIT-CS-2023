import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

public class UsePrintStreamToCopyFile {
    public static void main(String[] args) {
        String sourceImagePath = "images/myimage.jpg";
        String targetImagePath = "images/myimage_copy.jpg";
        try (var fileInputStream = new FileInputStream(sourceImagePath);
             var printStream = new PrintStream(targetImagePath);
        ) {
            //直接将数据从一个流复制到另一个流
            fileInputStream.transferTo(printStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("文件复制结束");
    }
}
