import java.util.List;

public class GetMax {
    public static void main(String[] args) {
        var nums = List.of(1, 5, 3, 9);
        var max = nums.stream()
                .reduce(Integer::max);
        if (max.isPresent()) {
            System.out.println("最大值为：" + max);
        } else {
            System.out.println("流为空！");
        }
    }
}
