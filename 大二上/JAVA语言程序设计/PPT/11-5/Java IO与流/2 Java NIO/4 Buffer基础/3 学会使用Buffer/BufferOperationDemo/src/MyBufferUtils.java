import java.nio.Buffer;

public class MyBufferUtils {
    //打印Buffer四个关键信息
    public static void printBufferInfo(Buffer buffer){
        System.out.println("================================");
        System.out.println("Capacity: " + buffer.capacity());
        System.out.println("Limit: " + buffer.limit());
        System.out.println("Position: " + buffer.position());
        System.out.println("Remaining: " + buffer.remaining());
        System.out.println("================================");
    }
}
