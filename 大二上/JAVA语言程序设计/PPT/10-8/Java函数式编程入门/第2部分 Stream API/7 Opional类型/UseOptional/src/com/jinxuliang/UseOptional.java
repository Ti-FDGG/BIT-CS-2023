package com.jinxuliang;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class UseOptional {
    public static void main(String[] args) {
        var numbers = List.of(106, 234, 39, 134, 19, 80);
        //min函数（还有max函数），返回Optional
        var result = numbers.stream()
                .min(Comparator.naturalOrder());
        System.out.println(result.get());//19

        //of函数返回Optional
        Optional<String> str = Optional.of("Hello");
        //isPresent()可以简写为ifPresent
        str.ifPresent(System.out::println);

        //map函数返回Optional
        var stringLength= str.map(String::length);
        stringLength.ifPresent(System.out::println);
    }
}

