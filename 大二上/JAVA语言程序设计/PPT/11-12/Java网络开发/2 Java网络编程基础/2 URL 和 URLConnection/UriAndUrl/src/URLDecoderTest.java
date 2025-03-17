import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLDecoderTest {
    public static void main(String[] args)
            throws Exception {
        // 将普通字符串转换成
        // application/x-www-form-urlencoded字符串
        String urlStr = URLEncoder.encode(
                "中国人", "UTF-8");
        System.out.println(urlStr);

        // 将application/x-www-form-urlencoded字符串
        // 转换成普通字符串
        String keyWord = URLDecoder.decode(
                "%E4%B8%AD%E5%9B%BD%E4%BA%BA", "utf-8");
        System.out.println(keyWord);
    }
}
