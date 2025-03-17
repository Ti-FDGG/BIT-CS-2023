import java.io.IOException;

public class CharArrayReader {
    public static void main(String[] args) throws IOException {
        useCharArrayReader();
    }

    private static void useCharArrayReader()
            throws IOException {
        char[] englishChars = {'a', 'b', 'c', 'd', 'e',
                'f', 'g', 'h', 'i', 'j'};
        printElementsOfCharArray(englishChars);
        System.out.println();
        char[] chineseWords={'中','文','字','符'};
        printElementsOfCharArray(chineseWords);
    }

    //使用CharArrayReader输出字符数组的所有元素
    private static void printElementsOfCharArray(char[] charArr)
            throws IOException {
      try(var cr = new java.io.CharArrayReader(charArr)){
          int x;
          while ((x = cr.read()) != -1) {
              System.out.print((char) x);
          }
      }
    }
}
