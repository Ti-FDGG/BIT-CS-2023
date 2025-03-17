import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class ImperativeVSDeclarative {
    public static void main(String[] args) {
        calculateUseImperativeStyle();
        calculateUseDeclarativeStyle();
        ImperativeVsDeclarative();
    }

    static void calculateUseImperativeStyle() {
        //定义一个变量，用于保存中间结果
        int sum = 0;
        //写一个循环
        for (int i = 1; i <= 100; i++) {
            //每次循环时，都将当前数字取出来，累加到sum变量中
            sum += i;
        }
        //输出最终结果
        System.out.println(sum);
    }

    static void calculateUseDeclarativeStyle() {
        //我需要求出[1,100]这个区间中所有整数的和
        int sum = IntStream.rangeClosed(1, 100).sum();
        System.out.println(sum);
    }

    //移除列表中的重复数字
    static void ImperativeVsDeclarative(){
        List<Integer> integerList = Arrays.asList(1,2,3,4,4,5,5,6,7,7,8,9,9);

        //命令式编程风格
        List<Integer> uniqueList = new ArrayList<>();
        for(Integer i :integerList)
            if(!uniqueList.contains(i)){
                uniqueList.add(i);
            }
        System.out.println("unique List : " + uniqueList);

        //声明式编程风格
        List<Integer> uniqueList1 = integerList.stream()
                .distinct()
                .collect(toList());
        System.out.println("uniqueList1 : " + uniqueList1);
    }
}
