import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class UseBuffer {
    public static void main(String[] args) {
//        testLimit();
//        testDirectAndHasArray();
//        readAvailableData();
        testCompact();
    }

    //读取所有可读取的数据
    private static void readAvailableData() {
        char[] charArr = {'a', 'b', 'c', 'd', 'e'};
        CharBuffer charBuffer = CharBuffer.wrap(charArr);
        charBuffer.limit(3);
        charBuffer.position(0);
        //当还有数据可读时，hasRemaining()=true
        while(charBuffer.hasRemaining()){
            System.out.println(charBuffer.get());
        }
        System.out.println("\n还原默认值\n");
        charBuffer.clear();
        while(charBuffer.hasRemaining()){
            System.out.println(charBuffer.get());
        }
    }




    //检测Limit的含义
    private static void testLimit() {
        char[] charArr = {'a', 'b', 'c', 'd', 'e'};
        CharBuffer charBuffer = CharBuffer.wrap(charArr);

        System.out.println(charBuffer.length());//5
        System.out.println(charBuffer.capacity());//5
        System.out.println(charBuffer.limit());//5
        System.out.println(charBuffer.position());//0


        charBuffer.limit(2);
        System.out.println(charBuffer.limit());//5
        System.out.println(charBuffer.get(1));//b
        //超过Limit,抛出IndexOutOfBoundsException
        System.out.println(charBuffer.get(2));
    }

    private static void testCompact(){
        char[] charArr = {'0','1', '2', '3', '4', '5','6','7','8','9'};
        CharBuffer charBuffer = CharBuffer.wrap(charArr);
        //0 1 2 3 4 5 6 7 8 9
        while (charBuffer.hasRemaining()){
            System.out.print(charBuffer.get()+" ");
        }
        System.out.println("\n设置position=2");
        charBuffer.position(2);
        MyBufferUtils.printBufferInfo(charBuffer);
        System.out.println("执行compact操作");
        charBuffer.compact();
        MyBufferUtils.printBufferInfo(charBuffer);
        System.out.println("输出内容");
        //8,9
        while (charBuffer.hasRemaining()){
            System.out.print(charBuffer.get()+" ");
        }
    }

}
