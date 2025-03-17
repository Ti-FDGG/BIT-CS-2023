import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTest {
    public static void main(String[] args) throws IOException {
//        getCurrentDir();

//        createPathUseFileSystem();

//        fileObjToPathObj();

//        getPathSegmentInfo();

//        realPathAndAbsolutePath();

//        normalize();

//        resolveAndRelativize();

        useResourceFile();
    }

    //获取当前文件夹
    private static void getCurrentDir() {
        System.out.println(System.getProperty("user.dir"));
        System.out.println(Path.of("").toAbsolutePath());
        System.out.println(Paths.get(".").normalize().toAbsolutePath());
    }

    private static void normalize() {
        Path somePath = Paths.get("c:/windows/./System32/drivers//..");
        //normalize会移除“.”和“..”
        //输出 c:\windows\System32
        System.out.println(somePath.normalize());
    }

    private static void realPathAndAbsolutePath() throws IOException {
        //"."表示当前文件夹
        Path currentDir = Paths.get(".");
        //样例输出：C:\Java NIO\PathAndFiles\.
        System.out.println(currentDir.toAbsolutePath());
        //样例输出：C:\Java NIO\PathAndFiles\.
        System.out.println(currentDir.toRealPath());
        //".."表示当前文件夹的上一级文件夹
        Path currentParent = Paths.get("..");
        //样例输出：C:\Java NIO
        System.out.println(currentParent.toRealPath());

        //如果文件夹不存在
        Path notExistPath = Paths.get("c:\\notExist");
        //C:\notExist
        System.out.println(notExistPath.toAbsolutePath());
        //抛出NoSuchFileException
        System.out.println(notExistPath.toRealPath());
    }

    private static void getPathSegmentInfo() {
        Path path = Paths.get("C:/windows/nodepad.exe");
        System.out.println("path name count:" + path.getNameCount()); //2
        System.out.println(path.getName(0)); //windows
        System.out.println(path.getName(1)); //nodepad.exe
        //filename: nodepad.exe
        System.out.println("filename: " + path.getFileName());
        //parent: C:\windows
        System.out.println("parent: " + path.getParent());
    }

    private static void resolveAndRelativize() throws IOException {
        Path windowsPath = Paths.get("C:", "windows");
        //解析子文件夹
        Path system32Path = windowsPath.resolve("System32");
        //C:\windows\System32
        System.out.println(system32Path.toRealPath());
        //解析兄弟文件夹
        Path siblingPath = system32Path.resolveSibling("Help");
        //C:\Windows\Help
        System.out.println(siblingPath.toRealPath());
        //生成从一个Path到另一个Path的“转移路线”
        Path etcPath = Paths.get("C:\\Windows\\System32\\drivers\\etc");
        Path helpPath = Paths.get("C:\\Windows\\Help");
        Path relativzieResult = etcPath.relativize(helpPath);
        //输出：..\..\..\Help
        System.out.println(relativzieResult);
    }

    private static void fileObjToPathObj() {
        File windowsDir = new File("c:\\windows");
        //可以把File转换为Path
        Path windowsPath = windowsDir.toPath();
        System.out.println(windowsPath);
    }

    //使用FileStstem创建Path实例
    private static void createPathUseFileSystem() {
        FileSystem fileSystem = FileSystems.getDefault();
        Path windowsPath = fileSystem.getPath("c:/windows");
        Path windowsPath2 = fileSystem.getPath("c:\\windows");
        //上述两个路径，其实是一样的
        System.out.println(windowsPath.equals(windowsPath2));
        //获取路径分隔符,Windows下是“\”
        System.out.println(fileSystem.getSeparator());
        //不管是使用“\\”还是“/”构建Path实例，最后输出的，都是当前操作系统默认的分隔符
        System.out.println(windowsPath.toString());//c:\windows
        System.out.println(windowsPath2.toString());//c:\windows
    }

    private static void useResourceFile() {
        var pathDemo = new PathTest();
        var dataFile = pathDemo.getClass().getResource("data.txt");
        System.out.println(dataFile);
        var testFile = pathDemo.getClass().getResource("test.txt");
        System.out.println(testFile);
    }

}
