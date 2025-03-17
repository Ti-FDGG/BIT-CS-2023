import java.nio.CharBuffer;

//展示flip与clear操作
public class FlipAndClear {
    public static void main(String[] args) {
        String[] poem =
                {
                        "海上生明月，天涯共此时。",
                        "情人怨遥夜，竟夕起相思。",
                        "灭烛怜光满，披衣觉露滋。",
                        "不堪盈手赠，还寝梦佳期"
                };

        CharBuffer buffer = CharBuffer.allocate(250);
        //向缓冲区中写入数据
        for (String s : poem) {
            for (int j = 0; j < s.length(); j++)
                buffer.put(s.charAt(j));
        }

        System.out.println("向缓冲区中写入数据之后");
        MyBufferUtils.printBufferInfo(buffer);
        //翻转缓冲区
        buffer.flip();
        System.out.println("翻转缓冲区之后");
        MyBufferUtils.printBufferInfo(buffer);
        // 读出数据
        while (buffer.hasRemaining())
            System.out.print(buffer.get());
        System.out.println("\n\n");
        //清空缓冲区
        buffer.clear();
        System.out.println("清空缓冲区之后");
        MyBufferUtils.printBufferInfo(buffer);
        System.out.println();
    }

}