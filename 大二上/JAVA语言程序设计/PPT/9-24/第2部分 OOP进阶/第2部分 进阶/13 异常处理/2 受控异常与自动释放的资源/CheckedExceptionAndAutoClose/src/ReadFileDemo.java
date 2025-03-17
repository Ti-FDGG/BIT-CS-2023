import java.io.*;

public class ReadFileDemo {
    public static void main(String[] args) {
        traditionalReadFile();
        readFileUseTryWithResources();
    }

    static void traditionalReadFile() {
        File file = new File("hello.txt");
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            //使用reader从文件中读取数据
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static void readFileUseTryWithResources() {
        File file = new File("hello.txt");
        try (var reader = new FileReader(file)) {
            //使用reader从文件中读取数据
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
