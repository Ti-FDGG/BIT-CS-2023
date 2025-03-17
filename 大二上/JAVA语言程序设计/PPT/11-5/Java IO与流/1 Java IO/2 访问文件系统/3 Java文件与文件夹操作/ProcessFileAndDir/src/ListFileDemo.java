import java.io.File;

public class ListFileDemo {
    public static void main(String[] args) {
        //listFileSystemRoots();
        listFilesOfDir( new File("c:\\windows"));
    }

    //使用list方法来列出指定文件夹下的所有文件和子文件夹
    private static void listFilesOfDir(File file) {
        String[] fileList = file.list();
        System.out.println("======c:\\windows=====");
        for (String fileName : fileList) {
            System.out.println(fileName);
        }
    }

    //列出当前计算机上所有的“顶层文件夹”（即root）
    private static void listFileSystemRoots() {
        File[] roots = File.listRoots();
        for (File root : roots)
            System.out.println(root);
    }

}