import java.io.Closeable;
import java.io.IOException;

public class MyAutoClosable implements Closeable {
    @Override
    public void close() throws IOException {
        System.out.println("Close方法被调用");
    }

    public static void main(String[] args) {
        try (var obj = new MyAutoClosable()) {
            System.out.println(obj.getClass().getName() + "实例化。");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
