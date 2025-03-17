import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class UseMarkDemo {

    static String filePath = "data.txt";

    public static void main(String[] args) throws IOException {

        readFromFileUseMark();
    }


    private static void readFromFileUseMark() throws IOException {
        byte[] buff = initBuffer();

        readFromFileUseMarkSuccess(buff);

        readFromFileUseMarkFailure(buff);
    }

    private static void readFromFileUseMarkSuccess(byte[] buff) {
        try (
                var inputStream = new ByteArrayInputStream(buff);
                var reader = new InputStreamReader(inputStream);
                //缓冲区大小设置为4个字节
                var bufferedReader = new BufferedReader(reader, 4);
        ) {
            System.out.println(bufferedReader.read());//0
            //给“1”打上书签，并设置从这里往后再读5个字节，这时，缓冲区实际上可以放5个数字
            bufferedReader.mark(5);
            System.out.println(bufferedReader.read());//1
            System.out.println(bufferedReader.read());//2
            System.out.println(bufferedReader.read());//3
            System.out.println(bufferedReader.read());//4
            System.out.println(bufferedReader.read());//5
            //在这里reset,没问题，因为这时”1“还在缓冲区中
            bufferedReader.reset();
            System.out.println();
            System.out.println(bufferedReader.read());//1
            System.out.println(bufferedReader.read());//2
            System.out.println(bufferedReader.read());//3

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFromFileUseMarkFailure(byte[] buff) {
        try (
                var inputStream = new ByteArrayInputStream(buff);
                var reader = new InputStreamReader(inputStream);
                //缓冲区大小设置为4个字节
                var bufferedReader = new BufferedReader(reader, 4);
        ) {
            System.out.println(bufferedReader.read());//0
            //给“1”打上书签，并设置从这里往后再读5个字节，书签应该有效，这时，缓冲区实际上可以放5个数字
            bufferedReader.mark(5);
            //bufferedReader.mark(6);
            System.out.println(bufferedReader.read());//1
            System.out.println(bufferedReader.read());//2
            System.out.println(bufferedReader.read());//3
            System.out.println(bufferedReader.read());//4
            System.out.println(bufferedReader.read());//5
            //读入5以后，继续往后读，这时”1“己不在缓冲区
            System.out.println(bufferedReader.read());//6
            //在这里reset,会失败，因为”1“己不在缓冲区
            //如果将前面readAheadLimit设置为6,则reset又可以成功了，因为”1“这时还在缓冲区中
            bufferedReader.reset();
            System.out.println();
            System.out.println(bufferedReader.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] initBuffer() {
        byte[] buff = new byte[127];
        for (int i = 0; i < buff.length; i++) {
            buff[i] = (byte) i;
        }
        return buff;
    }
}
