import java.nio.charset.Charset;

public class CharsetInfo {
    public static void main(String[] args) {
        //获取默认字符集
        System.out.println("默认字符集：" + Charset.defaultCharset());
        System.out.println("\n=====支持的字符集列表======\n");
        //获取当前JVM所支持的所有字符集
        var charsets = Charset.availableCharsets();
        charsets.forEach((key, charset) -> {
            System.out.println(key + ":" + charset);
        });
    }
}
