package com.jinxuliang;

import com.jinxuliang.model.OrderClient;

import java.util.Comparator;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class OptionalIntTest {
    public static void main(String[] args) {

        // 创建一个“空的”OptionalInt实例
        OptionalInt empty = OptionalInt.empty();
        // 创建一个 OptionalInt实例，保存数值287
        OptionalInt number = OptionalInt.of(287);
        //从OptionaInt实例中提取数据的方法
        if (number.isPresent()) {
            int value = number.getAsInt();
            System.out.println("Number is " + value);
        } else {
            System.out.println("Number is absent.");
        }

        // 一些Stream API方法，返回OptionalInt
        OptionalInt numbers = IntStream.of(1, 10, 37, 20, 31)
                .filter(n -> n % 2 == 1).max();
        if (numbers.isPresent()) {
            int value = numbers.getAsInt();
            System.out.println("最大值为： " + value);
        } else {
            System.out.println("流为空");
        }

        var client = getOrderClient();
        //client有可能为null,使用orElse指定默认值
        String name = client.map(OrderClient::getName).orElse("无名氏");
        System.out.println(name);



    }

    //用Optional封装有可能为null值的方法
    private static Optional<OrderClient> getOrderClient() {
        var ranValue = new Random().nextInt(100);
        if (ranValue % 2 == 0) {
            return Optional.empty();
        } else {
            var client = new OrderClient(ranValue,
                    "client" + ranValue);
            return Optional.of(client);
        }
    }
}
