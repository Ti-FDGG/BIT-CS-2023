import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class ProcessList {
    public static void main(String[] args) {
        //原始数组
        Character[] letters = {'P', 'C', 'M'};

        //数组转为List
        List<Character> list = Arrays.asList(letters);
        System.out.println("转换之后: ");
        output(list);

        // 反转List
        Collections.reverse(list);
        System.out.println("\n反转之后: ");
        output(list);

        // 复制List
        List<Character> copyList =
                Arrays.asList(new Character[3]);
        Collections.copy(copyList, list);
        System.out.println("\n复制之后: ");
        output(copyList);

        // 用特定的元素填充List
        Collections.fill(list, 'R');
        System.out.println("\n填充之后: ");
        output(list);
    }

    private static void output(List<Character> listRef) {
        System.out.print("List: ");

        for (Character element : listRef)
            System.out.printf("%s ", element);

        System.out.printf("\n最大值: %s", Collections.max(listRef));
        System.out.printf("  最小值: %s\n", Collections.min(listRef));
    }
}
