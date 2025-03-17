import java.io.File;
import java.io.FilenameFilter;

//使用FilenameFilter，过滤文件
public class FilterFiles {
    public static void main(final String[] args) {
        findAllExeInWindowsFolder();
    }
    //查找Windows文件夹下所有的exe文件
    private static void findAllExeInWindowsFolder() {
        String dir = "c:\\windows";
        String filterPattern = ".exe";
        File file = new File(dir);
        //指定要列出的文件类型
        FilenameFilter fnf = (dir1, name) -> name.endsWith(filterPattern);
        String[] names = file.list(fnf);
        //list方法可能返回一个null值……
        assert names != null;
        for (String name : names)
            System.out.println(name);
    }
}

