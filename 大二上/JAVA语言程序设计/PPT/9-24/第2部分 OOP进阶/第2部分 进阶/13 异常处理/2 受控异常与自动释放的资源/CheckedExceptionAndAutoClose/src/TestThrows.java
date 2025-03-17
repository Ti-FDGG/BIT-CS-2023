import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TestThrows {
    public static void main(String[] args) throws FileNotFoundException {
        //如果取消注释，以下代码无法通过编译
       FileInputStream fis=new FileInputStream("a.txt");


    }
}
