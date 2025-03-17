package com.jinxuliang.protocol;

import lombok.Data;

//自定义通讯协议数据包的基类
@Data
public abstract class Packet {
    //版本
    private Byte version = 1;
    //指令（最多256条，事先约定好每个数字对应什么指令）
    public abstract Byte getCommand();
}
