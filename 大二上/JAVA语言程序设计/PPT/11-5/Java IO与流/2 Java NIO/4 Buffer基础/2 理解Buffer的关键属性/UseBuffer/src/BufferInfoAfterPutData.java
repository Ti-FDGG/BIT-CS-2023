import java.nio.ByteBuffer;

//展示存入数据后各参数值的变化
public class BufferInfoAfterPutData {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(7);
        MyBufferUtils.printBufferInfo(buffer);
        //写入数据
        buffer.put((byte) 10).put((byte) 20).put((byte) 30);
        MyBufferUtils.printBufferInfo(buffer);
        //输出其内容
        for (int i = 0; i < buffer.limit(); i++)
            System.out.print(buffer.get(i) + ",");
    }
}