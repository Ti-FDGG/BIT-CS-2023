import java.io.File;
import java.io.IOException;
//分解路径字符串
public class DecomposeFilePath {
    public static void main(final String[] args) throws IOException {
        //"."表示当前路径
        String path = "./src/DecomposeFilePath.java";
        File file = new File(path);
        System.out.println("Absolute path = " + file.getAbsolutePath());
        //Canonical形式是规范化后的结果
        System.out.println("Canonical path = " + file.getCanonicalPath());
        System.out.println("Name = " + file.getName());
        System.out.println("Parent = " + file.getParent());
        System.out.println("Path = " + file.getPath());
        System.out.println("Is absolute = " + file.isAbsolute());
    }
}



