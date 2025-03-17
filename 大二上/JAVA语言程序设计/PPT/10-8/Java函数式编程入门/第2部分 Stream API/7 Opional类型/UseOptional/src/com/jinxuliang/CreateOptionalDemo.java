package com.jinxuliang;

import java.util.Optional;
import java.util.stream.Stream;

public class CreateOptionalDemo {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("Optional", "type", null, "", "is", "very", "useful");
        stream.map(str -> processString(str, 2)) //截取两个字符
                .filter(str -> str.isPresent())  //过滤掉空引用
                .forEach(System.out::println); //输出结果
    }

    //截取指定长度的字符串
    public static Optional<String> processString(String string, int length) {
        if (string == null || length <= 0 || string.length() < length) {
            return Optional.empty();
        }
		//构建Optional对象
        return Optional.of(string.substring(0, length));
    }
}
