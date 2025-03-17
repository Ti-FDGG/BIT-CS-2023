package com.jinxuliang.protocol;

//在这里，集中定义指令集，可以定义最多256个指令
public interface Command {
    //用于登录的指令id
    Byte LOGIN_REQUEST = 1;

    //依据指令id，获取具体指令实现类型
    static Class<? extends Packet> getRequestType(byte command){
        if(command == LOGIN_REQUEST){
            return LoginRequestPacket.class;
        }
        return null;
    }
}
