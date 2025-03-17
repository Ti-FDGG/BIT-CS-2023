import java.nio.Buffer;
import java.nio.ByteBuffer;

public class LimitAndPosition {
    public static void main(String[] args) {
        Buffer buffer = ByteBuffer.allocate(7);
        MyBufferUtils.printBufferInfo(buffer);

        System.out.println("Changing buffer limit to 5");
        buffer.limit(5);
        MyBufferUtils.printBufferInfo(buffer);
        System.out.println("Changing buffer position to 3");
        buffer.position(3);
        MyBufferUtils.printBufferInfo(buffer);
        //java.nio.HeapByteBuffer[pos=3 lim=5 cap=7]
        System.out.println(buffer);
    }
}