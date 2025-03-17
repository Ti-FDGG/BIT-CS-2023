import java.nio.CharBuffer;

public class TestCompact {
    public static void main(String[] args) {
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
