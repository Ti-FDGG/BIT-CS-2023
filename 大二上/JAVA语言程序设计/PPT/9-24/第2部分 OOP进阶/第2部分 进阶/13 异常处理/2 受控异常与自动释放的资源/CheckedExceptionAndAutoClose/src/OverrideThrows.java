import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class OverrideThrows {
    public void test() throws IOException {
        var fis = new FileInputStream("a.txt");
    }
}

class Sub extends OverrideThrows {
    //你可以试一试，
    //如果test方法声明抛出了比父类方法更大的异常,比如Exception
    //则代码将无法编译……
    public void test() throws FileNotFoundException {
        //...
    }
}