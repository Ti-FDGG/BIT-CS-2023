import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileSystemInfo {
    public static void main(String[] args) throws IOException {

        //getFileSystemInfo();
        getAllSystemProperties();

    }

    private static void getFileSystemInfo() throws IOException {
        //获取用户当前工作目录
        System.out.println(System.getProperty("user.dir"));
        //另一种方式：
        System.out.println(new File(".").getCanonicalPath());
        //获取用户目录
        System.out.println(System.getProperty("user.home"));

        //获取当前操作系统的路径分隔符
        System.out.println(File.separator);

        //获取当前操作系统的换行符
        var separator = System.getProperty("line.separator");
        //由于换行符不可见，所以，这里输出它的ASCII码值
        separator.chars().forEach(System.out::println);
    }

    //获取当前操作系统的相关参数信息
    private static void getAllSystemProperties() {
        System.getProperties().forEach((key, value) -> {
            System.out.println(key + ":" + value);
        });
    }
}
