package com.jinxuliang.protocol.serialize;

//如果有多种序列化方式，则给其分配一个唯一的整数
public interface SerializerAlgorithm {
    //值为1时，采用JSON序列化
    byte JSON = 1;
}
