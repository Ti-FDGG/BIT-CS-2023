import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class EncodingDetails {
    public static void main(String[] args) {
        //默认字符编码：UTF-8
        System.out.println("Default Character Encoding: " + System.getProperty("file.encoding"));
        printEncodingDetails("abc");
        printEncodingDetails("€");
        printEncodingDetails("\u1F602");
        printEncodingDetails("中国");
    }
    private static void printEncodingDetails(String symbol) {
        System.out.println("\nSymbol: " + symbol);
        System.out.println("ASCII: " + Arrays.toString(symbol.getBytes(StandardCharsets.US_ASCII)));
        System.out.println("ISO-8859-1: " + Arrays.toString(symbol.getBytes(StandardCharsets.ISO_8859_1)));
        System.out.println("UTF-8: " + Arrays.toString(symbol.getBytes(StandardCharsets.UTF_8)));
        System.out.println("UTF-16: " + Arrays.toString(symbol.getBytes(StandardCharsets.UTF_16)));
        System.out.println("UTF-16 BE: " + Arrays.toString(symbol.getBytes(StandardCharsets.UTF_16BE)));
        System.out.println("UTF-16 LE: " + Arrays.toString(symbol.getBytes(StandardCharsets.UTF_16LE)));
    }
}
