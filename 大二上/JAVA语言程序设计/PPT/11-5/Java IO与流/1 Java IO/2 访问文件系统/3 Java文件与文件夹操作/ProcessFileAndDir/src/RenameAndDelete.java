import java.io.File;
import java.io.IOException;

public class RenameAndDelete {
    public static void main(String[] args) throws IOException {
        File file = new File("test.txt");
        if (!file.exists()) {
            boolean result = file.createNewFile();
            if (result) {
                System.out.println(file + "已经创建");
            }
        }
        File renamed = new File("test_rename.txt");
        boolean renameresult = file.renameTo(renamed);
        if (renameresult) {
            //验证文件必须存在
            assert renamed.exists();
            System.out.println("改名成功：" + renamed);
        }
        boolean deleteResult = renamed.delete();
        if (deleteResult) {
            System.out.println("删除成功：" + renamed);
        }
    }
}
