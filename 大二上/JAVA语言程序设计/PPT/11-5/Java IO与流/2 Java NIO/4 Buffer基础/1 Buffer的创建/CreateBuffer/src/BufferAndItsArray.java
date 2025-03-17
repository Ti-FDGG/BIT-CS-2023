import java.nio.ByteBuffer;

public class BufferAndItsArray {

    public static void main(String[] args) {
//        testDirectAndHasArray();
        bufferAndItsArray();
    }
    //展示Buffer与底层数组之间的关系
    private static void bufferAndItsArray() {
        ByteBuffer buffer1 = ByteBuffer.allocate(10);
        if (buffer1.hasArray()) {
            //buffer1 array: [B@6d311334
            System.out.println("buffer1 array: " + buffer1.array());
            //Buffer1 array offset: 0
            System.out.println("Buffer1 array offset: " +
                    buffer1.arrayOffset());
           MyBufferUtils.printBufferInfo(buffer1);

            System.out.println();
        }

        byte[] bytes = new byte[200];
        //将offset设置为10,则positon将等于arrayOffset值+此处offset的值
        ByteBuffer buffer2 = ByteBuffer.wrap(bytes, 10, 50);
        if (buffer2.hasArray()) {
            //buffer2 array: [B@4dd8dc3
            System.out.println("buffer2 array: " + buffer2.array());
            //Buffer2 array offset: 0
            System.out.println("Buffer2 array offset: " +
                    buffer2.arrayOffset());
           MyBufferUtils.printBufferInfo(buffer2);
        }
    }

    //测试isDirect和hasArray方法
    private static void testDirectAndHasArray() {
        byte[] arr = {0, 1, 2, 3, 4, 5};

        ByteBuffer buffer1 = ByteBuffer.wrap(arr);
        System.out.println(buffer1.isDirect()); //false
        System.out.println(buffer1.hasArray()); //true

        ByteBuffer buffer2 = ByteBuffer.allocate(6);
        System.out.println(buffer2.isDirect()); //false
        System.out.println(buffer2.hasArray()); //true

        ByteBuffer buffer3 = ByteBuffer.allocateDirect(6);
        System.out.println(buffer3.isDirect()); //true
        System.out.println(buffer3.hasArray()); //false
    }
}