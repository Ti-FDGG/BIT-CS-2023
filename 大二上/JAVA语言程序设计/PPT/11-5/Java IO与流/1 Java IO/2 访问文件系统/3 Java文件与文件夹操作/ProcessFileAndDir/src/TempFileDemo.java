import java.io.File;
import java.io.IOException;

//创建临时文件
public class TempFileDemo {
    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("java.io.tmpdir"));
        File temp = File.createTempFile("text", ".txt");
        System.out.println(temp);
        //退出时删除
        temp.deleteOnExit();
    }
}