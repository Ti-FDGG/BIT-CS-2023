import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

public class WordTypeCount {
    public static void main(String[] args) {
        Map<String, Integer> myMap = new HashMap<>();
        createMap(myMap);
        System.out.println(myMap);
    }

    private static void createMap(Map<String, Integer> map) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个英文句子:");
        String input = scanner.nextLine();

        var tokens = input.split(" ");
        for (String token : tokens) {
            String word = token.toLowerCase();
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else
                map.put(word, 1);
        }
    }


}
