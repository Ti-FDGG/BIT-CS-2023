import java.io.File;
import java.io.IOException;

import java.util.Date;
//使用File类实例获取文件或文件夹的相关信息
public class GetFileAndDirectoryInfo {
    public static void main(final String[] args) throws IOException {
        //指向一个具体文件
        printFileInfo("./src/GetFileAndDirectoryInfo.java");
        System.out.println("==================");
        //指向一个文件夹
        printFileInfo("./src");
    }

    private static void printFileInfo(String filePath) {
        File file = new File(filePath);
        System.out.println(file + " 相关信息:");
        System.out.println("Exists = " + file.exists());
        System.out.println("Is directory = " + file.isDirectory());
        System.out.println("Is file = " + file.isFile());
        System.out.println("Is hidden = " + file.isHidden());
        System.out.println("Length = " + file.length());
        System.out.println("lastModified = "+file.lastModified());
        System.out.println("canExecute =" + file.canExecute());
        System.out.println("canRead =" + file.canRead());
        System.out.println("canWrite =" + file.canWrite());
    }
}