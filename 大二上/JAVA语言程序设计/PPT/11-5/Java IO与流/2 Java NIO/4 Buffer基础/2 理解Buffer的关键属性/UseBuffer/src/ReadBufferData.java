import java.nio.CharBuffer;

public class ReadBufferData {
    public static void main(String[] args) {
        readAvailableData();
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

}
