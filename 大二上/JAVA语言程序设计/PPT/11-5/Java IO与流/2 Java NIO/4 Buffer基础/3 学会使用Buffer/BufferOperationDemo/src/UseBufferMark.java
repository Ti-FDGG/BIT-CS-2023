import java.nio.ByteBuffer;

public class UseBufferMark {
    public static void main(String[] args) {
        ByteBuffer buffer=ByteBuffer.allocate(7);
        buffer.put((byte)10).put((byte)20).put((byte) 30).put((byte) 40);
        buffer.limit(4);
        //给第2个位置加上一个书签，之后再移动到第4个位置
        buffer.position(1).mark().position(3);
        System.out.println(buffer.get());//40
        System.out.println();
        //回到书签处
        buffer.reset();
        //输出剩余的内容：20，30，40
        while (buffer.hasRemaining()){
            System.out.println(buffer.get()+",");
        }
        //回到开头：mark清除，位置position值归0,limit不变
        buffer.rewind();
        MyBufferUtils.printBufferInfo(buffer);
    }
}
