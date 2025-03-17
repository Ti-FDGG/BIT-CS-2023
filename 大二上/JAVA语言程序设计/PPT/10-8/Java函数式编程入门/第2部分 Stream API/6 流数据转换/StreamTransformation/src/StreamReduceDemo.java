import java.util.List;

public class StreamReduceDemo {

    public static void main(String[] args) {
        sumByLoop();
        sumByReduce();
    }

    //使用传统的循环方式来求和
    static void sumByLoop() {
        var nums = List.of(4, 5, 3, 9);
        int sum = 0;
        for (var num : nums) {
            sum += num;
        }
        System.out.println(sum);
    }

    //使用Reduce方式求和
    static void sumByReduce() {
        var nums = List.of(4, 5, 3, 9);

        var sum = nums.stream()
                .reduce(0, (a, b) -> a + b);
        System.out.println(sum);//21

        //用方法引用简写
        sum = nums.stream().reduce(0, Integer::sum);
        System.out.println(sum);
    }
}
