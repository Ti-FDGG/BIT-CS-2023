import java.nio.CharBuffer;

public class TestLimit {
    public static void main(String[] args) {
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
}
