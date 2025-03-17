import javax.sound.sampled.Line;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerDemo {
    public static void main(String[] args) throws FileNotFoundException {

//        userInputFromKeyboard();

//        scanString();

//        useDelimiter();

//        loopScanAndAutoClose();

//        readJavaFile();

        readAndProcessDataFile();
    }

    private static void userInputFromKeyboard() {
        System.out.print("请输入一个字符串：");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        System.out.println("\n用户输入了：" + userInput);
    }

    //使用next系列方法从字符串中分解Token
    private static void scanString() {
        //要处理的原始字符串
        String line = "hello 1 2.5 true";
        var scLine = new Scanner(line);
        //读取第一个Token
        String word = scLine.next();
        System.out.println(word);
        //读取整数
        int num = scLine.nextInt();
        System.out.println(num * 2);
        //读取double数值
        double dNum = scLine.nextDouble();
        System.out.println(dNum * 100 / 5.0);
        //读取boolearn数值
        boolean bool = scLine.nextBoolean();
        System.out.println(bool == true);
        scLine.close();
    }

    //展示如何指定要切割字符串所使用的分隔符
    private static void useDelimiter() {
        String dataString = "abc#1#2.5#true";
        //指定分隔符
        Scanner scanner = new Scanner(dataString).useDelimiter("#");
        var word = scanner.next();
        var num = scanner.nextInt();
        var fnum = scanner.nextFloat();
        var bool = scanner.nextBoolean();
        System.out.printf("%s %d %f %b", word, num, fnum, bool);
        scanner.close();
    }

    //循环读取和自动关闭
    private static void loopScanAndAutoClose() {
        var userInput = JOptionPane.showInputDialog("请输入一个英文句子");
        try (Scanner scanner = new Scanner(userInput)) {
            while (scanner.hasNext()) {
                System.out.println(scanner.next());
            }
        }
    }

    //读取Java文件中的所有行
    private static void readJavaFile() throws FileNotFoundException {
        try (var scanner = new Scanner(new File("src/ScannerDemo.java"))) {
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        }
    }


    //从数据文件中逐行读入，并且处理它们
    private static void readAndProcessDataFile() throws FileNotFoundException {
        //本地内部类，封装行的处理功能
        class LineProcessor {
            String word, line;
            int num;
            boolean bool;
            void process(Scanner lineScanner) {
                word = lineScanner.next();
                num = lineScanner.nextInt();
                bool = lineScanner.nextBoolean();
                System.out.printf("%s %d %b \n", word, num, bool);
            }
        }
        LineProcessor processor = new LineProcessor();
        String line;
        try (var scanner = new Scanner(new File("data/mydata.txt"))) {
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                try (var lineScanner = new Scanner(line).useDelimiter("#")) {
                    processor.process(lineScanner);
                }
            }
        }
    }







}
